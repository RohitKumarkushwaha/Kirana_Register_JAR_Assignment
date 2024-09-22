package com.kirana_register_API.Controller;

import com.kirana_register_API.Entity.Transaction_Register;
import com.kirana_register_API.Service.Transaction_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/KiranaRegisterAPI")
public class Transaction_Controller {

    @Autowired
    private Transaction_Service transactionService;

    /**
     * Save a new transaction.
     * 
     * @param transaction The transaction data to be saved.
     * @param currency    The currency used in the transaction.
     * @return ResponseEntity indicating success or failure.
     */
    @PostMapping("/save")
    public ResponseEntity<String> saveTransaction(
            @RequestBody Transaction_Register transaction,
            @RequestParam(name = "currency", required = true) String currency) {
        try {
            transactionService.SaveTransaction(transaction, currency);
            return new ResponseEntity<>("Transaction saved successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save transaction: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieve transactions by date.
     * 
     * @param date Date in 'yyyy-MM-dd' format.
     * @return List of transactions for the given date.
     */
    @GetMapping("/getTransactionByDate")
    public ResponseEntity<List<Transaction_Register>> getTransactionByDate(@RequestParam("date") String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date, formatter);
            List<Transaction_Register> transactions = transactionService.getAllTransactionsByDate(localDate);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieve daily transaction reports.
     * 
     * @return Map of transactions grouped by day.
     */
    @GetMapping("/getDailyReports")
    public ResponseEntity<Map<Object, List<Transaction_Register>>> getDailyReports() {
        try {
            Map<Object, List<Transaction_Register>> dailyReports = transactionService.getDailyReports();
            return new ResponseEntity<>(dailyReports, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update a transaction by its ID.
     * 
     * @param id          Transaction ID.
     * @param transaction The updated transaction data.
     * @return ResponseEntity indicating success or failure.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTransaction(@PathVariable Long id,
            @RequestBody Transaction_Register transaction) {
        try {
            transactionService.updateTransaction(id, transaction);
            return new ResponseEntity<>("Transaction updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update transaction: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete a transaction by its ID.
     * 
     * @param id Transaction ID.
     * @return ResponseEntity indicating success or failure.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteCustomer(id);
            return new ResponseEntity<>("Transaction deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete transaction: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
