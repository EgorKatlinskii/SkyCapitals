package com.nikego.skycapitals.models;

import com.nikego.skycapitals.repository.ScoreRepository;
import com.nikego.skycapitals.repository.UserRepository;
import com.nikego.skycapitals.services.ScoreService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreServiceImp implements ScoreService {
    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;
    private final static int MININTNUMBER=  20000000;
    private final static int MAXINTNUMBER = 21474836;

    public ScoreServiceImp(ScoreRepository scoreRepository, UserRepository userRepository) {
        this.scoreRepository = scoreRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Integer> createScore(String ostOffice){
        try{
            final User user =userRepository.findByOstOffice(ostOffice);
            final Score score = new Score();
            int randomNumber;
            /*зацикливание*/
            while(true){
                randomNumber = (int)(Math.random() * ((MAXINTNUMBER - MININTNUMBER) + 1)) + MININTNUMBER;
                if(!scoreRepository.existsByScoreNumber(randomNumber)){
                    score.setUserId(user.getUserId());
                    score.setScoreNumber(randomNumber);
                    scoreRepository.save(score);
                    return Optional.of(randomNumber);
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
