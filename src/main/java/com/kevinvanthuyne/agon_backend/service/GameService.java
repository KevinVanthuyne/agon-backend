package com.kevinvanthuyne.agon_backend.service;

import com.kevinvanthuyne.agon_backend.dao.GameDao;
import com.kevinvanthuyne.agon_backend.model.Game;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public Optional<Game> getGame(int id) {
        return gameDao.findById(id);
    }

    public Game addGame(Game game) {
        return gameDao.save(game);
    }

    public List<Game> getAll() {
        return gameDao.findAllBy();
    }

    public List<Game> getAllOrdered() {
        return gameDao.findAllByOrderByIdAsc();
    }

    public void deleteGame(int id) {
        getGame(id).ifPresent((gameDao::delete));
    }

    public Game updateGame(Game updatedGame) {
        return gameDao.save(updatedGame);
    }
}
