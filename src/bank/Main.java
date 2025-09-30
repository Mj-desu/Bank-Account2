package bank;
//CODE REVIEWER: lady.mendoza

import java.util.List;

//NO CHECKSTYLE VIOLATIONS.

final class Main {
  /**
   * Value 500.
   */
  static final int TEST_VAL_500 = 500;
  /**
   * Value -100.
   */
  static final int TEST_VAL_NEG100 = -100;
  /**
   * Value -500.
   */
  static final int TEST_VAL_NEG500 = -500;
  /**
   * Value -100.
   */
  static final int TEST_VAL_1000 = 1000;
  /**
   * Value 1500.
   */
  static final int TEST_VAL_1500 = 1500;

  /**
   * Value 11500.
   */
  static final int TEST_VAL_11500 = 11500;
  /**
   * Value 100.
   */
  static final int TEST_VAL_100 = 100;

  private Main() {
  }

  /**
   * @param args
   * @throws Exception
   */
  public static void main(final String[] args) throws Exception {

    BankAccountManager myManager = new BankAccountManager();
    SavingsAccount myAccount = new SavingsAccount("MJ");

    // Case 1
    myManager.addAccount(myAccount);

    // Case 2
    myAccount.deposit(TEST_VAL_1000);

    // Case 3
    try {
      myAccount.deposit(0);
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    // Case 4
    try {
      myAccount.deposit(TEST_VAL_NEG500);
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    // Case 5
    myAccount.withdraw(TEST_VAL_500);

    // Case 6
    try {
      myAccount.withdraw(TEST_VAL_1500);
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    // Case 7
    try {
      myAccount.withdraw(TEST_VAL_NEG100);
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    // Case 8
    try {
      myAccount.freezeAccount();
      myAccount.deposit(TEST_VAL_500);
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    // Case 9
    try {
      myAccount.freezeAccount();
      myAccount.withdraw(TEST_VAL_500);
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    // Case 10
    myAccount.unfreezeAccount();
    myAccount.withdraw(TEST_VAL_100);

    // Case 11
    System.out.println(myAccount.getBalance());

    // Case 12
    List<Transaction> myTransactionHistory = myAccount.getTransactionHistory();
    List<Transaction> filteredTransaction = myManager
        .filterTransactionsAbove(TEST_VAL_500, myTransactionHistory);
    for (Transaction t : filteredTransaction) {
      System.out.print(t.toString());
    }

    // Case 13
    List<Transaction> sortedTransactionHistory = myManager
        .sortTransactionsByAmount(myTransactionHistory);
    for (Transaction t : sortedTransactionHistory) {
      System.out.print(t.toString());
    }

    // Case 14
    try {
      final int val5 = 5;
      myManager.getAccount(val5);
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

}
