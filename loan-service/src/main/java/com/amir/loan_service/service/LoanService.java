package com.amir.loan_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amir.loan_service.client.FraudDetectionClient;
import com.amir.loan_service.dto.LoanDto;
import com.amir.loan_service.entity.Loan;
import com.amir.loan_service.entity.LoanStatus;
import com.amir.loan_service.reponsitory.LoanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final FraudDetectionClient fraudDetectionClient;
    private final LoanRepository loanRepository;

    public List<LoanDto> listAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(LoanDto::from)
                .toList();
    }

    public String applyLoan(LoanDto loanDto) {
        var loan = Loan.from(loanDto);
        LoanStatus loanStatus = fraudDetectionClient.evaluateLoan(loan.getCustomerId());
        loan.setLoanStatus(loanStatus);
        if (loanStatus.equals(LoanStatus.APPROVED)) {
            loanRepository.save(loan);
            return "Loan applied successfully";
        }
        return "Sorry! Your loan was not approved";
    }
}