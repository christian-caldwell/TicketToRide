package commands;

import models.CommandResult;

public interface ICommand {
    public CommandResult exec();
}
