package com.gov.sys.entity;



import com.gov.sys.audit.NotAudited;
import com.gov.sys.ws.UpdateTrackable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable, UpdateTrackable {

	private static final long serialVersionUID = 130624L;
	
	private String createUser;
    private Date createDate;
	private String lastUpdUser;
    private Date lastUpdDate;
    
    
    @Column(name="CREATE_USER",  columnDefinition="VARCHAR2(20 CHAR)", updatable=false)
    public String getCreateUser() {
        return createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    @Column(name = "CREATE_DATE", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    @NotAudited
    @Column(name="LAST_UPD_USER", columnDefinition="VARCHAR2(20 CHAR)")
    public String getLastUpdUser() {
        return lastUpdUser;
    }
    public void setLastUpdUser(String lastUpdUser) {
        this.lastUpdUser = lastUpdUser;
    }
    
    @NotAudited
    @Column(name = "LAST_UPD_DATE")
    @Temporal(TemporalType.TIMESTAMP)  
    public Date getLastUpdDate() {
        return lastUpdDate;
    }
    public void setLastUpdDate(Date lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }
}
