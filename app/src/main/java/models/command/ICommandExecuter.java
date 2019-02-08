package models.command;

import models.data.Result;

public interface ICommandExecuter {
    public CommandResult exec();
}
