package com.nikego.skycapitals.models;

import com.nikego.skycapitals.services.IUserTransaction;
import org.springframework.stereotype.Service;

@Service
public class UserTransaction implements IUserTransaction {
    private final CardServiceImp cardServiceImp;

    public UserTransaction(CardServiceImp cardServiceImp) {
        this.cardServiceImp = cardServiceImp;
    }

    /*1.проверка баланса, пополнение и списание счетов, возврат статуса*/
    /*перевод с карты на карту*/
    @Override
    public boolean transferMoney(Integer numberCardSender, Integer numberCardRecipient, int sum) {
        try {
            Card cardSender = cardServiceImp.read(numberCardSender);
            Card cardRecipient = cardServiceImp.read(numberCardRecipient);
            if (cardSender.getBalance() >= sum) {
                cardSender.setBalance(cardSender.getBalance() - sum);
                cardRecipient.setBalance(cardRecipient.getBalance() + sum);
                return cardServiceImp.update(cardSender, cardSender.getNumberCard()) &&
                        cardServiceImp.update(cardRecipient, cardRecipient.getNumberCard());
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}