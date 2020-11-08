package com.nikego.skycapitals.Models;

import com.nikego.skycapitals.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements com.nikego.skycapitals.services.UserService {
    private final UserRepository userRepository;

    private UserServiceImp(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public void create(UserAccount client) {
        userRepository.save(client);
    }

    @Override
    public List<UserAccount> readAll() {
        return userRepository.findAll();
    }

    @Override
    public UserAccount read(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public boolean update(UserAccount userAccount, int id) {
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