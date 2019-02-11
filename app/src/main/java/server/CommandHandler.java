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
import models.data.Result;

public class CommandHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        /*
        Create commands that call the command class, which will call the serverCommands
         */
        boolean success = false;

        try {
            //exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            OutputStream respBody = exchange.getResponseBody();

            // Extract the JSON string from the HTTP request body
            HttpHelper helper = new HttpHelper();
            String reqData = helper.httpRequestToJson(exchange);

            //Display/log the request JSON data
            System.out.println("JSON sent to server: " + reqData);

            Gson gson = new Gson();

            ICommandExecuter facadeCommand;
            ICommandExecuter clientProxyCommand;


            //TODO: need to add in the clientProxy call


            // Create the command through deserialization
            ObjectMapper mapper = new ObjectMapper();
            facadeCommand = mapper.readValue(reqData, GeneralCommand.class);
            Result result = facadeCommand.exec();

            //Use ObjectMappper to convert the result to a json object
            String jsonResponse = mapper.writeValueAsString(result);

            //Send the HTTP response to the client
            if (helper.sendHttpResponse(exchange, jsonResponse))
                success = true;

            if (!success) {
                // The HTTP request was invalid somehow, so we return a "bad request"
                // status code to the client.
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                // We are not sending a response body, so close the response body
                // output stream, indicating that the response is complete.
                exchange.getResponseBody().close();
            }


        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            System.out.println("error with command handler...");
        }
    }

}
