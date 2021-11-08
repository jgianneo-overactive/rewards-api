package com.awards.controller;

import com.awards.model.PointsCustomerReport;
import com.awards.model.Transaction;
import com.awards.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody CreateTransactionRequest request) {
        return new ResponseEntity<>(transactionService.insertTransaction(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.getTransactionById(id), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Transaction>> getTransactionByCustomerId(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.getTransactionsByCustomerId(id), HttpStatus.OK);
    }

    @GetMapping("/reports")
    public ResponseEntity<List<PointsCustomerReport>> getTransactionByCustomerId() {
        return new ResponseEntity<>(transactionService.generatePointsCustomerReport(), HttpStatus.OK);
    }
}
