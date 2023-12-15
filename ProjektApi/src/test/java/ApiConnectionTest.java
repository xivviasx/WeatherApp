import org.example.ApiClient;
import org.example.ApiConnection;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ApiConnectionTest {
    ApiClient client;
    @Test
    public void processTestPositive() throws IOException {
        client = Mockito.mock(ApiClient.class);
        ApiConnection apiConnection=new ApiConnection(client);
        when(client.makeConnection(0.0,0.0)).thenReturn(new StringBuilder("{\"main\":{\"temp\":272.96,\"pressure\":1015,\"humidity\":84},\"name\":\"Gniezno\"}"));

        String result = apiConnection.process(0.0,0.0);
        assertEquals(result, new String("Name: Gniezno Temp: 272.96 Cisnienie: 1015 Wilgotnosc: 84"));

    }
    @Test
    public void processTestNegative() throws IOException {
        client = Mockito.mock(ApiClient.class);
        ApiConnection apiConnection = new ApiConnection(client);

        when(client.makeConnection(0.0, 0.0)).thenThrow(new IOException("Error"));

        try {
            apiConnection.process(0.0, 0.0);
        } catch (IOException e) {
            assertEquals("Error", e.getMessage());
        }
    }

    @Test
    public void processTestPositiveEmptyWeatherData() throws IOException {
        ApiClient client = Mockito.mock(ApiClient.class);
        ApiConnection apiConnection = new ApiConnection(client);

        when(client.makeConnection(0.0, 0.0)).thenReturn(new StringBuilder("{}"));

        String result = apiConnection.process(0.0,0.0);

        assertEquals("Brak danych pogodowych", result);
    }

}