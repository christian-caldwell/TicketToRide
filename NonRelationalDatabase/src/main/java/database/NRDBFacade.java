package database;

import java.util.List;
import java.util.Map;

import externalClasses.Game;
import externalClasses.GeneralCommand;
import externalClasses.User;
import externalInterfaces.DBFacade;

class NRDBFacade implements DBFacade {

    private CommandDao commandDao;
    private UserDao userDao;
    private GameDao gameDao;

    public NRDBFacade() {

    }
    @Override
    public void clearDB() {

    }

    @Override
    public void backupGame(Game game) {

    }

    @Override
    public void addCommand(Game game, GeneralCommand command) {

    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void startGame(Game game) {

    }

    @Override
    public void endGame(Game game) {

    }

    @Override
    public Map<String, Game> getLobby() {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public Map<String, GeneralCommand> getCommands() {
        return null;
    }

    @Override
    public Map<String, Game> getGames() {
        return null;
    }
}
