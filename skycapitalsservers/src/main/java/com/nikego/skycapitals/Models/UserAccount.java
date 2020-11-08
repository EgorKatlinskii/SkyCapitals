package com.nikego.skycapitals.Models;

import javax.persistence.*;

@Entity
@Table (name="User Account")
public class UserAccount {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private  Integer userId;
    @Column(name="User Name")
    private String userName;
    @Column(name="User Surname")
    private String userSurname;
    @Column(name="Ost Office")
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
