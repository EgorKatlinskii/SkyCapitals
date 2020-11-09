package com.nikego.skycapitals.repository;


import com.nikego.skycapitals.models.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository  extends JpaRepository<User, Integer> {

}
