package com.kevinvanthuyne.agon_backend.dao;

import com.kevinvanthuyne.agon_backend.model.Setting;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SettingDao extends CrudRepository<Setting, String> {
    Optional<Setting> findByKey(String key);
}
