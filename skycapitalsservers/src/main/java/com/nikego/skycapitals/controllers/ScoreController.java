package com.nikego.skycapitals.controllers;

import com.nikego.skycapitals.models.ScoreServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;
import java.util.Optional;

@RestController
public class ScoreController {
    private final ScoreServiceImp scoreServiceImp;

    @Autowired
    public ScoreController(ScoreServiceImp scoreServiceImp) {
        this.scoreServiceImp = scoreServiceImp;
    }

    @PostMapping(value = "/create/score/{ostOffice}",produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> createScore(@PathVariable(name="ostOffice") String ostOffice){
        Optional<?> numberScore = scoreServiceImp.createScore(ostOffice);
        return numberScore.isPresent()
               ? ResponseEntity.status(HttpStatus.OK).body(new AbstractMap.SimpleEntry<>("numberScore:",numberScore))
               : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AbstractMap.SimpleEntry<>("СтатусОперации:","Ошибка!"));
    }
}
