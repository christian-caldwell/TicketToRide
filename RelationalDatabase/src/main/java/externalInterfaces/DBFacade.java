package externalInterfaces;

import java.util.List;
import java.util.Map;

import externalClasses.Game;
import externalClasses.GeneralCommand;
import externalClasses.User;

public interface DBFacade {
    public void clearDB();
    public void backupGame(Game game);
    public void addCommand(Game game, GeneralCommand command);
    public void addUser(User user);
    public void startGame(Game game);
    public void endGame(Game game);

    public Map<String, Game> getLobby();
    public List<User> getUsers();
    public Map<String, GeneralCommand> getCommands();
    public Map<String, Game> getGames();
}

//This should be pretty simple?
