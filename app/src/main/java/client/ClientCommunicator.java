package client;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import models.data.Request;
import models.data.User;
import models.data.Result;
import server.GeneralCommand;

public class ClientCommunicator /*implements IClient */{

    /*
    This Class is soley responsible for passing commamnds to the server and
    accepting the results. This class serializes and deserializes the Json,
    handles server exceptions, and general works to contain all internet
    protocal logic for the client side*/



    public Result send(GeneralCommand command, String host, String port) {
        try {
            URL url = new URL("http://" + host + ":" + port + "/");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.addRequestProperty("Accept", "application/json");

            Gson gson = new Gson();
            OutputStream os = http.getOutputStream();
            String jsonStr = gson.toJson(command.toString());
            writeString(jsonStr, os);

            http.connect();
            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = http.getInputStream();
                Reader read = new InputStreamReader(is);

                //Result result = gson.fromJson(read, Result.class);
                return gson.fromJson(read, Result.class);//result;

            } else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
