package com.gov.sys.autolog;

import com.gov.sys.audit.IAuditEntry;

import javax.persistence.Transient;
import java.io.Serializable;

public abstract class BaseAuditEntry implements IAuditEntry, Serializable {

    private static final long serialVersionUID = 160826L;

    private Integer vtaNo;
    private Integer vtaYear;
    private Integer vehId;
    private String spNo;
    private String plateNo;
    private String vehType;
    private String plateGroup;

    @Transient
    public Integer getVtaNo() {
        return vtaNo;
    }

    public void setVtaNo(Integer vtaNo) {
        this.vtaNo = vtaNo;
    }

    @Transient
    public Integer getVtaYear() {
        return vtaYear;
    }

    public void setVtaYear(Integer vtaYear) {
        this.vtaYear = vtaYear;
    }

    @Transient
    public Integer getVehId() {
        return vehId;
    }

    public void setVehId(Integer vehId) {
        this.vehId = vehId;
    }

    @Transient
    public String getSpNo() {
        return spNo;
    }

    public void setSpNo(String spNo) {
        this.spNo = spNo;
    }

    @Transient
    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    @Transient
    public String getVehType() {
        return vehType;
    }

    public void setVehType(String vehType) {
        this.vehType = vehType;
    }

    @Transient
    public String getPlateGroup() {
        return plateGroup;
    }

    public void setPlateGroup(String plateGroup) {
        this.plateGroup = plateGroup;
    }

}
