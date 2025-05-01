package com.amir.fraud_detection_service.service;

import org.springframework.stereotype.Service;

import com.amir.fraud_detection_service.entity.LoanStatus;
import com.amir.fraud_detection_service.repository.FraudRecordRepository;

import lombok.RequiredArgsConstructor;

@Service  
@RequiredArgsConstructor  
public class FraudDetectionService {  
  
    private final FraudRecordRepository fraudRecordRepository;  
  
    public LoanStatus checkForFraud(int customerId) {  
        return fraudRecordRepository.existsByCustomerId(customerId) ? LoanStatus.REJECTED : LoanStatus.APPROVED;  
    }  
}