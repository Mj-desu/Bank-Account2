package bank;

import java.util.List;

public interface BankAccount {
  /**
   * Deposit amount to bank account.
   * @param amount
   * @throws Exception
   */
  void deposit(double amount) throws Exception;
  /**
   * Withdraw amount from bank account.
   * @param amount
   * @throws Exception
   */
  void withdraw(double amount) throws Exception;
  /**
   * Return the balance of the account.
   * @return balance
   */
  double getBalance();
  /**
   * Returns true if account is frozen.
   * @return isFrozen
   */
  boolean isFrozen();
  /**
   * Returns all transaction history in string.
   * @return List<Transaction>
   */
  List<Transaction> getTransactionHistory();
}

