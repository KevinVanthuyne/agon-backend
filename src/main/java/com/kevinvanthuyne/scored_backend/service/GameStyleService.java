package com.kevinvanthuyne.scored_backend.service;

import com.kevinvanthuyne.scored_backend.dao.GameStyleDao;
import com.kevinvanthuyne.scored_backend.model.GameStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameStyleService {
    private final GameStyleDao gameStyleDao;

    @Autowired
    public GameStyleService(GameStyleDao gameStyleDao) {
        this.gameStyleDao = gameStyleDao;
    }

    public Optional<GameStyle> getStyle(int gameId) {
        return gameStyleDao.findById(gameId);
    }

    public GameStyle updateGameStyle(GameStyle gameStyle) {
        return gameStyleDao.save(gameStyle);
    }
}
