package com.nikego.skycapitals.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

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
