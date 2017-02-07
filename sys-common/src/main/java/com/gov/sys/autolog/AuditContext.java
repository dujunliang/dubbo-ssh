package com.gov.sys.autolog;

/**
 * 將用戶請求信息綁定到當前線程
 * <p>
 * Author: Gavin
 * Date: Jul 19, 2016 12:00:11 PM
 * Version: 1.0
 */
public class AuditContext {

    private static final ThreadLocal<AuditContext> MAP = new ThreadLocal<AuditContext>();

    private AuditContext() {
    }

    public static AuditContext getInstance() {
        AuditContext context = MAP.get();
        if (context == null) {
            context = new AuditContext();
            MAP.set(context);
        }
        return context;
    }

    public void remove() {
        MAP.remove();
    }

    private Long logId;
    private String userId;
    private Integer serviceId;
    private Integer processId;
    private String funcCode;
    private String hostIP;
    private String hostName;
    private String requestURL;
    private String remark;
    private boolean isAudited = false;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public String getFuncCode() {
        return funcCode;
    }

    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode;
    }

    public String getHostIP() {
        return hostIP;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isAudited() {
        return isAudited;
    }

    public void setIsAudited(boolean isAudited) {
        this.isAudited = isAudited;
    }
}
