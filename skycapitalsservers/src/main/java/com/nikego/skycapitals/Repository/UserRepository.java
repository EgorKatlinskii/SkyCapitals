package com.nikego.skycapitals.Repository;


import com.nikego.skycapitals.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository  extends JpaRepository<User, Integer> {

}
