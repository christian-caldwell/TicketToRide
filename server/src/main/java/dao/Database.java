package dao;

import java.sql.*;

public class Database {

    private Connection conn;

    static {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
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
            } finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws Exception {
        try {
            final String CONNECTION_URL = "jdbc:sqlite:ben_and_seths_awesome_database.db";

            // Open a database connection
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new Exception("openConnection failed", e);
        }
    }

    public void closeConnection(boolean commit) throws Exception {
        try {
            if (commit) {
                conn.commit();
            } else {
                conn.rollback();
            }

            conn.close();
            conn = null;
        } catch (SQLException e) {
            throw new Exception("closeConnection failed", e);
        }
    }

    public void createGameTable() {

        try {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();

                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `Game` (\n" +
                        "`gameID` INT\n" +
                        "`gameName` TEXT NOT NULL,\n" +
                        "PRIMARY KEY (gameID)\n" +
                        ");");
            } finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createUserTable() {
        try {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();

                stmt.executeUpdate("CREATE TABLE IF NOT EXIST `User` (\n" +
                        "`userName` TEXT NOT NULL,\n" +
                        "`password` TEXT NOT NULL,\n" +
                        "FOREIGN KEY (gameID),\n" +
                        "PRIMARY KEY (userName)");
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCommandTable() {
        try {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();

                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `Command` (\n" +
                        "`commandID` INT,\n" +
                        "`id` INT NOT NULL,\n" +
                        "`commandText` TEXT,\n" +
                        "FOREIGN KEY(gameID),\n" +
                        "PRIMARY KEY (commandID)\n" +
                        ");");
            } finally {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}


