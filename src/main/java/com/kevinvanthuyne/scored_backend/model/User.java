package com.kevinvanthuyne.scored_backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String initials;

    public User(String id, String name, String initials) {
        this.id = id;
        this.name = name;
        this.initials = initials;
    }

    public User() {
        this("", "", "");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }
}
