package bank;
//CODE REVIEWER: KYLE ANGEL RAMIREZ

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
   * Entry point.
   * @param args
   * @throws Exception
   */
  public static void main(final String[] args) throws Exception {

    SavingsAccount myAccount = new SavingsAccount("MJ");
    System.out.println(myAccount.getOwnerName());

    myAccount.deposit(TEST_VAL_1000);
    myAccount.deposit(TEST_VAL_1000);
    myAccount.withdraw(TEST_VAL_500);
    myAccount.withdraw(TEST_VAL_500);

    for (Transaction a : myAccount.getTransactionHistory()) {
      System.out.println(a.toString());
    }

//    // Deposit
//    myAccount.deposit(TEST_VAL_1000);
//    myAccount.deposit(0);
//    myAccount.deposit(TEST_VAL_NEG500);
//    System.out.println(myAccount.getBalance());
//
//    // Withdraw
//    myAccount.withdraw(TEST_VAL_500);
//    myAccount.withdraw(TEST_VAL_1500);
//    myAccount.withdraw(TEST_VAL_NEG100);
//
//    // Freeze account
//    myAccount.freezeAccount();
//    myAccount.deposit(TEST_VAL_11500);
//    myAccount.withdraw(TEST_VAL_500);
//
//    // UnFreeze account
//    myAccount.unfreezeAccount();
//    myAccount.withdraw(TEST_VAL_100);
//
//    System.out.println(myAccount.isFrozen());
//    System.out.println(myAccount.getBalance());
  }

}
