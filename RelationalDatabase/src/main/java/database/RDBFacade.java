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
    public void clearDB() throws Exception{
        this.commandDao.clear();
        this.userDao.clear();
        this.gameDao.clear();
    }

    @Override
    public void backupGame(Game game) throws Exception{

    }

    @Override
    public void addCommand(Game game, GeneralCommand command) throws Exception{

    }

    @Override
    public void addUser(User user) throws Exception{

    }

    @Override
    public void createGame(Game game) throws Exception{

    }

    @Override
    public void endGame(Game game) throws Exception{

    }

    @Override
    public List<User> getUsers()throws Exception {
        return null;
    }

    @Override
    public Map<String, GeneralCommand> getCommands()throws Exception {
        return null;
    }

    @Override
    public Map<String, Game> getGames()throws Exception {
        return null;
    }
}
