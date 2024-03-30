package com.enigmacamp.loanapp.model.entity;

import com.enigmacamp.loanapp.util.constant.EInstalmentType;
import jakarta.persistence.Entity;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
class InstalmentType {
    private String id;
    private EInstalmentType instalmentType;
}