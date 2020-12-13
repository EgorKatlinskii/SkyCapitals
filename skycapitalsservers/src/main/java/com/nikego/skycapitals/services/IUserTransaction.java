package com.nikego.skycapitals.services;

public interface IUserTransaction {

    /*перевод с карты на карту*/
    boolean transferMoney(Long numberCardSender, Long numberCardRecipient, int sum);

}
