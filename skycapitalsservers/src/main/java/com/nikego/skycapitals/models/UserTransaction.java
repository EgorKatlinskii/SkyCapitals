package com.nikego.skycapitals.models;

import com.nikego.skycapitals.services.IUserTransaction;
import org.springframework.stereotype.Service;

@Service
public class UserTransaction implements IUserTransaction {
    private final UserServiceImpl userServiceImp;

    public UserTransaction(UserServiceImpl userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    /*перевод с карты на карту*/
    @Override
    public boolean transferMoney(Integer idSender, Integer idRecipient, int sum) {
        try {
            final User userSender = userServiceImp.read(idSender);
            final User userRecipient = userServiceImp.read(idRecipient);
            if (userSender.getUserBalance() > sum) {
                userRecipient.setUserBalance(userRecipient.getUserBalance() + sum);
                userSender.setUserBalance(userSender.getUserBalance() - sum);
                try {
                    userServiceImp.update(userSender, userSender.getUserId());
                    userServiceImp.update(userRecipient, userRecipient.getUserId());

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
