package dao;

import java.sql.*;

import models.command.Command;


public class CommandDao {
    Database db = new Database();

    public CommandDao() throws Exception{
        try {
            db.openConnection();
            db.createUserTable();
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
            ps.setString(1,u.userName);
            ps.setString(2,u.password);
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
    public void delete(String command) {
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
            System.out.println("Failed to delete user from User table.");
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
            stmt.executeUpdate("DELETE FROM User ");
        }
        catch (Exception e) {
            System.out.println("Failed to clear User table.");
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

    public String getPersonID(String username) {
        String return_string = "";
        try {
            db.openConnection();
            PreparedStatement ps = null;
            ps = db.getConn().prepareStatement("SELECT PersonID FROM User " +
                    "WHERE" +
                    " Username = ?;");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return_string = rs.getString("PersonID");
        }
        catch (Exception e) {
            System.out.println("Could not get personID in User table.");
        }
        finally {
            try {
                db.closeConnection(true);
            }
            catch (Exception e ) {
                e.printStackTrace();
            }
        }
        return return_string;
    }


    /**
     *
     * @param personID the user you want to see in the database
     * @return information about the user specified by parameter
     */
    public User get(String personID) {
        User myUser = new User();
        try {
            db.openConnection();
            PreparedStatement ps = null;
            ps = db.getConn().prepareStatement("SELECT * FROM User " +
                    "WHERE" +
                    " PersonID = ?;");
            ps.setString(1, personID);
            ResultSet rs = ps.executeQuery();
            myUser.email = rs.getString("Email");
            myUser.userName = rs.getString("Username");
            myUser.firstName = rs.getString("FirstName");
            myUser.lastName = rs.getString("LastName");
            myUser.gender = rs.getString("Gender");
            myUser.password = rs.getString("Password");
            myUser.personID = rs.getString("PersonID");
        }
        catch (Exception e) {
            System.out.println("Could not get user from User table.");
            myUser = null;
        }
        finally {
            try {
                db.closeConnection(true);
            }
            catch (Exception e ) {
                e.printStackTrace();
                myUser = null;
            }
        }
        return myUser;
    }


}
