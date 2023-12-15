package org.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {
    public StringBuilder makeConnection(double latitude, double longitude) throws IOException {
        String apiKey = "b31ed20f2f7e0e9ec45d47be45d88758";
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
            StringBuilder response = new StringBuilder();
            char[] buffer = new char[4096];
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) != -1) {
                response.append(buffer, 0, bytesRead);
            }
            return response;
        }
    }
}

