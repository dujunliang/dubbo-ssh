package com.gov.sys.audit;

/**
 * 用於審計日誌的特殊字段信息
 */
public interface IAuditEntry {

    Integer getVtaNo();

    Integer getVtaYear();

    Integer getVehId();

    String getSpNo();

    String getPlateNo();

    String getVehType();

    String getPlateGroup();

}
