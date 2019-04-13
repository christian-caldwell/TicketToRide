package database;

import java.util.List;
import java.util.Map;

import externalClasses.Game;
import externalClasses.GeneralCommand;
import externalClasses.User;
import externalInterfaces.DBFacade;

class RDBFacade implements DBFacade {

    private CommandDao commandDao;
    private UserDao userDao;
    private GameDao gameDao;

    public RDBFacade() throws Exception{
        this.commandDao = new CommandDao();
        this.userDao = new UserDao();
        this.gameDao = new GameDao();
    }

    @Override
    public void clearDB() {
        this.commandDao.clear();
        this.userDao.clear();
        this.gameDao.clear();
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
