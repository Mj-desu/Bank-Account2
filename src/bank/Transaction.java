package bank;

public class Transaction {
  /**
   * Type of transaction.
   */
  private String tType;
  /**
   * Transaction amount.
   */
  private double tAmount;
  /**
   * Constructor for Transaction class.
   * @param type
   * @param amount
   */
  public Transaction(final String type, final double amount) {
    setType(type);
    setAmount(amount);
  }
  /**
   * Getter for transaction type.
   * @return tType
   */
  public String getType() {
    return tType;
  }
  /**
   * Setter for transaction type.
   * @param type
   */
  public void setType(final String type) {
    this.tType = type;
  }
  /**
   * Getter for transaction amount.
   * @return tAmount
   */
  public double getAmount() {
    return tAmount;
  }
  /**
   * Setter for transaction amount.
   * @param amount
   */
  public void setAmount(final double amount) {
    this.tAmount = amount;
  }
  /**
   * Type and amount in string.
   * @return string
   */
  public String toString() {
    return  "Type: " + tType + " Amount: " + tAmount;
  }
}
