package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import models.command.ICommandExecuter;
import models.command.CommandResult;

public class CommandHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        try {
            System.out.println("inside of command.");
            Gson gson = new Gson();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            OutputStream respBody = exchange.getResponseBody();
            String word = exchange.getRequestURI().getPath().split("/")[2];
            String methodName = exchange.getRequestURI().getPath().split("/")[1];

            ICommandExecuter command = new GeneralCommand(/*"Commands", methodName,
                    new Class<?>[]{ String.class },
                    new Object[] { word }*/);
            CommandResult lr;
            lr = command.exec();
            String jsonStr = gson.toJson(lr);
            writeString(jsonStr, respBody);
            respBody.close();

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
