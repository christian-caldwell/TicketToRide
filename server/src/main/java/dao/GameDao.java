package dao;

import org.apache.commons.lang3.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import models.data.Game;

public class GameDao {
    Database db = new Database();

    public GameDao() throws Exception {
        try {
            db.openConnection();
            db.createGameTable();
            db.closeConnection(true);
        } catch (Exception e) {
            throw new Exception("openConnection failed", e);
        }
    }

    /**
     * adds one game to database
     *
     * @param game the game you want to add
     */
    public boolean add(Game game) {
        boolean added = false;
        try {
            db.openConnection();
            PreparedStatement ps = null;
            ps = db.getConn().prepareStatement("INSERT INTO Game (game) VALUES(?);");
            Blob blob = toBlob(game, ps);
            ps.setBlob(1, blob);
            ps.executeUpdate();
            added = true;
        } catch (Exception e) {
            System.out.println("Failed to add game to Game table.");
        } finally {
            try {
                db.closeConnection(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return added;
    }

    /**
     * deletes one game specified by the parameter
     *
     * @param game the game you want to delete
     */
    public void delete(Game game) {
        try {
            db.openConnection();
            PreparedStatement ps = db.getConn().prepareStatement("DELETE FROM Game " +
                    "WHERE" +
                    " gameName = ?;");
            ps.setString(1, game.getGameName());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Failed to delete game from Game table.");
        } finally {
            try {
                db.closeConnection(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * deletes all the information in the Game table
     */
    public void clear() {
        try {
            db.openConnection();
            Statement stmt = db.getConn().createStatement();
            stmt.executeUpdate("DELETE FROM Game ");
        } catch (Exception e) {
            System.out.println("Failed to clear Game table.");
        } finally {
            try {
                db.closeConnection(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param gameName the game you want to see in the database
     * @return information about the game specified by parameter
     */
    public ArrayList<Game> getAllGames(String gameName) {
        ArrayList<Game> games = new ArrayList<>();
        try {
            db.openConnection();
            ResultSet rs = db.getConn().prepareStatement("SELECT * FROM Game;").executeQuery();
            while (rs.next()) {
                games.add(fromBlob(rs.getBlob("game")));
            }
        } catch (Exception e) {
            System.out.println("Could not get game from Game table.");
            return null;
        } finally {
            try {
                db.closeConnection(true);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return games;
    }

    private Blob toBlob(Game game, PreparedStatement ps) {
        try {
            Blob blob = ps.getConnection().createBlob();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = null;
            byte[] bytes = null;
            try {
                out = new ObjectOutputStream(bos);
                out.writeObject(game);
                out.flush();
                bytes = bos.toByteArray();
            } finally {
                try {
                    bos.close();
                } catch (IOException ex) {
                    System.out.print(ex);
                }
            }
            blob.setBytes(1, bytes);
            return blob;
        } catch (Exception e) {
            System.out.print("Failed to create blob: " + e);
            return null;
        }
    }

    private Game fromBlob(Blob blob) {
        ObjectInput in = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(blob.getBytes(1,1));
            in = new ObjectInputStream(bis);
            Object o = in.readObject();

        } catch (Exception e) {
            System.out.print("Failed to convert to game from blob: " + e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                System.out.print("Failed to convert to game from blob: " + e);
            }
        }
        return null;
    }
}
