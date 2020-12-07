package com.nikego.skycapitals.models;

import com.nikego.skycapitals.repository.CardRepository;
import com.nikego.skycapitals.services.CardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImp implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImp(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card read(int id ){
        return cardRepository.getOne(id);
    }

    @Override
    public boolean update(Card card,Integer numberCard){
        if(cardRepository.existsById(numberCard)){
            card.setNumberCard(numberCard);
            cardRepository.save(card);
            return true;
        }
        return false;
    }
}
