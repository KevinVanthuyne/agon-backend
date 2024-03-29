package com.kevinvanthuyne.agon_backend.dao;

import com.kevinvanthuyne.agon_backend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudRepository<User, String> {
    List<User> findAllBy();

    Optional<User> findByNameIgnoreCase(String name);
}
