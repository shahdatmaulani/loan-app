package com.enigmacamp.loanapp.model.entity;

import com.enigmacamp.loanapp.util.constant.LoanStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
class LoanTransactionDetail {
    private String id;
    private Long transactionDate;
    private Double nominal;
    private LoanTransaction loanTransaction;
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus; // enum
    private Long createdAt;
    private Long updatedAt;
}
