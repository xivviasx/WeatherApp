package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
//Jackson do przetwarzania JSONa
//Apache PDFBox do generowania pliku PDF
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            //odczytywanie danych json jako obiekty java i zapisywanie do obietku klasy Miasta
            ObjectMapper objectMapper = new ObjectMapper();
            Miasta miasta = objectMapper.readValue(new File("J:\\Desktop\\3\\pracownia_programowania\\projekt2\\projekt2\\src\\main\\java\\org\\example\\lista.json"), Miasta.class);

            //pobieranie nazwy miasta od użytkownika; szukanie miasta w liscie miast
            Scanner scan = new Scanner(System.in);
            System.out.println("Podaj miasto: ");
            String szukaneMiasto = scan.nextLine();

            Double latitude = null, longitude = null;
            List<Miasto> listaMiast = miasta.getMiasta();
            for (Miasto miasto : listaMiast) {
                if (miasto.getMiasto().equals(szukaneMiasto)) {
                    latitude = miasto.getLatitude();
                    longitude = miasto.getLongitude();
                }
            }

            //API OpenWeatherMap
            String apiKey = "b31ed20f2f7e0e9ec45d47be45d88758";
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude +"&appid=" + apiKey;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            //odczytywanie odpowiedzi
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();
            //System.out.println(response);

            //deserializacja odpowiedzi API do obiektu Java
            Odpowiedź odpowiedz = objectMapper.readValue(response.toString(), Odpowiedź.class);

            //wypisanie informacji o pogodzie
            System.out.println("Temp: " + odpowiedz.getMain().getTemp() + " Cisnienie: " + odpowiedz.getMain().getPressure() + " Wilgotnosc: " + odpowiedz.getMain().getHumidity());

            //zapisywanie do pdf
            Formaty.to_pdf(odpowiedz);
            

        } catch (Exception e) {
            e.printStackTrace();
        }//
    }
}

