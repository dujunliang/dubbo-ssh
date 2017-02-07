package com.gov.sys.autolog;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "VSS_AUDIT_DETAIL")
public class AuditDetailEntity implements Serializable {

    private static final long serialVersionUID = 20160717L;

    private Long id;
    private String columnName;
    private String valueBefore;
    private String valueAfter;

    private AuditIndexEntity auditIndexEntity;

    public AuditDetailEntity() {
    }

    public AuditDetailEntity(String columnName, String valueBefore, String valueAfter, AuditIndexEntity auditIndexEntity) {
        this.columnName = columnName;
        this.valueBefore = valueBefore;
        this.valueAfter = valueAfter;
        this.auditIndexEntity = auditIndexEntity;
    }

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "VSS_AUDIT_DETAIL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "AUDIT_DETAIL_ID", columnDefinition = "NUMBER(20, 0)")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "COLUMN_NAME", columnDefinition = "COLUMN_NAME(30 CHAR)", nullable = false)
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Column(name = "VALUE_BEFORE", columnDefinition = "VARCHAR2(4000 CHAR)")
    public String getValueBefore() {
        return valueBefore;
    }

    public void setValueBefore(String valueBefore) {
        this.valueBefore = valueBefore;
    }

    @Column(name = "VALUE_AFTER", columnDefinition = "VARCHAR2(4000 CHAR)")
    public String getValueAfter() {
        return valueAfter;
    }

    public void setValueAfter(String valueAfter) {
        this.valueAfter = valueAfter;
    }

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "AUDIT_INDEX_ID", nullable = false)
    public AuditIndexEntity getAuditIndexEntity() {
        return auditIndexEntity;
    }

    public void setAuditIndexEntity(AuditIndexEntity auditIndexEntity) {
        this.auditIndexEntity = auditIndexEntity;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("columnName", columnName)
                .append("valueBefore", valueBefore)
                .append("valueAfter", valueAfter)
                .toString();
    }
}
