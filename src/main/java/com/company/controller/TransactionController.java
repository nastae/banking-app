package com.company.controller;

import com.company.dto.TransactionDTO;
import com.company.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> addTransaction(@PathVariable Long userId, @Valid @RequestBody TransactionDTO transaction) {
        return transactionService.addTransaction(userId, transaction)
                                 .fold(e -> ResponseEntity.unprocessableEntity().body(e),
                                         ResponseEntity::ok);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> transactions(@PathVariable Long userId, @PageableDefault(sort = "direction", size = 50)
            Pageable pageRequest) {
        return ResponseEntity.ok(transactionService.getAll(userId, pageRequest));
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<?> accountBalance(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.amount(userId));
    }
}
