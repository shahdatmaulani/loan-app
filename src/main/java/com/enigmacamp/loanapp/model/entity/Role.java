package com.enigmacamp.loanapp.model.entity;

import com.enigmacamp.loanapp.util.constant.ERole;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private String id;
    @Enumerated(EnumType.STRING)
    private ERole role;
}
