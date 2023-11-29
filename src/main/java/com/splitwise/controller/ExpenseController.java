package com.splitwise.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.splitwise.entity.Expense;
import com.splitwise.entity.User;
import com.splitwise.service.SplitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/splitwise")
public class ExpenseController {

    @Autowired
    SplitServiceImpl splitService;

    @PostMapping(value = "/expense", consumes = "application/json")
    public ResponseEntity<String> createExpense(@RequestBody String expense){
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        try {
            Expense expenseObject = gson.fromJson(expense, Expense.class);
            System.out.println(expenseObject);
            splitService.addExpense(expenseObject);
            return new ResponseEntity<>("Expense added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body("INVALID REQUEST");
        }
    }

    @PostMapping(value = "/invite", consumes = "application/json")
    public ResponseEntity<String> sendInvite(@RequestParam String inviteToUser) {
        try {
            splitService.sendInvite(inviteToUser);
            return ResponseEntity.ok("Invitation sent to user");
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().body("Something went wrong");
        }

    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<String> register(@RequestBody String userDetails) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            User user = gson.fromJson(userDetails, User.class);
            splitService.registerUser(user);
            return ResponseEntity.ok("Invitation sent to user");
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body("INVALID REQUEST");
        }
    }

    @GetMapping("/balance")
    public String userBalance() {
        return "success";
    }
}
