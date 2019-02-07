package view.activityInterface;

import server.GeneralCommand;

public interface IGameBoard {
    /**
     * This is called from a player's device when they perform a move
     * @param g
     */
    void pushCommand(GeneralCommand g);

    /**
     * This is called on every other player's device when a move is performed so that the game board
     * gets updated
     * @param g
     * @return
     */
    GeneralCommand pullCommand(GeneralCommand g);
}