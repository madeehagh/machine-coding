package com.splitwise.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
  String id;
  String name;
  String email;
  String phoneNo;
}
