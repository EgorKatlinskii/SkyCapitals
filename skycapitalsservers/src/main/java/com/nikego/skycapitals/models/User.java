package com.nikego.skycapitals.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@SecondaryTable(name = "score", pkJoinColumns = @PrimaryKeyJoinColumn(name = "Custom_id"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private Integer userId;

    @Column(name = "user_name")
    @NotNull
    @NotEmpty(message = "Please provide a name!")
    private String userName;

    @Column(name = "user_surname")
    @NotNull
    @NotEmpty(message = "Please provide a surname!")
    private String userSurname;

    @Column(name = "ost_office")
    @NotNull
    @NotEmpty(message = "Please provide a office!")
    private String ostOffice;

    @Column(name = "number_score", table = "score")
    @NotNull
    private long scoreNumber;

    @Column(name = "name_card", table= "score")
    @NotNull
    private String cardName;

    @Column(name = "balance",table = "score")
    @NotNull
    private int userBalance;

    public int getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(int userBalance) {
        this.userBalance = userBalance;
    }

    public String getCardName() {
        return cardName;
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

    public long getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(Integer scoreNumber) {
        this.scoreNumber = scoreNumber;
    }
}
