package com.ly.ocr.controller;

import com.ly.ocr.model.PaymentSlip;
import com.ly.ocr.repository.PaymentRepository;
import com.ly.ocr.service.OCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/slips")
public class SlipController {

    @Autowired
    private OCRService ocrService;

    @Autowired
    private PaymentRepository repository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadSlip(@RequestParam("file") MultipartFile file,
                                             @RequestParam("expectedTransactionId") String expectedTransactionId,
                                             @RequestParam("expectedAmount") String expectedAmount) {
        try {
            Path path = Paths.get("uploads/" + file.getOriginalFilename());
            Files.write(path, file.getBytes());
            File convFile = path.toFile();

            String extractedText = ocrService.extractText(convFile);
            System.out.println("Extracted Text : " + extractedText);
            boolean valid = ocrService.validateSlip(extractedText, expectedTransactionId, expectedAmount);

            PaymentSlip slip = new PaymentSlip();
            slip.setFilePath(path.toString());
            slip.setTransactionId(expectedTransactionId);
            slip.setAmount(expectedAmount);
            slip.setExtractedText(extractedText);
            System.out.println("expected amount is " + expectedAmount);
            System.out.println("expected transaction Id is " + expectedTransactionId);
            repository.save(slip);

            return ResponseEntity.ok(valid ? "Slip validated and saved" : "Slip invalid but saved for review");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error processing slip");
        }
    }
}
