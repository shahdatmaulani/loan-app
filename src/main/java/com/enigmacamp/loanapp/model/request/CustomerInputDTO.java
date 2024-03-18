package com.enigmacamp.loanapp.model.request;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerInputDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
}
