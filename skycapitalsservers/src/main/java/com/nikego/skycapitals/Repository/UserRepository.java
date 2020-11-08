package com.nikego.skycapitals.Repository;


import com.nikego.skycapitals.Models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserAccount, Integer> {

}
