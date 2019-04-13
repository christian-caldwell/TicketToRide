package database;

import java.sql.*;
import java.util.ArrayList;

public class CommandDao {
    Database db = new Database();

    public CommandDao() throws Exception{
        try {
            db.openConnection();
            db.createCommandTable();
            db.closeConnection(true);
        } catch (Exception e) {
            throw new Exception("openConnection failed", e);
        }
    }

    /**
     * adds one command to database
     * @param commandText the command you want to add
     */
    public boolean add(String commandText) {
        boolean added = false;
        try {
            db.openConnection();
            PreparedStatement ps = null;
            ps = db.getConn().prepareStatement("INSERT INTO Command (commandID, id, commandText) VALUES(?, ?, ?);");
            ps.setString(1,"");
            ps.setString(2,"");
            ps.setString(3,commandText);
            ps.executeUpdate();
            added = true;
        }
        catch (Exception e) {
            System.out.println("Failed to add command to Command table.");
        }
        finally {
            try {
                db.closeConnection(true);
            }
            catch (Exception e ) {
                e.printStackTrace();
            }
        }
        return added;
    }

    /**
     * deletes one user specified by the parameter
     * @param command the user you want to delete
     */
    public void delete(String command) { //TODO: i think this needs to change to be deleted by game instead of commandText...
        try {
            db.openConnection();
            PreparedStatement ps = null;
            ps = db.getConn().prepareStatement("DELETE FROM Command " +
                    "WHERE" +
                    " commandText = ?;");
            ps.setString(1, command);
            ps.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Failed to delete command from Command table.");
        }
        finally {
            try {
                db.closeConnection(true);
            }
            catch (Exception e ) {
                e.printStackTrace();
            }
        }
    }

    /**
     * deletes all the information in the Command table
     */
    public void clear () {
        try {
            db.openConnection();
            Statement stmt = db.getConn().createStatement();
            stmt.executeUpdate("DELETE FROM Command ");
        }
        catch (Exception e) {
            System.out.println("Failed to clear Command table.");
        }
        finally {
            try {
                db.closeConnection(true);
            }
            catch (Exception e ) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> getAll() {
        ArrayList<String> commands = new ArrayList<>();
        try {
            db.openConnection();
            ResultSet rs = db.getConn().prepareStatement("SELECT * FROM Command;").executeQuery();
            commands.add(rs.getString("commandText"));
        }
        catch (Exception e) {
            System.out.println("Could not get Commands from Command table.");
        }
        finally {
            try {
                db.closeConnection(true);
            }
            catch (Exception e ) {
                e.printStackTrace();
            }
        }
        return commands;
    }
}
