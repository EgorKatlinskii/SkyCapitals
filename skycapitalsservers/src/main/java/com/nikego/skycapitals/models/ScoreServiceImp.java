package com.nikego.skycapitals.models;

import com.nikego.skycapitals.repository.ScoreRepository;
import com.nikego.skycapitals.repository.UserRepository;
import com.nikego.skycapitals.services.ScoreService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreServiceImp implements ScoreService {
    private ScoreRepository scoreRepository;
    private UserRepository userRepository;
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
            score.setUser(user);
            /*добавление номера путем извлечения у user*/
            score.setUserId(user.getUserId());
            while(true){
                int randomNumber = MININTNUMBER + (int) (Math.random() * MAXINTNUMBER);
                if(!scoreRepository.findByNumberScore(randomNumber)){
                    score.setScoreNumber(randomNumber);
                    scoreRepository.save(score);
                    return Optional.of(randomNumber);
                }
            }
        }
        catch (Exception e){
            return Optional.empty();
        }
    }
}
