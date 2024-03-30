package com.enigmacamp.loanapp.model.entity;

import com.enigmacamp.loanapp.util.constant.ApprovalStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "")
class LoanTransaction {
    private String id;
    private LoanType loanType;
    private InstalmentType instalmentType;
    private Customer customer;
    private Double nominal;
    private Long approvedAt;
    private String approvedBy;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus; // enum
    private List<LoanTransactionDetail> loanTransactionDetails;
    private Long createdAt;
    private Long updatedAt;
}
