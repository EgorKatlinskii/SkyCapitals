package com.nikego.skycapitals.models;

import com.nikego.skycapitals.repository.CardRepository;
import com.nikego.skycapitals.repository.ScoreRepository;
import com.nikego.skycapitals.services.CardService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImp implements CardService {
    private final CardRepository cardRepository;
    private final ScoreRepository scoreRepository;
    private final static long MININTNUMBER=  100000000000000L;
    private final static long MAXINTNUMBER = 999999999999999L;
    private final static int MINNUMBERPASSWORD = 1000;
    private final  static int MAXNUMBERPASSWORD = 9999;

    public CardServiceImp(CardRepository cardRepository, ScoreRepository scoreRepository) {
        this.cardRepository = cardRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public Card read(int id ){
        return cardRepository.getOne(id);
    }

    @Override
    public boolean update(Card card,long numberCard){
        if(cardRepository.existsByNumberCard(numberCard)){
            card.setNumberCard(numberCard);
            cardRepository.save(card);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Card> create(String nameCard,int scoreNumber){
        try{
            final Score score=scoreRepository.findByscoreNumber(scoreNumber);
            final Card card=new Card();
            long randomNumberCard;
            int randomPassword = (int)(Math.random() * ((MAXNUMBERPASSWORD - MINNUMBERPASSWORD) + 1)) + MINNUMBERPASSWORD;
            while(true){
                randomNumberCard = (long)(Math.random() * ((MAXINTNUMBER - MININTNUMBER) + 1)) + MININTNUMBER;
                if(!cardRepository.existsByNumberCard(randomNumberCard)){
                    card.setNameCard(nameCard);
                    card.setNumberCard(randomNumberCard);
                    card.setBalance(0);
                    card.setPassword(randomPassword);
                    card.setNumberScore(score.getScoreNumber());
                    cardRepository.save(card);
                    return Optional.of(card);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
