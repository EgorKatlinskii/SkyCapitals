package com.nikego.skycapitals.models;

import com.nikego.skycapitals.repository.UserRepository;
import com.nikego.skycapitals.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository DBRepository;

    public UserServiceImpl(UserRepository DBRepository) {
        this.DBRepository = DBRepository;
    }

    @Override
    public Optional<User> accountVerification(String ostoffice, Integer password) {

        try {
            User user = DBRepository.findByOstOffice(ostoffice);
            if(user.getPassword().equals(password)){
                return Optional.of(user);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<User> readAll() {
        return DBRepository.findAll();
    }

    @Override
    public void create(User user) throws Exception {
        DBRepository.save(user);
    }

    @Override
    public User read(int id) {
        return DBRepository.getOne(id);
    }

    @Override
    public boolean update(User userAccount, int id) {
        if (DBRepository.existsById(id)) {
            userAccount.setUserId(id);
            DBRepository.save(userAccount);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (DBRepository.existsById(id)) {
            DBRepository.deleteById(id);
            return true;
        }
        return false;
    }
}