package com.ly.ocr.repository;

import com.ly.ocr.model.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentSlip, Long> {
    PaymentSlip findByTransactionId(String transactionId);
}
