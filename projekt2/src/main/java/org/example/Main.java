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
            Miasta miasta = objectMapper.readValue(new File("J:\\Desktop\\3\\pracownia programowania\\projekt2\\projekt2\\src\\main\\java\\org\\example\\lista.json"), Miasta.class);

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
            //try- jak sie uda to to zrobi, a jak sie nie uda to nie zrobi
            try (PDDocument document = new PDDocument()) { //tworzenie nowego obiektu klasy PDDocument
                PDPage page = new PDPage(PDRectangle.A4); //tworzenie nowej strony w obiekcie klasy PDDocument
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) { //obiekt umożliwiający dodawanie wartosci do strony
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                    contentStream.beginText(); // rozpoczyna blok tekstu, który umożliwia dodawanie tekstu do strony
                    contentStream.newLineAtOffset(50, 700);
                    contentStream.showText("Temperatura: " + odpowiedz.getMain().getTemp());
                    contentStream.showText(" Cisnienie: " + odpowiedz.getMain().getPressure());
                    contentStream.showText(" Wilgotnosc: " + odpowiedz.getMain().getHumidity());
                    contentStream.endText();
                }
                document.save("wyniki.pdf");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

