package com.kevinvanthuyne.agon_backend.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;

    @Column(unique = true, length = 32)
    @Length(min = 3, max = 32)
    private String name;

    @Column(length = 3)
    private String initials;

    // Can be null for non-admin users
    @Column(length = 128)
    @Length(min = 8, max = 128)
    private String password;

    public User(String id, String name, String initials, String password) {
        this.id = id;
        this.name = name;
        this.initials = initials;
        this.password = password;
    }

    public User(String name) {
        this(UUID.randomUUID().toString(), name, "", null);
    }

    public User() {
        this("");
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", initials='" + initials + '\'' +
                '}';
    }
}
