package com.nikego.skycapitals.controllers;

import com.nikego.skycapitals.models.UserTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;

@RestController
class UserTransactionControler {
    private final UserTransaction userTransaction;

    public UserTransactionControler(UserTransaction userTransaction) {
        this.userTransaction = userTransaction;
    }

    @PostMapping(value = "/by_card_number",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> transferMoney(@RequestBody Integer idSender,Integer idRecipient,int sum){
        return userTransaction.transferMoney(idSender, idRecipient, sum)
                ?ResponseEntity.status(HttpStatus.OK).body(new AbstractMap.SimpleEntry<>
                ("Статус операции:","Операция успешно выполнена!"))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AbstractMap.SimpleEntry<>
                ("Статус операции:","Операция недоступна!"));
    }


}
