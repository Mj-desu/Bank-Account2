package bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountManagerTest {
  /**
   * Initialization of bank account manager.
   */
  private BankAccountManager manager;

  @BeforeEach
  void setUp() {
    manager = new BankAccountManager();
  }

  @Test
  void testAddAccountAssignsSequentialIds() {
    // Arrange
    BankAccount account1 = new SavingsAccount("Alice");
    BankAccount account2 = new SavingsAccount("Bob");

    manager.addAccount(account1);
    manager.addAccount(account2);

    assertNotNull(manager.getAccount(1), "Account 1 should be retrievable.");
    assertNotNull(manager.getAccount(2), "Account 2 should be retrievable.");
  }

  @Test
  void testAddAccountInitialBalanceIsCorrect() throws Exception {
    final double expectedBalance = 550.75;
    BankAccount account = new SavingsAccount("Test User");
    account.deposit(expectedBalance);
    manager.addAccount(account);
    BankAccount retrievedAccount = manager.getAccount(1);

    assertEquals(expectedBalance, retrievedAccount.getBalance(),
        "The balance should be correctly stored and retrieved.");
  }

  @Test
  void testGetAccountValidId() {
    BankAccount expectedAccount = new SavingsAccount("Getter Test");
    manager.addAccount(expectedAccount);
    BankAccount actualAccount = manager.getAccount(1);

    assertSame(expectedAccount, actualAccount,
        "The retrieved account should be the exact same object added.");
  }

  @Test
  void testGetAccountInvalidIdThrowsNullPointerException() {
    final int testVal = 999;
    assertThrows(NullPointerException.class, () -> {
      manager.getAccount(testVal);
    }, "Should throw NullPointerException for a non-existent account ID.");
  }

  @Test
  void testListAccountsOutputFormat() throws Exception {
    final double testVal1 = 100.5;
    final double testVal2 = 250;
    BankAccount account1 = new SavingsAccount("Zoe");
    BankAccount account2 = new SavingsAccount("Max");
    account1.deposit(testVal1);
    account2.deposit(testVal2);
    manager.addAccount(account1);
    manager.addAccount(account2);

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    manager.listAccounts();

    System.setOut(System.out);

    String expectedOutput = "Zoe: 100.5\r\nMax: 250.0";
    assertEquals(expectedOutput, outContent.toString().trim(),
        "listAccounts output format and content are incorrect.");
  }

  @Test
  void testSortTransactionsByAmountSortsAscendingly() {
    final double maxVal = 1000;
    final double midVal = 500;
    final double lowVal = 100;
    List<Transaction> transactions = new ArrayList<>(Arrays.asList(
        new Transaction("Deposit", maxVal), new Transaction("Deposit", midVal),
        new Transaction("Deposit", lowVal)));

    List<Transaction> sortedList = manager
        .sortTransactionsByAmount(transactions);

    assertEquals(lowVal, sortedList.get(0).getAmount(),
        "First element should be the minimum amount.");
    assertEquals(midVal, sortedList.get(1).getAmount(),
        "Second element should be the middle amount.");
    assertEquals(maxVal, sortedList.get(2).getAmount(),
        "Third element should be the maximum amount.");
  }

  @Test
  void testFilterTransactionsAboveReturnsOnlyAboveAmount() {
    final double testVal1 = 50;
    final double testVal2 = 100;
    final double testVal3 = 150;
    final double testVal4 = 200;
    final double filterAmount = 100.00;

    List<Transaction> transactions = Arrays.asList(
        new Transaction("Deposit", testVal1),
        new Transaction("Deposit", testVal3),
        new Transaction("Deposit", testVal2),
        new Transaction("Deposit", testVal4));

    List<Transaction> filtered = manager.filterTransactionsAbove(filterAmount,
        transactions);

    assertEquals(2, filtered.size(),
        "Should only return 2 transactions above 100.00 (150.00 and 200.00).");

    // Use a stream to verify the amounts are correct
    List<Double> amounts = filtered.stream().map(Transaction::getAmount)
        .collect(Collectors.toList());

    assertTrue(amounts.contains(testVal3),
        "Filtered list must contain 150.00.");
    assertTrue(amounts.contains(testVal4),
        "Filtered list must contain 200.00.");
    assertFalse(amounts.contains(testVal2),
        "Filtered list must NOT contain 100.00 (only values ABOVE).");
  }
}
