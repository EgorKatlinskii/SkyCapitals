package com.nikego.skycapitals.models.repository;

import com.nikego.skycapitals.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBRepository extends JpaRepository<User, Integer> {

}