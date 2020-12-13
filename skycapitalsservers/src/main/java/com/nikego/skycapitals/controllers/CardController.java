package com.nikego.skycapitals.controllers;

import com.nikego.skycapitals.models.Card;
import com.nikego.skycapitals.services.CardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;

@RestController
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(value="/readCard/{id}",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> read(@PathVariable(name = "id") Long id){
        final Card card=cardService.read(id);
        return card != null
                ? ResponseEntity.status(HttpStatus.OK).body(new AbstractMap.SimpleEntry<>("Карта:",card))
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/update/card/{id}",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Card card,@PathVariable(name = "id") int id){
       return cardService.update(card,id)
               ? ResponseEntity.status(HttpStatus.OK).
               body(new AbstractMap.SimpleEntry<>("СтатусОперации:","Успешно обновлено!"))
               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/create/card/{nameString}/{scoreNumber}")
    @ResponseBody
    public ResponseEntity<?> create(@PathVariable (name = "nameString") String nameCard,
                                    @PathVariable(name="scoreNumber") int scoreNumber){
        try{
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            final Card card = (Card)(cardService.create(nameCard,scoreNumber).get());
            return ResponseEntity.status(HttpStatus.OK).body(card);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new AbstractMap.SimpleEntry<>("СтатусОперации:", e.getMessage()));
        }
    }

}
