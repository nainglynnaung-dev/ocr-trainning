package com.ly.ocr.service.impl;

import com.ly.ocr.service.OCRService;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;
@Service
public class OCRServiceImpl implements OCRService {

    public String extractText(File file) throws TesseractException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\Sousuke\\tessdata"); // adjust path
        tesseract.setLanguage("eng");
        return tesseract.doOCR(file);
    }
         public boolean validateSlip(String extractedText, String expectedTransactionId, String expectedAmount) {
        return extractedText.contains(expectedTransactionId) && extractedText.contains(expectedAmount);
    }
}
