package com.nikego.skycapitals.controllers;

import com.nikego.skycapitals.models.ScoreServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.AbstractMap;
import java.util.Optional;

public class ScoreController {
    private ScoreServiceImp scoreServiceImp;

    public ScoreController(ScoreServiceImp scoreServiceImp) {
        this.scoreServiceImp = scoreServiceImp;
    }

    @PostMapping(value = "/createScore/{ostOffice}")
    @ResponseBody
    private ResponseEntity<?> createScore(@RequestBody String ostOffice){
        Optional<?> numberScore = scoreServiceImp.createScore(ostOffice);
        return numberScore.isPresent()
               ? ResponseEntity.status(HttpStatus.OK).body(new AbstractMap.SimpleEntry<>("СтатусОперации:","Успешно создано!\n"+numberScore))
               : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AbstractMap.SimpleEntry<>("СтатусОперации:","Ошибка!"));
    }
}
