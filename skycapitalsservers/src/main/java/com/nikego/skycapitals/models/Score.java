package com.nikego.skycapitals.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    @NotNull
    private Integer userId;

    @Column(name = "number_score")
    private @NotNull Integer scoreNumber;

    @Column(name="name_card")
    @NotNull
    @OneToMany(mappedBy = "score",cascade = CascadeType.ALL)
    private List<Card> card;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",insertable = false, updatable = false)
    private User user;


    public Score(){}

    public Score(@NotNull Integer userId, @NotNull Integer scoreNumber, @NotNull List<Card> card) {
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

    public @NotNull Integer getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(@NotNull Integer scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
