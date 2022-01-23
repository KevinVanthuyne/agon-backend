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

    public List<Game> getAllCurrentAndPassedGames() {
        return gameDao.findAllByStartDateBefore(LocalDate.now());
    }

    public void setStartDates(LocalDate startDate) {
        // Set start date of the first game
        Game firstGame = gameDao.findFirstByOrderByIdAsc();
        firstGame.setStartDate(startDate);
        gameDao.save(firstGame);

        // Update all games to be active for a month, starting from the first game
        List<Game> games = gameDao.findAllByOrderByIdAsc();
        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);

            // Set the start date to one month after the previous game
            if (i > 0) {
                Game previousGame = games.get(i - 1);
                LocalDate oneMonthLater = previousGame.getStartDate().plusMonths(1);
                game.setStartDate(oneMonthLater);
            }
        }

        gameDao.saveAll(games);
    }

    public Optional<Game> getActiveGame() {
        LocalDate now = LocalDate.now();
        // Find the active game of the current month
        return gameDao.findFirstByStartDateBetween(now.withDayOfMonth(1), now.withDayOfMonth(now.lengthOfMonth()));
    }

    public Game updateGame(Game updatedGame) {
        return gameDao.save(updatedGame);
    }
}
