package com.nikego.skycapitals.models;

import com.nikego.skycapitals.repository.DBRepository;
import com.nikego.skycapitals.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final DBRepository DBRepository;

    public UserServiceImpl(DBRepository DBRepository) {
        this.DBRepository = DBRepository;
    }

    @Override
    public List<User> readAll() {
        return DBRepository.findAll();
    }

    @Override
    public void create(User user) throws Exception{ DBRepository.save(user); }

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