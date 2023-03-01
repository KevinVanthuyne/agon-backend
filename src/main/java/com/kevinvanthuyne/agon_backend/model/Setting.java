package com.kevinvanthuyne.agon_backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "settings")
public class Setting {
    @Id
    private String key;
    private String value;

    public Setting(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Setting() {
        this("", "");
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
