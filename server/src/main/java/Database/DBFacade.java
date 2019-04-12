package Database;

import models.command.Command;
import models.data.Game;
import models.data.User;

public interface DBFacade {
    public void clearDB();
    public void backupGame(Game game);
    public void addCommand(Game game, Command command);
    public void addUser(User user);
    public void startGame(Game game);
    public void endGame(Game game);

    public 
}

//This should be pretty simple?
