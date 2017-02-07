package com.gov.sys.dao;

import com.gov.sys.entity.AbstractEntity;
import com.gov.sys.exception.DatabaseException;
import com.gov.sys.exception.SysException;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.StringWriter;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class HibernateDAO implements DAO {

    /**
     * Logger available to subclasses
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(HibernateDAO.class);

    protected static final String HQL = "hql";
    protected static final String SQL = "sql";

    private static final Configuration FREEMARKER = new Configuration();

    protected XMLConfiguration queryFile;

    protected abstract EntityManager getEntityManager();

    //-------------------------------------------------------------------------
    // Convenience methods for storing individual objects
    //-------------------------------------------------------------------------

    public Object save(Object obj) {
        getEntityManager().persist(setOperationInfo(obj));
        return obj;
    }

    public AbstractEntity add(AbstractEntity entity) {
        save(entity);
        return entity;
    }

    public void update(Object obj) {
        getEntityManager().persist(setOperationInfo(obj));
    }

    public void delete(Object obj) {
        getEntityManager().remove(setOperationInfo(obj));
    }

    //-------------------------------------------------------------------------
    // Convenience methods for loading single object
    //-------------------------------------------------------------------------

    public <T> T getSingle(DaoCmd cmd, Class<?> T, ResultTransformer transformer) {
        Query query = createQuery(cmd);
        if (transformer != null) {
            query.setResultTransformer(transformer);
        }
        return (T) query.uniqueResult();
    }

    public <T> T getSingle(DaoCmd cmd, Class<?> T, boolean aliasToBean) {
        return getSingle(cmd, T, aliasToBean ? getTransformerAdapter(T) : null);
    }

    public <T> T getSingle(DaoCmd cmd, Class<?> T) {
        return getSingle(cmd, T, null);
    }

    public <T> T getSingle(Class<T> entityClass, Object entityPK) {
        return getEntityManager().find(entityClass, entityPK);
    }

    //-------------------------------------------------------------------------
    // Convenience methods for loading multiple objects
    //-------------------------------------------------------------------------

    public <T> List<T> getList(DaoCmd cmd, Class<?> T, Integer maxResult, Integer startFrom, ResultTransformer transformer) {
        Query query = createQuery(cmd);
        if (maxResult != null) {
            query.setMaxResults(maxResult);
        }
        if (startFrom != null) {
            query.setFirstResult(startFrom);
        }
        if (transformer != null) {
            query.setResultTransformer(transformer);
        }
        return query.list();
    }

    public <T> List<T> getList(DaoCmd cmd, Class<?> T, Integer maxResult, ResultTransformer transformer) {
        return getList(cmd, T, maxResult, null, transformer);
    }

    public <T> List<T> getList(DaoCmd cmd, Class<?> T, ResultTransformer transformer) {
        return getList(cmd, T, null, null, transformer);
    }

    public <T> List<T> getList(DaoCmd cmd, Class<?> T, Integer maxResult) {
        return getList(cmd, T, maxResult, null, null);
    }

    public <T> List<T> getList(DaoCmd cmd, Class<?> T, Integer maxResult, Integer startFrom) {
        return getList(cmd, T, maxResult, startFrom, null);
    }

    public <T> List<T> getList(DaoCmd cmd, Class<?> T) {
        return getList(cmd, T, null, null, null);
    }

    public <T> List<T> getList(DaoCmd cmd, Class<?> T, Integer maxResult, Integer startFrom, boolean aliasToBean) {
        return getList(cmd, T, maxResult, startFrom, aliasToBean ? getTransformerAdapter(T) : null);
    }

    public <T> List<T> getList(DaoCmd cmd, Class<?> T, Integer maxResult, boolean aliasToBean) {
        return getList(cmd, T, maxResult, null, aliasToBean);
    }

    public <T> List<T> getList(DaoCmd cmd, Class<?> T, boolean aliasToBean) {
        return getList(cmd, T, null, aliasToBean);
    }

    //-------------------------------------------------------------------------
    // Helper methods used by the operations above
    //-------------------------------------------------------------------------

    private ResultTransformer getTransformerAdapter(Class clazz) {
        return Map.class.isAssignableFrom(clazz) ? Transformers.ALIAS_TO_ENTITY_MAP : BeanTransformerAdapter.newInstance(clazz);
    }

    private Object setOperationInfo(Object obj) {
        Assert.notNull(obj);
        if (obj instanceof AbstractEntity) {
            AbstractEntity entity = (AbstractEntity) obj;
            Date sysDate = Calendar.getInstance().getTime();
//            User user = UserContext.getUser();
//            if (user == null) {
//                user = new AnonymousUser();
//            }
//            if (!getEntityManager().contains(entity)) {
//                entity.setCreateDate(sysDate);
//                entity.setCreateUser(user.getUserId());
//            }
//            entity.setLastUpdDate(sysDate);
//            entity.setLastUpdUser(user.getUserId());
            return entity;
        }
        return obj;
    }

    protected Query createQuery(DaoCmd cmd) {
        Session session = getSession();

        String queryType = queryFile.getString(cmd.getQueryKey() + "[@type]");
        if (queryType == null) {
            queryType = HQL;
        }

        Query query = null;
        if (HQL.equals(queryType)) {

            StringBuilder addString = new StringBuilder();
            if (StringUtils.isNotBlank(cmd.getParamString())) {
                addString.append(cmd.getParamString());
            }
            if (StringUtils.isNotBlank(cmd.getOrderString())) {
                addString.append(cmd.getOrderString());
            }

            // String queryString = queryFile.getString(cmd.getQueryKey());
            String queryString = processTemplate(cmd);

            if (addString.length()>0) {
                if (StringUtils.contains(queryString.toLowerCase(), " order ")) {
                    queryString = queryString + "," + addString.toString();
                } else {
                    queryString = queryString + " order by " + addString.toString();
                }
            }
            query = session.createQuery(queryString);

        } else if (SQL.equals(queryType)) {
            StringBuilder addString = new StringBuilder();
            if (StringUtils.isNotBlank(cmd.getParamString())) {
                addString.append(cmd.getParamString());
            }
            if (StringUtils.isNotBlank(cmd.getOrderString())) {
                addString.append(cmd.getOrderString());
            }

            String queryString = processTemplate(cmd);
            if (addString.length()>0) {
                if (StringUtils.contains(queryString.toLowerCase(), " order ")) {
                    queryString = queryString + "," + addString.toString();
                } else {
                    queryString = queryString + " order by " + addString.toString();
                }
            }
            query = session.createSQLQuery(queryString);
            
        } else {
            throw new DatabaseException("Unknown query type: " + queryType);
        }

        Object params = cmd.getParams();
        if (params != null) {
            if (params instanceof Map) {
                query.setProperties((Map) params);
            } else {
                query.setProperties(params);
            }
        }
        return query;
    }

    /**
     * FreeMarker Template Process
     */
    protected String processTemplate(DaoCmd cmd) {
        String queryKey = cmd.getQueryKey();
        String queryString = queryFile.getString(cmd.getQueryKey());

        StringTemplateLoader templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate(queryKey, queryString);
        FREEMARKER.setTemplateLoader(templateLoader);

        try {
            StringWriter writer = new StringWriter();
            FREEMARKER.getTemplate(queryKey).process(cmd.getParams(), writer);
            return writer.toString();
        } catch (Exception e) {
            LOGGER.error(e.toString());
            throw new SysException("An exception occurred while processing the query template: " + e.toString());
        }

    }

    public void execute(DaoCmd cmd) {

        String queryType = queryFile.getString(cmd.getQueryKey() + "[@type]");
        if (queryType == null) {
            queryType = HQL;
        }

        // String queryString = queryFile.getString(cmd.getQueryKey());
        String queryString = processTemplate(cmd);

        Session session = getSession();
        Query query = null;
        if (HQL.equals(queryType)) {
            query = session.createQuery(queryString);
        } else if (SQL.equals(queryType)) {
            query = session.createSQLQuery(queryString);
        } else {
            throw new DatabaseException("Unknown query type: " + queryType);
        }
        if (cmd.getParams() instanceof Map) {
            query.setProperties((Map) cmd.getParams());
        } else {
            query.setProperties(cmd.getParams());
        }
        query.executeUpdate();
    }

    /**
     * Call Procedure
     */
    public void execute(String queryKey, Object... args) {
        String queryString = queryFile.getString(queryKey);
        if (StringUtils.isNotBlank(queryString)) {
            Query query = getSession().createSQLQuery(queryString);
            for (int i = 0; i < args.length; i++) {
                query.setParameter(i, args[i]);
            }
            query.executeUpdate();
        }
    }

    protected final Session getSession() {
        return (Session) getEntityManager().getDelegate();
    }

    public void setQueryFilePath(String queryFilePath)
            throws ConfigurationException {
        queryFile = new XMLConfiguration();
        queryFile.setDelimiterParsingDisabled(true);
        queryFile.load(queryFilePath);
    }

    public void setQueryFileURL(URL queryFileURL) throws ConfigurationException {
        queryFile = new XMLConfiguration();
        queryFile.setDelimiterParsingDisabled(true);
        queryFile.load(queryFileURL);
    }

    public void flush() {
        getSession().flush();
    }

}
