package lk.chamasha.cv.filtering.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class PDFTextExtractor {

    public static String extractText(MultipartFile file) throws Exception {
        try (InputStream input = file.getInputStream();
             PDDocument document = PDDocument.load(input)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }
}

