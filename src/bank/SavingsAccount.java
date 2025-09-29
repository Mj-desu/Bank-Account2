package bank;

public class SavingsAccount extends AbstractBankAccount {
  /**
   * Variable to store owner's name.
   */
  private String ownerName;
  /**
   * Constructor for class SavingsAccount.
   * @param name
   */
  public SavingsAccount(final String name) {
    this.setOwnerName(name);
  }
  /**
   * Returns the name of the owner.
   * @return ownerName
   */
  public String getOwnerName() {
    return ownerName;
  }
  /**
   * Setter for the owerName variable.
   * @param name
   */
  public void setOwnerName(final String name) {
    this.ownerName = name;
  }

}
