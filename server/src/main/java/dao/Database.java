package dao;

import java.sql.*;

public class Database {

    private Connection conn;
    static {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConn() {
        return conn;
    }

    public void removeTables(Statement stmt) {
        try {
            try {

                stmt.executeUpdate("drop table Game;\n" +
                        "drop table User;\n" +
                        "drop table Command;");
            }
            finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void openConnection() throws Exception {
        try {
            final String CONNECTION_URL = "jdbc:sqlite:Database.sqlite";

            // Open a database connection
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new Exception("openConnection failed", e);
        }
    }

    public void closeConnection(boolean commit) throws Exception {
        try {
            if (commit) {
                conn.commit();
            }
            else {
                conn.rollback();
            }

            conn.close();
            conn = null;
        }
        catch (SQLException e) {
            throw new Exception("closeConnection failed", e);
        }
    }
    public void createEventTable() {

        try {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();

                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `Game` (\n" +
                        "`gameID` INT\n" +
                        "`gameName` TEXT NOT NULL,\n" +
                        "PRIMARY KEY (gameID)\n" +
                        ");");
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `Event` (\n" +
                        "\t`EventID`\tTEXT NOT NULL UNIQUE,\n" +
                        "\t`Descendant`\tTEXT,\n" +
                        "\t`Person`\tTEXT,\n" +
                        "\t`Latitude`\tREAL,\n" +
                        "\t`Longitude`\tREAL,\n" +
                        "\t`Country`\tTEXT,\n" +
                        "\t`City`\tTEXT,\n" +
                        "\t`EventType`\tTEXT,\n" +
                        "\t`Year`\tTEXT\n" +
                        ");");
            }
            finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void createAccessTokenTable() {

        try {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();


                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS AccessToken (" +
                        "Token TEXT UNIQUE," +
                        "Username TEXT NOT NULL," +
                        "PRIMARY KEY(Token)" +
                        ");");
                conn.commit();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void createPersonTable() {

        try {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();

                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `Person` (\n" +
                        "\t`PersonID`\tTEXT NOT NULL UNIQUE,\n" +
                        "\t`Descendant`\tTEXT NOT NULL,\n" +
                        "\t`FirstName`\tTEXT NOT NULL,\n" +
                        "\t`LastName`\tTEXT NOT NULL,\n" +
                        "\t`Gender`\tTEXT,\n" +
                        "\t`Father`\tTEXT,\n" +
                        "\t`Mother`\tTEXT,\n" +
                        "\t`Spouse`\tTEXT\n" +
                        ");");
            }
            finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void createUserTable() {

        try {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();

                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `User` (\n" +
                        "\t`Username`\tTEXT NOT NULL UNIQUE,\n" +
                        "\t`Password`\tTEXT NOT NULL,\n" +
                        "\t`Email`\tTEXT NOT NULL,\n" +
                        "\t`FirstName`\tTEXT NOT NULL,\n" +
                        "\t`LastName`\tTEXT NOT NULL,\n" +
                        "\t`Gender`\tTEXT,\n" +
                        "\t`PersonID`\tTEXT NOT NULL UNIQUE\n" +
                        ");");
            }
            finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}



