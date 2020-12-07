package com.nikego.skycapitals.services;

import com.nikego.skycapitals.models.Card;

public interface CardService {

    Card read(int id);
    boolean update(Card card, Integer numberCard);
}
