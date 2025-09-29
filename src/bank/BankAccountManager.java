package bank;

import java.util.HashMap;
import java.util.Map;

public class BankAccountManager {
  /**
   * Hash map of accounts.
   */
  private Map<Integer, BankAccount> accounts;
  /**
   * Next account id.
   */
  private int nextAccountId;

  /**
   * Constructor for BankAccountManager class.
   */
  public BankAccountManager() {
    accounts = new HashMap<>();
    nextAccountId = 1;
  }

  final void addAccount(final BankAccount account) {
    accounts.put(nextAccountId++, account);
  }

  final BankAccount getAccount(final int accountId) {
    return accounts.get(accountId);
  }
  void listAccounts() {

  }
}
