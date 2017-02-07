package com.gov.sys.autolog;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: Gavin
 * Date: Jul 27, 2016 10:00:25 AM
 * Version: 1.0
 */
public class AuditEntry extends BaseAuditEntry {

    private static final long serialVersionUID = 20160720L;

    private Action action;
    private String entityId;
    private String entityName;
    private String tableName;

    private List<AuditField> auditFields;

    public AuditEntry() {
    }

    public AuditEntry(Action action, String entityId, String entityName, String tableName) {
        this.action = action;
        this.entityId = entityId;
        this.entityName = entityName;
        this.tableName = tableName;
    }

    public AuditEntry(Action action, String entityId, String entityName,
                      String tableName, List<AuditField> auditFields) {
        this(action, entityId, entityName, tableName);
        this.auditFields = auditFields;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<AuditField> getAuditFields() {
        if (this.auditFields == null) {
            this.auditFields = new ArrayList<AuditField>();
        }
        return auditFields;
    }

    public void setAuditFields(List<AuditField> auditFields) {
        this.auditFields = auditFields;
    }

    public void addAuditField(AuditField auditField) {
        if (auditField == null) {
            return;
        }
        getAuditFields().add(auditField);
    }

    public void removeAuditField(AuditField auditField) {
        if (auditField == null) {
            return;
        }
        getAuditFields().remove(auditField);
    }

    public void removeAuditFields() {
        Set<AuditField> auditFieldSet = new HashSet<AuditField>();
        auditFieldSet.addAll(getAuditFields());
        for (AuditField auditField : auditFieldSet) {
            removeAuditField(auditField);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("action", action)
                .append("entityId", entityId)
                .append("entityName", entityName)
                .append("tableName", tableName)
                .toString();
    }
}
