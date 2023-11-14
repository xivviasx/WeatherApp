package org.example;
//pdf
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

//json
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

//xml
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
public class Formaty {
    public static void to_pdf(Odpowiedź odpowiedz){
        try (PDDocument document = new PDDocument()) { //tworzenie nowego obiektu klasy PDDocument
            PDPage page = new PDPage(PDRectangle.A4); //tworzenie nowej strony w obiekcie klasy PDDocument
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) { //obiekt umożliwiający dodawanie wartosci do strony
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                contentStream.beginText(); // rozpoczyna blok tekstu, który umożliwia dodawanie tekstu do strony
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("Temperatura: " + odpowiedz.getMain().getTemp() + " Cisnienie: " + odpowiedz.getMain().getPressure()+" Wilgotnosc: " + odpowiedz.getMain().getHumidity());
                contentStream.endText();
            }
            document.save("wyniki.pdf");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void to_json(Odpowiedź odpowiedz){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Zapisujemy obiekt do pliku JSON
            objectMapper.writeValue(new File("wyniki.json"), odpowiedz);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void to_xml(Odpowiedź odpowiedz){
        XmlMapper xmlMapper = new XmlMapper();
        try {
            // Zapisz obiekt do pliku XML
            xmlMapper.writeValue(new File("wyniki.xml"), odpowiedz);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
