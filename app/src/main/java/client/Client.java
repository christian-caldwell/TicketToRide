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

import models.Request;
import models.User;
import models.Result;

public class Client implements IClient {
    public Result send(String serverHost, String serverPort, Request request) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.addRequestProperty("Accept", "application/json");

            Gson gson = new Gson();
            OutputStream os = http.getOutputStream();
            String jsonStr = gson.toJson(request);
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


    @Override
    public void updateClient() {

    }

    @Override
    public void join() {

    }

    @Override
    public void create() {

    }

	@Override
	public void updateAuthToken(String newAuthToken) {
		
	}

	@Override
	public String passAuthToken() {
		
		return null;
	}
	@Override
	public void setUserValues(User newUser) {
		
	}
}
