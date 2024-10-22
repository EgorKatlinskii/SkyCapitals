
package com.nikego.skycapitals.controllers;

import com.nikego.skycapitals.models.UserTransaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;

@RestController
public class UserTransactionControler {
    private final UserTransaction userTransaction;

    public UserTransactionControler(UserTransaction userTransaction) {
        this.userTransaction = userTransaction;
    }

    @PostMapping(value = "/by_card_number/{numberCardSender}/{numberCardRecipient}/{sum}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> transferMoney(@PathVariable Long numberCardSender,@PathVariable Long numberCardRecipient,@PathVariable int sum){
        return userTransaction.transferMoney(numberCardSender, numberCardRecipient, sum)
                ?ResponseEntity.status(HttpStatus.OK).body(new AbstractMap.SimpleEntry<>
                ("Статус операции:","Операция успешно выполнена!"))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AbstractMap.SimpleEntry<>
                ("Статус операции:","Операция недоступна!"));
    }
}

