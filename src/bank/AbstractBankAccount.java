package bank;
//CODE REVIEWER: KYLE ANGEL RAMIREZ

import java.util.List;

import bank.Exceptions.AccountFrozenException;
import bank.Exceptions.InsufficientFundsException;
import bank.Exceptions.InvalidAmountException;

//NO CHECKSTYLE VIOLATIONS. ALL REQUIRED METHODS ARE PRESENT.

public abstract class AbstractBankAccount implements BankAccount {
  /**
   * List of transaction history.
   */
  private List<Transaction> transactionHistory;
  /**
   * Variable for balance of the account.
   */
  private double balance;
  /**
   * Variable to check if account is frozen.
   */
  private boolean isFrozen;

  /**
   * Constructor for AbstractBankAccount.
   */
  public AbstractBankAccount() {
    this.balance = 0;
    this.isFrozen = false;
  }

  /**
   * Deposit amount to bank account.
   * @param amount
   */
  public void deposit(final double amount) throws Exception {
    if (isFrozen) {
      throw new AccountFrozenException("Account is frozen. Cannot deposit.");
    }
    if (amount <= 0) {
      throw new InvalidAmountException("The deposit amount must be positive.");
    }
    this.balance += amount;
    System.out.printf("Deposited: Php %.2f\n", amount);

  }

  /**
   * Withdraw amount from bank account.
   * @param amount
   */
  public void withdraw(final double amount) throws Exception {
    if (isFrozen) {
      throw new AccountFrozenException("Account is frozen. Cannot withdraw.");
    }
    if (amount > balance) {
      throw new InsufficientFundsException();
    }
    if (amount < 0) {
      throw new InvalidAmountException(
          "The withdrawn amount must be positive.");
    }
    this.balance -= amount;
    System.out.printf("Withdrawn: Php %.2f\n", amount);
  }

  /**
   * Return the balance of the account.
   * @return balance
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Returns true if account is frozen.
   * @return isFrozen
   */
  public boolean isFrozen() {
    return isFrozen;
  }

  /**
   * Sets account to frozen.
   */
  public void freezeAccount() {
    this.isFrozen = true;
    System.out.println("Account has been frozen.");
  }

  /**
   * Sets account to not frozen.
   */
  public void unfreezeAccount() {
    this.isFrozen = false;
    System.out.println("Account has been unfrozen.");
  }
  /**
   * Getter for transaction history.
   * @return transactionHistory
   */
  public List<Transaction> getTransactionHistory() {
    return transactionHistory;
  }
}
