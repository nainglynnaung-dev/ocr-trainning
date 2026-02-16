package com.ly.ocr.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class PaymentSlip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;
    private String transactionId;
    private String payer;
    private String amount;
    private LocalDate date;

    @Lob
    private String extractedText;

}
