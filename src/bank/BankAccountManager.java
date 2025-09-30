package bank;

import java.util.HashMap;
import java.util.List;
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
    int accountId = nextAccountId++;
    accounts.put(accountId, account);
    System.out.println(accountId + ": " + account.getBalance());
  }

  final BankAccount getAccount(final int accountId) {
    if (accounts.get(accountId) == null) {
      throw new NullPointerException();
    }
    return accounts.get(accountId);
  }

  final void listAccounts() {
    for (BankAccount a : accounts.values()) {
      System.out
          .println(((SavingsAccount) a).getOwnerName() + ": " + a.getBalance());
    }
  }

  final List<Transaction> filterTransactionsAbove(final double amount,
      final List<Transaction> txList) {

    return txList.stream()
        .filter(transaction -> transaction.getAmount() > amount).toList();
  }

  final List<Transaction> sortTransactionsByAmount(
      final List<Transaction> txList) {
    txList.sort((Transaction t1, Transaction t2) -> {
      return Double.compare(t1.getAmount(), t2.getAmount());
    });
    return txList;
  }
}
