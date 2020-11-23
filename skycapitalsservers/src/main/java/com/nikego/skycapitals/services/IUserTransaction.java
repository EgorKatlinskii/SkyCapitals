package com.nikego.skycapitals.services;

import com.nikego.skycapitals.models.User;

public interface IUserTransaction {


    /*перевод с карты на карту*/
    boolean transferMoney(User userSender,User userRecipient,int sum);

}
