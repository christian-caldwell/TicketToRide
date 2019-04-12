package dao;

import model.User;
import java.sql.*;

public class UserDao {
    Database db = new Database();

    public UserDao() throws Exception{
        try {
            db.openConnection();
            db.createUserTable();
            db.closeConnection(true);
        } catch (Exception e) {
            throw new Exception("openConnection failed", e);
        }
    }
    /**
     * adds one user to database
     * @param u the user you want to add
     */
    public boolean add(User u) {
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
     * deletes one user specified by the parameter
     * @param u the user you want to delete
     */
    public void delete(User u) {
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
