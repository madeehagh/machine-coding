package com.splitwise.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.splitwise.entity.Expense;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/expense")
public class ExpenseController {

    @PostMapping(value = "/createExpense", consumes = "application/json")
    public ResponseEntity<String> createExpense(@RequestBody String expense){
        System.out.println(expense);
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        Expense expenseObject = gson.fromJson(expense, Expense.class);
        System.out.println(expenseObject);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @GetMapping("/balance")
    public String userBalance() {
        return "sucess";
    }
}
