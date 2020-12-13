package com.nikego.skycapitals.services;

import com.nikego.skycapitals.models.Card;

import java.util.Optional;

public interface CardService {

    Card read(Long id);

    boolean update(Card card, long numberCard);

    Optional create(String nameCard, int id);
}
