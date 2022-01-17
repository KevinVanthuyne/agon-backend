package com.kevinvanthuyne.scored_backend.dto;

import com.kevinvanthuyne.scored_backend.model.User;

public class UserDto implements ModelBuildable<User> {
    private final String id;
    private final String name;
    private final String initials;

    public UserDto(String id, String name, String initials) {
        this.id = id;
        this.name = name;
        this.initials = initials;
    }

    public UserDto(User user) {
        this(user.getId(), user.getName(), user.getInitials());
    }

    public UserDto() {
        this("", "", "");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInitials() {
        return initials;
    }

    @Override
    public User buildModel() {
        return new User(id, name, initials);
    }
}
