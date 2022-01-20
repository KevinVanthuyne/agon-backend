package com.kevinvanthuyne.scored_backend.dto;

import com.kevinvanthuyne.scored_backend.model.User;

public record UserDto(String id,
                      String name,
                      String initials) implements ModelBuildable<User> {
    public UserDto(User user) {
        this(user.getId(), user.getName(), user.getInitials());
    }

    public UserDto() {
        this("", "", "");
    }

    @Override
    public User buildModel() {
        return new User(id, name, initials);
    }
}
