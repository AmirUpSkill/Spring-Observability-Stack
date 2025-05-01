package com.amir.fraud_detection_service.entity;

public record FraudRecord(
    Long id , 
    String fraudRecordId,
    int customerId , 
    LoanStatus loanStatus
) {

}
