package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import helper.HttpHelper;
import models.command.ICommandExecuter;

public class CommandHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        /*
        Create commands that call the command class, which will call the serverCommands


         */
        try {
            System.out.println("inside of command.");
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            OutputStream respBody = exchange.getResponseBody();
//            String word = exchange.getRequestURI().getPath().split("/")[2];
//            String methodName = exchange.getRequestURI().getPath().split("/")[1];

            // Extract the JSON string from the HTTP request body
            HttpHelper helper = new HttpHelper();
            String reqData = helper.httpRequestToJson(exchange);

            //Display/log the request JSON data
            System.out.println("JSON sent to server: " + reqData);

            Gson gson = new Gson();

            ICommandExecuter facadeCommand;
            ICommandExecuter clientProxyCommand;

//            Create the command through deserialization
            ObjectMapper mapper = new ObjectMapper();
            facadeCommand = mapper.readValue(reqData, GeneralCommand.class);
            facadeCommand.exec();

        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            System.out.println("error with command handler...");
        }
    }

}
