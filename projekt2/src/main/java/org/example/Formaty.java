package org.example;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
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
            System.out.println("xd");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
