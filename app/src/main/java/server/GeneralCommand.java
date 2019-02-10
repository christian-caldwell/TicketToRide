package server;

import java.io.Serializable;
import java.lang.reflect.Method;

import models.command.CommandResult;
import models.command.ICommandExecuter;


public class GeneralCommand implements ICommandExecuter, Serializable {
/*
    ---README---
    This code is very good, exactly what we need. The TAs seemed to be
    uncertain about whether this needs to its own class or whether this
    can be done withing the Server class. Please consult a TA about this
    specific class.

*/

    public void set_className(String _className) {
        this._className = _className;
    }

    public void set_methodName(String _methodName) {
        this._methodName = _methodName;
    }

    public void set_paramTypes(Class<?>[] _paramTypes) {
        this._paramTypes = _paramTypes;
    }

    public void set_paramValues(Object[] _paramValues) {
        this._paramValues = _paramValues;
    }

    public String get_className() {
        return _className;
    }

    public String get_methodName() {
        return _methodName;
    }

    public Class<?>[] get_paramTypes() {
        return _paramTypes;
    }

    public Object[] get_paramValues() {
        return _paramValues;
    }

    public GeneralCommand() {
    }

    public String _className;
    public String _methodName;
    public Class<?>[] _paramTypes;
    public Object[] _paramValues;


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

    @Override
    public String toString() {
        return "{GeneralCommand [className=" + _className + ", methodName=" + _methodName
                + ", paramTypes=" + _paramTypes.toString() + ", paramValues=" + _paramValues + "]}";
    }

}
