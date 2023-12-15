package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static double longitude;
    private static double latitude;

    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Miasta miasta = objectMapper.readValue(new File("lista.json"), Miasta.class);

            while (true) {
                Scanner scan = new Scanner(System.in);
                System.out.println("P-Podaj miasto, Z-Zakończ");
                if (scan.nextLine().equals("Z")) {
                    break;
                }
                String szukaneMiasto = scan.nextLine();

                List<Miasto> listaMiast = miasta.getMiasta();
                Miasto znalezioneMiasto = listaMiast.stream()
                        .filter(miasto -> miasto.getMiasto().equals(szukaneMiasto))
                        .findFirst()
                        .orElse(null);
                if (znalezioneMiasto==null)
                {
                    System.out.println("Nie znaleziono miasta");
                    continue;
                }
                latitude=znalezioneMiasto.getLatitude();
                longitude=znalezioneMiasto.getLongitude();

                ApiClient client= new ApiClient();
                ApiConnection api = new ApiConnection(client);
                String odpowiedz = api.process(latitude,longitude);
                System.out.println(odpowiedz);

                System.out.println("P-PDF J-JSON X-XML");
                String format = scan.nextLine();

                switch (format) {
                    case "P":
                        FileWriter.to_pdf(odpowiedz);
                        break;
                    case "J":
                        FileWriter.to_json(odpowiedz);
                        break;
                    case "X":
                        FileWriter.to_xml(odpowiedz);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

