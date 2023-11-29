import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Expense {

  private String label;
  private String paidBy;
  private double amount;
  private Date createdDate;
  private List<Split> splits;

  public Expense(String label,
                 String paidBy,
                 double amount,
                 Date createdDate,
                 List<Split> splits) {
    this.label = label;
    this.paidBy = paidBy;
    this.amount = amount;
    this.createdDate = createdDate;
    this.splits = splits;
  }
}
