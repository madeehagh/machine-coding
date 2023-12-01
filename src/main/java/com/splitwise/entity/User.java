package com.splitwise.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  String name;
  String email;
  String phoneNo;

  public User(String name, String email, String phoneNo) {
    this.name = name;
    this.email = email;
    this.phoneNo = phoneNo;
  }

  public long getId() {
    return this.id;
  }
}
