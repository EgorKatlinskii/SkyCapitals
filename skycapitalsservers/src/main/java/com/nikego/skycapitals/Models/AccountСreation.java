package com.nikego.skycapitals.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*создание аккаунта*/
@Entity
class AccountСreation {

    private @Id @GeneratedValue int userId;
    private String userName;
    private String userSurname;
    private String ostOffice;

    public AccountСreation(int userId, String userName, String userSurname, String ostOffice) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.ostOffice = ostOffice;
    }

}
