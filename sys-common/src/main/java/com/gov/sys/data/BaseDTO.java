package com.gov.sys.data;


import com.gov.sys.datatable.AbstractDatatableDTO;

import java.util.Date;


public class BaseDTO extends AbstractDatatableDTO {
    private static final long serialVersionUID = 3761458575768025556L;
    private String createUser;
    private Date createDate;
    private String lastUpdUser;
    private Date lastUpdDate;
    
    private String spNo;
    private String nextUrlId;
    
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdUser() {
        return lastUpdUser;
    }

    public void setLastUpdUser(String lastUpdUser) {
        this.lastUpdUser = lastUpdUser;
    }

    public Date getLastUpdDate() {
        return lastUpdDate;
    }

    public void setLastUpdDate(Date lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }

    public String getSpNo() {
        return spNo;
    }

    public void setSpNo(String spNo) {
        this.spNo = spNo;
    }

    public String getNextUrlId() {
        return nextUrlId;
    }

    public void setNextUrlId(String nextUrlId) {
        this.nextUrlId = nextUrlId;
    }

}
