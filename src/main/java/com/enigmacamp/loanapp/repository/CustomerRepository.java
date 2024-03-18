package com.enigmacamp.loanapp.repository;

import com.enigmacamp.loanapp.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("SELECT c FROM Customer c WHERE c.status = false AND c.id = :id")
    Customer findByIdCustomerIsDeleted(@Param("id") String id);
}
