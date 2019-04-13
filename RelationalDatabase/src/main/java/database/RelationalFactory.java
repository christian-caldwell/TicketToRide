package database;

import externalInterfaces.DBFacade;
import externalInterfaces.DBFactory;

public class RelationalFactory implements DBFactory {
    @Override
    public DBFacade getFacade() throws Exception {
        return new RDBFacade();
    }
}
