package com.nikego.skycapitals.services;

public interface IUserTransaction {


    /*перевод с карты на карту*/
    boolean transferMoney(Integer idSender,Integer idRecipient,int sum);

}
