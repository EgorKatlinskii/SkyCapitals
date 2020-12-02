package com.nikego.skycapitals.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "score")
public class Score {

    @Column(name = "userId")
    @NotNull
    private Integer userId;

    @Column(name = "number_score")
    @NotNull
    private long scoreNumber;

    @Column(name="name_card")
    @NotNull
    private String card;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


    public Score(@NotNull int userId, @NotNull long scoreNumber, @NotNull String card) {
        this.userId = userId;
        this.scoreNumber = scoreNumber;
        this.card = card;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(long scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
