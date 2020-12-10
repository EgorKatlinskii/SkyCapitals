package com.nikego.skycapitals.repository;

import com.nikego.skycapitals.models.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score,Integer> {
    boolean findByscoreNumber(Integer scoreNumber);
    boolean existsByScoreNumber(Integer randomNumber);
}

