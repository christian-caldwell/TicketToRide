package server;

import java.lang.reflect.Method;

import models.command.CommandResult;
import models.command.ICommandExecuter;
import models.data.Result;


public class GeneralCommand implements ICommandExecuter {
/*
    ---README---
    This code is very good, exactly what we need. The TAs seemed to be
    uncertain about whether this needs to its own class or whether this
    can be done withing the Server class. Please consult a TA about this
    specific class.

*/
    private String _className;
    private String _methodName;
    private Class<?>[] _paramTypes;
    private Object[] _paramValues;
    private ClientProxy clientProxy;

    public GeneralCommand(String className, String methodName,
                          Class<?>[] paramTypes, Object[] paramValues) {
        _className = className;
        _methodName = methodName;
        _paramTypes = paramTypes;
        _paramValues = paramValues;
        clientProxy = new ClientProxy();
    }

    @Override
    public CommandResult exec() {
        CommandResult result = new CommandResult();
        try {

            Class<?> receiver = Class.forName(_className);
            Method method = receiver.getMethod(_methodName, _paramTypes);
            //FIXME: It's possible that the fist param of 'invoke' will need to be a static class
            result.setData(method.invoke(receiver, _paramValues));
            result.setSuccesful(true);

        }
        catch (Exception e) {
            e.printStackTrace();
            result.setErrorMessage(e.toString());
            result.setSuccesful(false);
        }
        return result;

    }

}
