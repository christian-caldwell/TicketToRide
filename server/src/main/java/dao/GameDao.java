package dao;

import java.sql.*;

import models.data.Game;

public class GameDao {
    Database db = new Database();

    public GameDao() throws Exception{
        try {
            db.openConnection();
            db.createUserTable();
            db.closeConnection(true);
        } catch (Exception e) {
            throw new Exception("openConnection failed", e);
        }
    }

    /**
     * adds one game to database
     * @param game the game you want to add
     */
    public boolean add(Game game) {
        boolean added = false;
        try {
            db.openConnection();
            PreparedStatement ps = null;
            ps = db.getConn().prepareStatement("INSERT INTO User (Username, Password, Email, FirstName, LastName, Gender, PersonID) VALUES(?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1,u.userName);
            ps.setString(2,u.password);
            ps.setString(3,u.email);
            ps.setString(4,u.firstName);
            ps.setString(5,u.lastName);
            ps.setString(6,u.gender);
            ps.setString(7,u.personID);
            ps.executeUpdate();
            added = true;
        }
        catch (Exception e) {
            System.out.println("Failed to add user to User table.");
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
     * deletes one game specified by the parameter
     * @param game the game you want to delete
     */
    public void delete(Game game) {
        try {
            db.openConnection();
            PreparedStatement ps = null;
            ps = db.getConn().prepareStatement("DELETE FROM User " +
                    "WHERE" +
                    " PersonID = ?;");
            ps.setString(1, u.personID);
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
     * deletes all the information in the User table
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
     * @param gameName the user you want to see in the database
     * @return information about the user specified by parameter
     */
    public Game get(String gameName) {
        Game myGame = new Game();
        try {
            db.openConnection();
            PreparedStatement ps = null;
            ps = db.getConn().prepareStatement("SELECT * FROM Game " +
                    "WHERE" +
                    " Game = ?;");
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
