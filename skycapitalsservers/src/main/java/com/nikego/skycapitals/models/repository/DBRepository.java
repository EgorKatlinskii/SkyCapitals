package com.nikego.skycapitals.models.repository;

import com.nikego.skycapitals.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DBRepository extends JpaRepository<User, Integer> {

}