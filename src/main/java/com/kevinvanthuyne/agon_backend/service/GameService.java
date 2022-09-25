package com.kevinvanthuyne.agon_backend.service;

import com.kevinvanthuyne.agon_backend.dao.GameDao;
import com.kevinvanthuyne.agon_backend.model.Game;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private static final Period GAME_PERIOD = Period.ofMonths(1);
    private final GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public Optional<Game> getGame(int id) {
        return gameDao.findById(id);
    }

    public Game addGame(Game game) {
        Optional<Game> lastGameOpt = gameDao.findFirstByOrderByIdDesc();
        lastGameOpt.ifPresent(value -> game.setStartDate(value.getStartDate().plus(GAME_PERIOD)));
        return gameDao.save(game);
    }

    public List<Game> getAll() {
        return gameDao.findAllBy();
    }

    public List<Game> getAllOrdered() {
        return gameDao.findAllByOrderByIdAsc();
    }

    public List<Game> getAllCurrentAndPassedGames() {
        return gameDao.findAllByStartDateLessThanEqual(LocalDate.now());
    }

    public void deleteGame(int id) {
        getGame(id).ifPresent((gameDao::delete));
    }

    public void setStartDates(LocalDate startDate) {
        // Set start date of the first game
        Game firstGame = gameDao.findFirstByOrderByIdAsc();
        firstGame.setStartDate(startDate);
        gameDao.save(firstGame);

        // Update all games to be active for the GAME_PERIOD, starting from the first game
        List<Game> games = gameDao.findAllByOrderByIdAsc();
        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);

            // Set the start date to the given period after the previous game
            if (i > 0) {
                Game previousGame = games.get(i - 1);
                LocalDate nextStartDate = previousGame.getStartDate().plus(GAME_PERIOD);
                game.setStartDate(nextStartDate);
            }
        }

        gameDao.saveAll(games);
    }

    public Optional<Game> getActiveGame() {
        LocalDate now = LocalDate.now();
        List<Game> gamesByStartDate = gameDao.findAllByOrderByStartDateAsc();

        for (int i = 0; i < gamesByStartDate.size(); i++) {
            Game game = gamesByStartDate.get(i);

            // If the first game's start date has not been reached yet, there's no active game
            if (i == 0 && game.getStartDate().isAfter(now)) {
                return Optional.empty();
            }

            // Special handling for the last game since there is no next game to compare it with
            if (i == gamesByStartDate.size() - 1) {
                if (game.getStartDate().plus(GAME_PERIOD).isAfter(now)) {
                    return Optional.of(game);
                }
                return Optional.empty();
            }

            // If the current date sits between this game's start date and the next, this is the active game
            Game nextGame = gamesByStartDate.get(i + 1);
            if (game.getStartDate().isEqual(now) || game.getStartDate().isBefore(now) && nextGame.getStartDate().isAfter(now)) {
                return Optional.of(game);
            }
        }
        return Optional.empty();
    }

    public Game updateGame(Game updatedGame) {
        return gameDao.save(updatedGame);
    }
}
