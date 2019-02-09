package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpURLConnection;

import helper.HttpHelper;
import models.command.ICommandExecuter;
import models.data.Request;

public class CommandHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        /*
        Create commands that call the command class, which will call the serverCommands


         */
        try {
            System.out.println("inside of command.");
            /*exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            OutputStream respBody = exchange.getResponseBody();
            String word = exchange.getRequestURI().getPath().split("/")[2];
            String methodName = exchange.getRequestURI().getPath().split("/")[1];
            */
            // Extract the JSON string from the HTTP request body
            HttpHelper helper = new HttpHelper();
            String reqData = helper.httpRequestToJson(exchange);

            //Display/log the request JSON data
            System.out.println(reqData);

            Gson gson = new Gson();

            ICommandExecuter facadeCommand; /* = new GeneralCommand("Commands", methodName,
                    new Class<?>[]{ String.class },
                    new Object[] { word });*/
            ICommandExecuter clientProxyCommand;
            Request request =  gson.fromJson(reqData, Request.class);




            switch(exchange.getRequestURI().toString()) {
                case "/login":
                    facadeCommand = new GeneralCommand("server.facade.LoginFacade", "login",
                            new Class<?>[]{Request.class}, new Object[] {request});
                    facadeCommand.exec();
                    clientProxyCommand = new GeneralCommand("server.ClientProxy", "updateLogin",
                            new Class<?>[]{Request.class}, new Object[] {request});
                    clientProxyCommand.exec();
                    break;


                case "/register":
                    facadeCommand = new GeneralCommand("server.facade.RegisterFacade", "register",
                            new Class<?>[]{Request.class}, new Object[] {request});
                    facadeCommand.exec();
                    clientProxyCommand = new GeneralCommand("server.ClientProxy", "updateRegister",
                            new Class<?>[]{Request.class}, new Object[] {request});
                    clientProxyCommand.exec();
                    break;


                case "/createGame":
                    facadeCommand = new GeneralCommand("server.facade.LobbyFacade", "createGame",
                            new Class<?>[]{Request.class}, new Object[] {request});
                    facadeCommand.exec();
                    clientProxyCommand = new GeneralCommand("server.ClientProxy", "updateCreateGame",
                            new Class<?>[]{Request.class}, new Object[] {request});
                    clientProxyCommand.exec();
                    break;


                case "/joinGame":
                    facadeCommand = new GeneralCommand("server.facade.LobbyFacade", "joinGame",
                            new Class<?>[]{Request.class}, new Object[] {request});
                    facadeCommand.exec();
                    clientProxyCommand = new GeneralCommand("server.ClientProxy", "updateJoinGame",
                            new Class<?>[]{Request.class}, new Object[] {request});
                    clientProxyCommand.exec();
                    break;


                case "/startGame":
                    facadeCommand = new GeneralCommand("server.facade.GameStartFacade", "startGame",
                            new Class<?>[]{Request.class}, new Object[] {request});
                    facadeCommand.exec();
                    clientProxyCommand = new GeneralCommand("server.ClientProxy", "updateStartGame",
                            new Class<?>[]{Request.class}, new Object[] {request});
                    clientProxyCommand.exec();
                    break;
            }

        } catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            System.out.println("error with command handler...");
        }
    }

}
