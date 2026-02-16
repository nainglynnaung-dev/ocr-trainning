package com.ly.ocr.service;

import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public interface OCRService {
    public boolean validateSlip(String extractedText, String expectedTransactionId, String expectedAmount);
    public String extractText(File file) throws TesseractException;

}
