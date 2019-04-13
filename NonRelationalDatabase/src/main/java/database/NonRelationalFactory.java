package database;

import externalInterfaces.DBFacade;
import externalInterfaces.DBFactory;

public class NonRelationalFactory implements DBFactory {
    @Override
    public DBFacade getFacade() {
        return new NRDBFacade();
    }
}
