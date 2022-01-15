package com.kevinvanthuyne.scored_backend.dao;

import com.kevinvanthuyne.scored_backend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, String> {
    List<User> findAllBy();
}
