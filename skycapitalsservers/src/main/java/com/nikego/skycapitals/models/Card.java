package com.nikego.skycapitals.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "card")
public class Card {
    @Id
    @Column(name = "number_card")
    private long numberCard;

    @Column(name = "name_card")
    private String nameCard;

    @Column(name = "password")
    private int password;

    @Column(name = "number_score")
    @JsonIgnore
    private Integer numberScore;

    @Column(name = "balance")
    private Integer balance;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "number_card", insertable = false, updatable = false)
    @JsonIgnore
    private Score score;

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getNumberScore() {
        return numberScore;
    }

    public void setNumberScore(Integer numberScore) {
        this.numberScore = numberScore;
    }

    public long getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(long numberCard) {
        this.numberCard = numberCard;
    }

    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }
}

