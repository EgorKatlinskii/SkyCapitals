package com.nikego.skycapitals.repository;

import com.nikego.skycapitals.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {
    boolean existsByNameCard(String nameCard);
    boolean existsByNumberCard(long numberCard);
}
