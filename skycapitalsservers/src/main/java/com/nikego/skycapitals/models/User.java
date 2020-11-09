package com.nikego.skycapitals.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="test_tbl")
public class User {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private  Integer userId;
    @Column(name="user_name")
    private String userName;
    @Column(name="user_surname")
    private String userSurname;
    @Column(name="ost_office")
    private String ostOffice;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getOstOffice() {
        return ostOffice;
    }

    public void setOstOffice(String ostOffice) {
        this.ostOffice = ostOffice;
    }
}
