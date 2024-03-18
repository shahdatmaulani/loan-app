package com.enigmacamp.loanapp.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private boolean status;
}
