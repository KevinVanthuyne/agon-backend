package com.kevinvanthuyne.agon_backend.service;

import com.kevinvanthuyne.agon_backend.dao.SettingDao;
import com.kevinvanthuyne.agon_backend.model.Setting;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettingService {
    private final SettingDao settingDao;

    public SettingService(SettingDao settingDao) {
        this.settingDao = settingDao;
    }

    public Optional<Setting> getSetting(String key) {
        return settingDao.findByKey(key);
    }
}
