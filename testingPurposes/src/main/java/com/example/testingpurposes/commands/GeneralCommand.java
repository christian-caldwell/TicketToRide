package com.example.testingpurposes.commands;

public class GeneralCommand implements ICommand{
    private String _className;
    private String _methodName;
    private Class<?>[] _paramTypes;
    private Object[] _paramValues;

    //constructor
    public GeneralCommand(String className, String methodName, Class<?>[] paramTypes, Object[] paramValues) {
    }

    @Override
    public Result exec() {
        return null;
    }
}
