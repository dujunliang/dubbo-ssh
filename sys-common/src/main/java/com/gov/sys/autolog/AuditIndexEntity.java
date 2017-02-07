package com.gov.sys.autolog;

import com.gov.sys.audit.IAuditEntry;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "VSS_AUDIT_INDEX")
public class AuditIndexEntity implements IAuditEntry, Serializable {

    private static final long serialVersionUID = 20160721L;

    private Long id;
    private String rowId;
    private Action action;
    private String tableName;
    private byte[] content;
    private Integer vtaNo;
    private Integer vtaYear;
    private Integer vehId;
    private String spNo;
    private String plateNo;
    private String vehType;
    private String plateGroup;

    private Set<AuditDetailEntity> auditDetailEntities;
    private AuditMasterEntity auditMasterEntity;

    public AuditIndexEntity() {
    }

    public AuditIndexEntity(String rowId, Action action, String tableName,
                            byte[] content, Integer vtaNo, Integer vtaYear, Integer vehId,
                            String spNo, String plateNo, String vehType, AuditMasterEntity auditMasterEntity) {
        this.rowId = rowId;
        this.action = action;
        this.tableName = tableName;
        this.content = content;
        this.vtaNo = vtaNo;
        this.vtaYear = vtaYear;
        this.vehId = vehId;
        this.spNo = spNo;
        this.plateNo = plateNo;
        this.vehType = vehType;
        this.auditMasterEntity = auditMasterEntity;
    }

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "VSS_AUDIT_INDEX_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "AUDIT_INDEX_ID", columnDefinition = "NUMBER(20, 0)")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ROW_ID", columnDefinition = "VARCHAR2(20 CHAR)", nullable = false)
    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    @Column(name = "ACTION", columnDefinition = "CHAR(1 CHAR)", nullable = false)
    @Enumerated(EnumType.STRING)
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Column(name = "TABLE_NAME", columnDefinition = "VARCHAR2(30 CHAR)", nullable = false)
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Lob
    @Column(name = "CONTENT")
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Column(name = "VTA_NO", columnDefinition = "NUMBER(5,0)")
    public Integer getVtaNo() {
        return vtaNo;
    }

    public void setVtaNo(Integer vtaNo) {
        this.vtaNo = vtaNo;
    }

    @Column(name = "VTA_YEAR", columnDefinition = "NUMBER(4,0")
    public Integer getVtaYear() {
        return vtaYear;
    }

    public void setVtaYear(Integer vtaYear) {
        this.vtaYear = vtaYear;
    }

    @Column(name = "VEH_ID", columnDefinition = "NUMBER(8,0)")
    public Integer getVehId() {
        return vehId;
    }

    public void setVehId(Integer vehId) {
        this.vehId = vehId;
    }

    @Column(name = "SP_NO", columnDefinition = "VARCHAR2(20 CHAR")
    public String getSpNo() {
        return spNo;
    }

    public void setSpNo(String spNo) {
        this.spNo = spNo;
    }

    @Column(name = "PLATE_NO", columnDefinition = "VARCHAR2(8 CHAR)")
    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    @Column(name = "VEH_TYPE", columnDefinition = "CHAR(1 CHAR)")
    public String getVehType() {
        return vehType;
    }

    public void setVehType(String vehType) {
        this.vehType = vehType;
    }

    @Override
    @Column(name = "PLATE_GROUP", columnDefinition = "CHAR(1 CHAR)")
    public String getPlateGroup() {
        return plateGroup;
    }

    public void setPlateGroup(String plateGroup) {
        this.plateGroup = plateGroup;
    }

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "AUDIT_MASTER_ID", nullable = false)
    public AuditMasterEntity getAuditMasterEntity() {
        return auditMasterEntity;
    }

    public void setAuditMasterEntity(AuditMasterEntity auditMasterEntity) {
        this.auditMasterEntity = auditMasterEntity;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "auditIndexEntity")
    public Set<AuditDetailEntity> getAuditDetailEntities() {
        return auditDetailEntities;
    }

    public void setAuditDetailEntities(Set<AuditDetailEntity> auditDetailEntities) {
        this.auditDetailEntities = auditDetailEntities;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("rowId", rowId)
                .append("action", action)
                .append("tableName", tableName)
                .append("content", content)
                .append("vtaNo", vtaNo)
                .append("vtaYear", vtaYear)
                .append("vehId", vehId)
                .append("spNo", spNo)
                .append("plateNo", plateNo)
                .append("vehType", vehType)
                .toString();
    }
}
