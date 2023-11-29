package com.splitwise.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Split {
  private String userId;
  private double amount;
}
