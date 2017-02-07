package com.gov.sys.autolog;

import com.gov.sys.audit.Audited;
import com.gov.sys.audit.IAuditEntry;
import com.gov.sys.audit.NotAudited;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.*;
import org.hibernate.persister.entity.EntityPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/**
 * Audit Log Listener is used to log insert, update, and delete operations.
 * Complete list of listeners/events can be obtained at <tt>org.hibernate.event.EventListeners</tt>.
 */
public class AuditEventListener implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener, Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditEventListener.class);

    private static final String DATETIME_PATTERN = "dd/MM/yyyy HH:mm:ss";

    private AuditConfiguration auditConfiguration;

    /**
     * @see PostInsertEventListener#onPostInsert(PostInsertEvent)
     */
    @Override
    public void onPostInsert(PostInsertEvent event) {
        auditEvent(event.getSession(), event.getPersister(), Action.I, event.getId(),
                event.getEntity(), null, event.getState());
    }

    /**
     * @see PostUpdateEventListener#onPostUpdate(PostUpdateEvent)
     */
    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        auditEvent(event.getSession(), event.getPersister(), Action.U, event.getId(),
                event.getEntity(), event.getOldState(), event.getState());
    }

    /**
     * @see PostDeleteEventListener#onPostDelete(PostDeleteEvent)
     */
    @Override
    public void onPostDelete(PostDeleteEvent event) {
        auditEvent(event.getSession(), event.getPersister(), Action.D, event.getId(),
                event.getEntity(), event.getDeletedState(), null);
    }

    @Override
    public void initialize(Configuration configuration) {
        auditConfiguration = AuditConfiguration.get(configuration);
    }

    private void auditEvent(EventSource session, EntityPersister entityPersister, Action action,
                            Serializable entityId, Object entity, Object[] oldState, Object[] newState) {
        try {
            if (isAudited()) {
                Class<?> entityClass = entity.getClass();
                if (isAudited(entityClass)) {
                    final AuditEntry auditEntry = createAuditEntry(action, entityId.toString(),
                            entityPersister.getEntityName(), getTableName(entityClass), entity);

                    String[] propertyNames = entityPersister.getPropertyNames();
                    if (!ObjectUtils.isEmpty(propertyNames)) {
                        addAuditFields(auditEntry, propertyNames, oldState, newState, entityClass, action);
                    }

                    auditConfiguration.getAuditProcessManager().get(session).add(auditEntry);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error get entity info.", e);
        }
    }

    private AuditEntry createAuditEntry(Action action, String entityId, String entityName, String tableName, Object entity) {
        AuditEntry auditEntry = new AuditEntry();
        if (entity instanceof IAuditEntry) {
            IAuditEntry entry = (IAuditEntry) entity;
            auditEntry.setVtaNo(entry.getVtaNo());
            auditEntry.setVtaYear(entry.getVtaYear());
            auditEntry.setVehId(entry.getVehId());
            auditEntry.setSpNo(entry.getSpNo());
            auditEntry.setVehType(entry.getVehType());
            auditEntry.setPlateGroup(entry.getPlateGroup());
        } else {
            BeanUtils.copyProperties(entity, auditEntry);
        }
        auditEntry.setAction(action);
        auditEntry.setEntityId(entityId);
        auditEntry.setEntityName(entityName);
        auditEntry.setTableName(tableName);
        return auditEntry;
    }

    private void addAuditFields(AuditEntry auditEntry, String[] propertyNames,
                                Object[] oldState, Object[] newState, Class<?> entityClass, Action action) {
        boolean isUpdate = (Action.U == action);
        for (int i = 0; i < propertyNames.length; i++) {
            PropertyDescriptor descriptor = getPropertyDescriptor(entityClass, propertyNames[i]);
            if (descriptor != null) {
                Method method = descriptor.getReadMethod();
                if (method == null || isNotAudited(method) || isCollection(method)) {
                    continue;
                }
                Column column = getColumn(method);
                if (column == null || StringUtils.isBlank(column.name())) {
                    continue;
                }
                if (isUpdate && !column.updatable()) {
                    continue;
                }
                Object oldValue = (oldState != null) ? oldState[i] : null;
                Object newValue = (newState != null) ? newState[i] : null;
                if (isNotChanged(oldValue, newValue)) {
                    continue;
                }
                auditEntry.addAuditField(createAuditField(
                        propertyNames[i],
                        column.name(),
                        getValue(oldValue),
                        getValue(newValue)
                ));
            }
        }
    }

    private AuditField createAuditField(String propertyName, String columnName, Object oldValue, Object newValue) {
        return new AuditField(propertyName, columnName, getValue(oldValue), getValue(newValue));
    }

    private boolean isAudited() {
        AuditContext auditContext = AuditContext.getInstance();
        return StringUtils.isNotBlank(auditContext.getUserId())
                && StringUtils.isNotBlank(auditContext.getFuncCode())
                && auditContext.isAudited();
    }

    private static PropertyDescriptor getPropertyDescriptor(Class<?> entityClass, String propertyName) {
        return BeanUtils.getPropertyDescriptor(entityClass, propertyName);
    }

    private static boolean isCollection(Method method) {
        return Collection.class.isAssignableFrom(method.getReturnType());
    }

    private static boolean isNotChanged(Object oldValue, Object newValue) {
        if (oldValue == newValue) {
            return true;
        }
        if (oldValue == null) {
            return StringUtils.isBlank(newValue.toString());
        }
        if (newValue == null) {
            return StringUtils.isBlank(oldValue.toString());
        }
        if (oldValue instanceof Date && newValue instanceof Date) {
            return DateUtils.truncatedEquals((Date) oldValue, (Date) newValue, Calendar.SECOND);
        }
        return (StringUtils.isBlank(oldValue.toString()) && StringUtils.isBlank(newValue.toString()))
                || Objects.equals(oldValue, newValue);
    }

    private static String getValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Date) {
            return DateFormatUtils.format((Date) value, DATETIME_PATTERN);
        }
        return value.toString();
    }

    private static String getTableName(Class<?> entityClass) {
        return entityClass.getAnnotation(Table.class).name();
    }

    private static Column getColumn(Method method) {
        return method.getAnnotation(Column.class);
    }

    private static boolean isAudited(Class<?> entityClass) {
        return entityClass.isAnnotationPresent(Audited.class);
    }

    private static boolean isNotAudited(Method method) {
        return method.isAnnotationPresent(NotAudited.class);
    }

}
