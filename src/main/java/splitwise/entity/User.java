package splitwise.entity;

import lombok.Data;

@Data
public class User {
  String id;
  String name;
  String email;
  String phoneNo;

  public User(String id, String name, String email, String phoneNo) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phoneNo = phoneNo;
  }
}
