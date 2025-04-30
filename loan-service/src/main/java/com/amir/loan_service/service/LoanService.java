package com.amir.loan_service.service;

import java.util.List;

import com.amir.loan_service.dto.LoanDto;

import com.amir.loan_service.reponsitory.LoanRepository;

public class LoanService {

    private final LoanRepository loanRepository;

    /*  Retrives all the Loans  */
    public List<LoanDto> listAllLoan(){
        return loanRepository.findAll()
                .stream()
                .map(LoanDto::from)
               .toList();

    }

    public String applyLoan(LoanDto loanDto){
        var loan = LoanDto.from(loanDto);
        /* Check the status of the loan which will be implemented inside the Fraud Detection Service later  */

        loan.setLoanStatus(LoanStatus);
        if (loanStatus.equals(LoanStatus.APPROVED)){
            loanRepository.save(loan);
            return "Loan Applied Successfully" ;
        }
        return "Sorry ! Your loan was not approved ";
    }

}
