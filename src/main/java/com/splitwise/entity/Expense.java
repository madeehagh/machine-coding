package com.splitwise.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.splitwise.enums.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Expense {
  private String label;
  private String paidBy;
  private double amount;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private Date createdDate;
  private List<String> users;
  private ExpenseType expenseType;
}
