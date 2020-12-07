package com.nikego.skycapitals.models;

import javax.persistence.*;


@Entity
@Table(name = "card")
public class Card {
    @Id
    @Column(name = "number_card")
    private Integer numberCard;

    @Column(name = "name_card")
    private String nameCard;

    @Column(name = "number_score")
    private Integer numberScore;

    @Column(name = "balance")
    private Integer balance;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "number_card", insertable = false, updatable = false)
    private Score score;


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

    public Integer getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(Integer numberCard) {
        this.numberCard = numberCard;
    }

    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }
}

