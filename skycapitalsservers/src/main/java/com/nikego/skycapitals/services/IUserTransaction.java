package com.nikego.skycapitals.services;

import com.nikego.skycapitals.models.Card;

public interface IUserTransaction {

    /*перевод с карты на карту*/
    boolean transferMoney(Integer numberCardSender, Integer numberCardRecipient, int sum);

}
