package server;

import java.lang.reflect.Method;

import models.command.CommandResult;
import models.command.ICommandExecuter;


public class GeneralCommand implements ICommandExecuter {
    @Override
    public CommandResult exec() {
        return null;
    }

/*
    ---README---
    This code is very good, exactly what we need. The TAs seemed to be
    uncertain about whether this needs to its own class or whether this
    can be done withing the Server class. Please consult a TA about this
    specific class.


    private String _className;
    private String _methodName;
    private Class<?>[] _paramTypes;
    private Object[] _paramValues;

    public GeneralCommand(String className, String methodName,
                          Class<?>[] paramTypes, Object[] paramValues) {
        _className = className;
        _methodName = methodName;
        _paramTypes = paramTypes;
        _paramValues = paramValues;
    }

    @Override
    public CommandResult exec() {
        CommandResult result = new CommandResult();
        try {
            Class<?> receiver = Class.forName(String.format("command.%s", _className));
            Method method = receiver.getMethod(_methodName, _paramTypes);
            result.data = method.invoke(null, _paramValues);
            result.success = true;
            result.errorInfo = null;
        }
        catch (Exception e) {
            e.printStackTrace();
            result.errorInfo = e.toString();
            result.success = false;
        }
        return result;
    }
    */
}
