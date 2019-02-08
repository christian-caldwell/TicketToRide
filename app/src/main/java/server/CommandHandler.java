package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import helper.HttpHelper;
import models.command.ICommandExecuter;
import models.command.CommandResult;
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

            ICommandExecuter command; /* = new GeneralCommand("Commands", methodName,
                    new Class<?>[]{ String.class },
                    new Object[] { word });*/
            Request request;








            switch(exchange.getRequestURI().toString()) {
                case "/login":
                    request =  gson.fromJson(reqData, Request.class);
                    //command = new GeneralCommand("")

                    break;

                    /*
                    // create toLower request and stuff in a command object and execute the command
                        ToLowerRequest toLowerRequest;
                        toLowerRequest =  gson.fromJson(reqData, ToLowerRequest.class);
                        c = new GenericCommand("com.example.singleton.Singleton", "toLower",
                                new Class<?>[]{ToLowerRequest.class}, new Object[] {toLowerRequest});
                        c.execute();
                        break;
                     */

                case "/register":

                    break;


                case "/lobby":

                    break;


                case "/gameStart":

                    break;



            }
/*
            CommandResult lr;
            lr = command.exec();
            String jsonStr = gson.toJson(lr);
            writeString(jsonStr, respBody);
            respBody.close();
*/
        } catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            System.out.println("error with command handler...");
        }
    }
    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }


}
