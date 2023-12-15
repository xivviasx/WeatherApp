package org.example;
//pdf
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;

//json
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class FileWriter {
    public static void to_pdf(String apiResponse){
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText(apiResponse);
                contentStream.endText();
            }
            document.save("wyniki.pdf");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void to_json(String apiResponse){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("wyniki.json"), apiResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void to_xml(String apiResponse){
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File("wyniki.xml"), apiResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
