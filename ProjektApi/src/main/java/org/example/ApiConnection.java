package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ApiConnection {
    ApiClient client;

    public ApiConnection(ApiClient client) {
        this.client= client;
    }

    public String process(Double latitude, Double longitude) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder response = client.makeConnection(latitude, longitude);
        ApiResponse odpowiedz = objectMapper.readValue(response.toString(), ApiResponse.class);
        if (odpowiedz.getMain() == null)
        {
            return "Brak danych pogodowych";
        }

        return("Name: " + odpowiedz.getName() + " Temp: " + odpowiedz.getMain().getTemp() + " Cisnienie: " + odpowiedz.getMain().getPressure() + " Wilgotnosc: " + odpowiedz.getMain().getHumidity());
    }

}
