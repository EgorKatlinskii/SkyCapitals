package com.nikego.skycapitals.models;

import com.nikego.skycapitals.repository.DBRepository;
import com.nikego.skycapitals.services.IUserTransaction;
import org.springframework.stereotype.Service;

@Service
public class UserTransaction implements IUserTransaction{

    private final DBRepository DBrepository;

    public UserTransaction(DBRepository DBrepository) {
        this.DBrepository = DBrepository;
    }

    /*перевод с карты на карту*/
    @Override
    public boolean transferMoney(User userSender, User userRecipient,int sum) {
        if(userSender.getUserBalance()!=0){
            userRecipient.setUserBalance(userRecipient.getUserBalance()+sum);
            userSender.setUserBalance(userSender.getUserBalance()-sum);
        }
        else{
            return false;
        }
        return true;
    }
}
