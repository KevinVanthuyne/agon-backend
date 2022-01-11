package com.kevinvanthuyne.scored_backend.service;

import com.kevinvanthuyne.scored_backend.dao.GameDao;
import com.kevinvanthuyne.scored_backend.model.Game;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
        gameDao.save(new Game(1, "Monster Bash")); // TODO temporary test data
    }

    public Optional<Game> getGame(int id) {
        return gameDao.findById(id);
    }

    public Game addGame(Game game) {
        return gameDao.save(game);
    }

    public List<Game> getAll() {
        return gameDao.findAll();
    }

    public void setStartDates(LocalDate startDate) {
        // Set start date of the first game
        Game firstGame = gameDao.findFirstOrderByIdDesc();
        firstGame.setStartDate(startDate);
        gameDao.save(firstGame);

        // Update all games to be active for a month, starting from the first game
        // TODO
    }
}
