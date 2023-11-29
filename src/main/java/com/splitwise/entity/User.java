package com.splitwise.entity;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class User {
  @Expose
  String id;
  String name;
  String email;
  String phoneNo;
}
