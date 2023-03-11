package com.kevinvanthuyne.agon_backend.dto;

import com.kevinvanthuyne.agon_backend.model.User;

public record UserDto(String id,
                      String name,
                      String initials,
                      String password) implements ModelBuildable<User> {
    public UserDto(User user) {
        this(user.getId(), user.getName(), user.getInitials(), user.getPassword());
    }

    public UserDto() {
        this("", "", "", null);
    }

    @Override
    public User buildModel() {
        return new User(id, name, initials, password);
    }
}
