package com.gov.sys.entity;

import com.gov.sys.audit.Audited;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by dujunliang on 16/11/28.
 */
@Entity
@Audited
@XmlRootElement
@Table(name = "SYS_USER")
public class UserEntity {


    public Long userId;

    public String userName;

    public String passWord;

    @Id
    @Column(name = "USER_ID", columnDefinition = "NUMBER(20, 0)")
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @Column(name = "USER_NAME", columnDefinition = "VARCHAR2(20 CHAR)")
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Column(name = "PASSWORD", columnDefinition = "VARCHAR2(20 CHAR)")
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
