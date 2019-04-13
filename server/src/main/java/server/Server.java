package server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import dao.CommandDao;
import dao.Database;
import dao.GameDao;
import dao.UserDao;
import models.command.Command;
import models.data.Game;
import models.data.User;

public class Server {


    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;


    private void run(String portNumber) {


        System.out.println("Initializing HTTP Server");
        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);

        System.out.println("Creating contexts");
        server.createContext("/", new CommandHandler());
        System.out.println("Starting server");
        server.start();
        System.out.println("Server started");
    }

    // "main" method for the com.example.testingpurposes.server program
    // "args" should contain one command-line argument, which is the port number
    // on which the com.example.testingpurposes.server should accept incoming com.example.testingpurposes.client connections.
    public static void main(String[] args) {
        String portNumber = "8080";
        new Server().run(portNumber);

        try {
            GameDao gamedao = new GameDao();
            CommandDao commandDao = new CommandDao();
            UserDao userDao = new UserDao();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long millis = System.currentTimeMillis();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        System.out.println("Started on port: " + portNumber);
    }
}



