package com.gov.sys.data.dto;

import java.io.Serializable;

/**
 * Created by dujunliang on 16/11/23.
 */
public class UserDTO implements Serializable {

    public String userName;

    public String passWord;

    public UserDTO() {

    }

    public UserDTO(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }
}
