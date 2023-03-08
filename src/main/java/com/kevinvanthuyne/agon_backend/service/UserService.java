package com.kevinvanthuyne.agon_backend.service;

import com.kevinvanthuyne.agon_backend.dao.UserDao;
import com.kevinvanthuyne.agon_backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User addUser(User user) {
        return userDao.save(user);
    }

    public Optional<User> getUser(String id) {
        return userDao.findById(id);
    }

    public Optional<User> getUserByName(String name) {
        return userDao.findByName(name);
    }

    public List<User> getAllUsers() {
        return userDao.findAllBy();
    }
}
