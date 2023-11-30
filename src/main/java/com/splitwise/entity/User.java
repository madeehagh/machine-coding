package com.splitwise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Getter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  String name;
  String email;
  String phoneNo;

  public long getId() {
    return this.id;
  }
}
