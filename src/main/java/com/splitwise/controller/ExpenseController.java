package com.splitwise.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.splitwise.entity.Expense;
import com.splitwise.service.SplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/expense")
public class ExpenseController {

    @Autowired
    SplitService splitService;

    @PostMapping(value = "/createExpense", consumes = "application/json")
    public ResponseEntity<String> createExpense(@RequestBody String expense){
        System.out.println(expense);
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        try {
            Expense expenseObject = gson.fromJson(expense, Expense.class);
            System.out.println(expenseObject);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body("INVALID REQUEST");
        }

    }

    @GetMapping("/balance")
    public String userBalance() {
        return "sucess";
    }
}
