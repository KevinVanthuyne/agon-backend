package com.kevinvanthuyne.agon_backend.service;

import com.kevinvanthuyne.agon_backend.dao.UserDao;
import com.kevinvanthuyne.agon_backend.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
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
        return userDao.findByNameIgnoreCase(name);
    }

    public List<User> getAllUsers() {
        return userDao.findAllBy();
    }

    /**
     * Retrieves the user from the database if there is one already with the given name, or creates a new user.
     */
    public User getOrCreateUser(String username) {
        Optional<User> userOpt = getUserByName(username);
        if (userOpt.isPresent()) return userOpt.get();

        User user = addUser(new User(username));
        LOGGER.info("New user added: {}", user);
        return user;
    }
}
