package bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import bank.Exceptions.AccountFrozenException;
import bank.Exceptions.InsufficientFundsException;
import bank.Exceptions.InvalidAmountException;

class SavingsAccountTest {
  /**
   * Test account variable initialization.
   */
  private SavingsAccount testAccount;
  /**
   * Data initialization.
   */
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  /**
   * Starting deposit for each test.
   */
  private final double depositAmount = 1000.0;

  @BeforeEach
  void setup() {
    testAccount = new SavingsAccount("Test");
    outContent.reset();
    System.setOut(new PrintStream(outContent));

  }

  @Test
  void testShouldReturnOwnerName() {
    String expectedOutput = "Test";
    String actualOutput = testAccount.getOwnerName();
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  void testShouldBalance() throws Exception {
    final double expectedOutput = 1000.00;
    double actualOutput;
    testAccount.deposit(expectedOutput);
    actualOutput = testAccount.getBalance();
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  @Tag("deposit")
  void testShouldReturnDepositedAmount() throws Exception {
    final double expectedOutput = 1000.0;
    double actualOutput;
    testAccount.deposit(expectedOutput);
    actualOutput = testAccount.getBalance();
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  @Tag("deposit")
  void testShouldReturnErrorIfAmountIsZero() throws Exception {
    final double zero = 0;
    assertThrows(InvalidAmountException.class, () -> testAccount.deposit(zero));
  }

  @Test
  @Tag("deposit")
  void testShouldReturnErrorIfAmountIsNegative() throws Exception {
    final double negVal = -500;
    assertThrows(InvalidAmountException.class,
        () -> testAccount.deposit(negVal));

  }

  @Test
  @Tag("withdraw")
  void testWithdrawValidAmount() throws Exception {
    final double withdrawAmount = 500;
    final double expectedOutput = 500;
    final double actualOutput;
    testAccount.deposit(depositAmount);
    testAccount.withdraw(withdrawAmount);
    actualOutput = testAccount.getBalance();
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  @Tag("withdraw")
  void testWithdrawInsufficientAmount() throws Exception {
    final double withdrawAmount = 1500.0;
    testAccount.deposit(depositAmount);
    assertThrows(InsufficientFundsException.class,
        () -> testAccount.withdraw(withdrawAmount));

  }

  @Test
  @Tag("withdraw")
  void testWithdrawNegativeAmount() throws Exception {

    final double withdrawAmount = -1500.0;
    testAccount.deposit(depositAmount);

    assertThrows(InvalidAmountException.class,
        () -> testAccount.withdraw(withdrawAmount));
  }

  @Test
  @Tag("FreezeAccount")
  void testFreezeAccount() {
    Boolean result;
    testAccount.freezeAccount();
    result = testAccount.isFrozen();

    assertTrue(result);
  }

  @Test
  @Tag("FreezeAccount")
  void testDepositWhenFrozen() throws Exception {

    testAccount.freezeAccount();

    assertThrows(AccountFrozenException.class,
        () -> testAccount.deposit(depositAmount));
  }

  @Test
  @Tag("FreezeAccount")
  void testWithdrawWhenFrozen() throws Exception {

    final double withdrawAmount = 1500.0;
    testAccount.deposit(depositAmount);
    testAccount.freezeAccount();

    assertThrows(AccountFrozenException.class,
        () -> testAccount.withdraw(withdrawAmount));
  }

  @Test
  @Tag("FreezeAccount")
  void testWithdrawWhenUnFrozen() throws Exception {
    String expectedOutput = "Deposited: Php 1000.0\n"
        + "Account has been frozen.\r\n" + "Account has been unfrozen.\r\n"
        + "Withdrawn: Php 500.0";
    final double withdrawAmount = 500.0;
    testAccount.deposit(depositAmount);
    testAccount.freezeAccount();
    testAccount.unfreezeAccount();
    testAccount.withdraw(withdrawAmount);

    assertEquals(expectedOutput, outContent.toString().trim());
  }

  @Test
  void testGetTransactionHistoryReturnsCorrectContent() throws Exception {
    final double testVal = 100;

    testAccount.deposit(testVal);
    testAccount.withdraw(testVal);

    List<Transaction> history = testAccount.getTransactionHistory();

    assertEquals("Deposited", history.get(0).getType());
    assertEquals(testVal, history.get(0).getAmount());

    assertEquals("Withdrawn", history.get(1).getType());
    assertEquals(testVal, history.get(1).getAmount());
  }
}
