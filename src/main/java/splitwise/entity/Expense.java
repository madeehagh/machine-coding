package splitwise.entity;

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
  private Date createdDate;
  private List<Split> splits;
}
