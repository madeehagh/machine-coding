package splitwise.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Split {
  private User user;
  private double amount;
}
