package externalInterfaces;

public interface DBFactory {
    public DBFacade getFacade() throws Exception;
}
