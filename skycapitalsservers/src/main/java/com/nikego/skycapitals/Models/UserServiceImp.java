package com.nikego.skycapitals.Models;

import com.nikego.skycapitals.Repository.UserRepository;
import com.nikego.skycapitals.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

//    private UserServiceImp(UserRepository userRepository){
//        this.userRepository=userRepository;
//    }
    @Override
    public void create(User client) {
        userRepository.save(client);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public User read(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public boolean update(User userAccount, int id) {
        if (userRepository.existsById(id)) {
            userAccount.setUserId(id);
            userRepository.save(userAccount);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}