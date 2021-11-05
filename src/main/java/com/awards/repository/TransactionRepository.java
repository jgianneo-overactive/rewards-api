package com.awards.repository;

import com.awards.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transaction t WHERE t.customer_id = ?1", nativeQuery = true)
    List<Transaction> getTransacionsByCustomerId(Long id);
}
