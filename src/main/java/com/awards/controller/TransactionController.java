package com.awards.controller;

import com.awards.model.PointsCustomerReport;
import com.awards.model.Transaction;
import com.awards.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/transactions")
@Api(value = "TransactionController", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @ApiOperation("Create new transaction")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Transaction.class)})
    public ResponseEntity<Transaction> createTransaction(@RequestBody @Valid CreateTransactionRequest request) {
        return new ResponseEntity<>(transactionService.insertTransaction(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Gets transaction by id")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.getTransactionById(id), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    @ApiOperation("Gets all transaction by customer id")
    public ResponseEntity<List<Transaction>> getTransactionByCustomerId(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.getTransactionsByCustomerId(id), HttpStatus.OK);
    }

    @GetMapping("/reports")
    @ApiOperation("Generate all customers points reports")
    public ResponseEntity<List<PointsCustomerReport>> getTransactionByCustomerId() {
        return new ResponseEntity<>(transactionService.generatePointsCustomerReport(2), HttpStatus.OK);
    }
}
