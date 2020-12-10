package com.nikego.skycapitals.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private Integer userId;

    @Column(name = "user_name")
    @NotBlank
    @NotEmpty(message = "Please provide a name!")
    private String userName;

    @Column(name = "password")
    @NotNull
    private Integer password;

    @Column(name = "user_surname")
    @NotBlank
    @NotEmpty(message = "Please provide a surname!")
    private String userSurname;

    @Column(name = "ost_office")
    @NotBlank
    @NotEmpty(message = "Please provide a office!")
    private String ostOffice;

    public List<Score> getScore() {
        return score;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Score> score;

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
