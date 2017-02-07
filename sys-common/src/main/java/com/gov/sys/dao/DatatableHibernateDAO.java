/**
 * @Project Name: dsat-common
 * @File Name: DataTableHibernateDAO.java
 * @Package Name: mo.gov.dsat.dao
 * @Date: 13/04/20165:15:25 PM
 * @Copyright: Copyright (c) 2016 Atos Information Technology HK Limited. All Rights Reserved.
 */
package com.gov.sys.dao;


import com.gov.sys.datatable.DatatableDaoCmd;
import com.gov.sys.datatable.Page;
import com.gov.sys.exception.DatabaseException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public abstract class DatatableHibernateDAO extends HibernateDAO implements DatatableDAO {

    @Override
    public <T extends Serializable> Page<T> getPage(DaoCmd cmd, Class<?> clazz, Integer maxResult, Integer startFrom) {
        List<T> entities = getList(cmd, clazz, maxResult, startFrom, null);
        int count = getCount(cmd);
        Page<T> page = new Page<T>();
        page.setEntities(entities);
        page.setCount(count);
        return page;
    }

    @Override
    public <T extends Serializable> Page<T> getPage(DatatableDaoCmd cmd, Class<?> clazz) {
        List<T> entities = getList(cmd, clazz, cmd.getDatatableDto().getLength(), cmd.getDatatableDto().getStart(), null);
        int count = getCount(cmd);
        Page<T> page = new Page<T>();
        page.setEntities(entities);
        page.setCount(count);
        return page;
    }

    @Override
    public <T extends Serializable> Page<T> getPage(DatatableDaoCmd cmd, Class<?> clazz, ResultTransformer transformer) {
        List<T> entities = getList(cmd, clazz, cmd.getDatatableDto().getLength(), cmd.getDatatableDto().getStart(), transformer);
        int count = getCount(cmd);
        Page<T> page = new Page<T>();
        page.setEntities(entities);
        page.setCount(count);
        return page;
    }

    @Override
    public int getCount(DaoCmd cmd) {
        Query query = createCountQuery(cmd);
        return Integer.parseInt(query.uniqueResult().toString());
    }

    private Query createCountQuery(DaoCmd cmd) {
        Session session = getSession();

        String queryType = queryFile.getString(cmd.getQueryKey() + "[@type]");
        if (queryType == null) {
            queryType = HQL;
        }

        Query query = null;
        if (HQL.equals(queryType)) {

            String queryString = processTemplate(cmd);
            queryString = prepareCountHql(queryString);
            query = session.createQuery(queryString);

        } else if (SQL.equals(queryType)) {
            // String queryString = queryFile.getString(cmd.getQueryKey());
            String queryString = processTemplate(cmd);
            if (cmd.getStatements() != null && !cmd.getStatements().isEmpty()) {
                StrSubstitutor sub = new StrSubstitutor(cmd.getStatements(), "{", "}");
                queryString = sub.replace(queryString);
            }
            String countString = prepareCountSQL(queryString);
            query = session.createSQLQuery(countString);
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

    private String prepareCountHql(String orgHql) {
        String fromHql = orgHql;
        fromHql = StringUtils.substring(fromHql, StringUtils.indexOf(fromHql.toLowerCase(), "from"));
        int indexof = StringUtils.lastIndexOf(fromHql.toLowerCase(), "order by");
        if (indexof > -1) {
            fromHql = StringUtils.substring(fromHql, 0, StringUtils.indexOf(fromHql.toLowerCase(), "order by"));
        }
        return "SELECT COUNT(*) " + fromHql;
    }

    private String prepareCountSQL(String orgSql) {
        String fromSql = orgSql;
        fromSql = StringUtils.substring(fromSql, 0, StringUtils.indexOf(fromSql.toLowerCase(), "order by"));
        String countSql = "SELECT COUNT(*) FROM (" + orgSql + ") AA";
        return countSql;
    }

    /******************************************************************/
    // add getPageByNativeSQL TODO(Jim 20160831)
    /******************************************************************/
    /**
     * oracle pagination params,rownum
     */
    private static final String PARAMS_ROWNUM_START = "_rownumStart";
    private static final String PARAMS_ROWNUM_END = "_rownumEnd";

    @Override
    public <T extends Serializable> Page<T> getPageByNativeSQL(DatatableDaoCmd cmd, Class<?> clazz) {
        int startForm = cmd.getDatatableDto().getStart();
        int maxResult = cmd.getDatatableDto().getLength();
        Map<String, Object> params = (Map<String, Object>) cmd.getParams();
        params.put(PARAMS_ROWNUM_START, startForm);
        params.put(PARAMS_ROWNUM_END, startForm + maxResult);
        cmd.setParams(params);
        List<T> entities = getList(cmd, clazz, null, null, null);

        params.put(PARAMS_ROWNUM_START, null);
        params.put(PARAMS_ROWNUM_END, null);
        cmd.setParams(params);
        int count = getCount(cmd);
        Page<T> page = new Page<T>();
        page.setEntities(entities);
        page.setCount(count);
        return page;
    }

    @Override
    public <T extends Serializable> Page<T> getPageByNativeSQL(DatatableDaoCmd cmd, Class<?> clazz, ResultTransformer transformer) {
        int startForm = cmd.getDatatableDto().getStart();
        int maxResult = cmd.getDatatableDto().getLength();
        Map<String, Object> params = (Map<String, Object>) cmd.getParams();
        params.put(PARAMS_ROWNUM_START, startForm);
        params.put(PARAMS_ROWNUM_END, startForm + maxResult);
        cmd.setParams(params);
        List<T> entities = getList(cmd, clazz, null, null, transformer);

        params.put(PARAMS_ROWNUM_START, null);
        params.put(PARAMS_ROWNUM_END, null);
        cmd.setParams(params);
        int count = getCount(cmd);
        Page<T> page = new Page<T>();
        page.setEntities(entities);
        page.setCount(count);
        return page;
    }
}
