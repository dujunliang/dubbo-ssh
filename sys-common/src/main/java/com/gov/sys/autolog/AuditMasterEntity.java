package com.gov.sys.autolog;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "VSS_AUDIT_MASTER")
public class AuditMasterEntity implements Serializable {

    private static final long serialVersionUID = 20160717L;

    private Long id;
    private String funcCode;
    private String operatorId;
    private Date operationTime;
    private Integer serviceId;
    private Integer processId;
    private String hostIP;
    private String hostName;
    private String remark;

    // private byte[] content;
    // private String subFuncCode;
    // private String approverId;
    // private String approveTime;

    private Set<AuditIndexEntity> auditIndexEntities;

    public AuditMasterEntity() {
    }

    public AuditMasterEntity(String funcCode, String operatorId, Date operationTime, Integer serviceId,
                             Integer processId, String hostIP, String hostName, String remark) {
        this.funcCode = funcCode;
        this.operatorId = operatorId;
        this.operationTime = operationTime;
        this.serviceId = serviceId;
        this.processId = processId;
        this.hostIP = hostIP;
        this.hostName = hostName;
        this.remark = remark;
    }

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "VSS_AUDIT_MASTER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "AUDIT_MASTER_ID", columnDefinition = "NUMBER(20, 0)")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "FUNC_CODE", columnDefinition = "VARCHAR2(24 CHAR)", nullable = false)
    public String getFuncCode() {
        return funcCode;
    }

    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode;
    }

    @Column(name = "OPERATOR_ID", columnDefinition = "VARCHAR2(20 CHAR)", nullable = false)
    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    @Column(name = "OPERATION_TIME", nullable = false)
    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    @Column(name = "SERVICE_ID", columnDefinition = "NUMBER(4, 0)")
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Column(name = "PROCESS_ID", columnDefinition = "NUMBER(4, 0)")
    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    @Column(name = "HOST_IP", columnDefinition = "VARCHAR2(20 CHAR)")
    public String getHostIP() {
        return hostIP;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    @Column(name = "HOST_NAME", columnDefinition = "VARCHAR2(20 CHAR)")
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Column(name = "REMARK", columnDefinition = "VARCHAR2(200 CHAR)")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "auditMasterEntity")
    public Set<AuditIndexEntity> getAuditIndexEntities() {
        return auditIndexEntities;
    }

    public void setAuditIndexEntities(Set<AuditIndexEntity> auditIndexEntities) {
        this.auditIndexEntities = auditIndexEntities;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("remark", remark)
                .append("id", id)
                .append("funcCode", funcCode)
                .append("operatorId", operatorId)
                .append("operationTime", operationTime)
                .append("serviceId", serviceId)
                .append("processId", processId)
                .append("hostIP", hostIP)
                .append("hostName", hostName)
                .toString();
    }
}
