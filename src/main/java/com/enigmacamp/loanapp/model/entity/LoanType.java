package com.enigmacamp.loanapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_loan_type")
class LoanType {
    private String id;
    private String type;
    private Double maxLoan;
}
