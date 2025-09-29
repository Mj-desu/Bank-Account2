package bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
  void testShouldBalance() {
    final double expectedOutput = 1000.00;
    double actualOutput;
    testAccount.deposit(expectedOutput);
    actualOutput = testAccount.getBalance();
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  @Tag("deposit")
  void testShouldReturnDepositedAmount() {
    final double expectedOutput = 1000.0;
    double actualOutput;
    testAccount.deposit(expectedOutput);
    actualOutput = testAccount.getBalance();
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  @Tag("deposit")
  void testShouldReturnErrorIfAmountIsZero() {
    String expectedOutput = "The deposit amount must be positive.";
    final double zero = 0;
    testAccount.deposit(zero);

    assertEquals(expectedOutput, outContent.toString().trim());
  }

  @Test
  @Tag("deposit")
  void testShouldReturnErrorIfAmountIsNegative() {
    final double negVal = -500;
    String expectedOutput = "The deposit amount must be positive.";
    testAccount.deposit(negVal);
    assertEquals(expectedOutput, outContent.toString().trim());

  }

  @Test
  @Tag("withdraw")
  void testWithdrawValidAmount() {
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
  void testWithdrawInsufficientAmount() {
    final String expectedOutput = "Deposited: Php 1000.00\n"
        + "Insufficient balance.";
    final double withdrawAmount = 1500.0;
    testAccount.deposit(depositAmount);
    testAccount.withdraw(withdrawAmount);
    assertEquals(expectedOutput, outContent.toString().trim());
  }

  @Test
  @Tag("withdraw")
  void testWithdrawNegativeAmount() {
    String expectedOutput = "Deposited: Php 1000.00\n"
        + "The withdrawn amount must be positive.";
    final double withdrawAmount = -1500.0;
    testAccount.deposit(depositAmount);
    testAccount.withdraw(withdrawAmount);

    assertEquals(expectedOutput.trim(), outContent.toString().trim());
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
  void testDepositWhenFrozen() {
    String expectedOutput = "Account has been frozen.\r\n"
        + "Account is frozen. Cannot deposit.";
    testAccount.freezeAccount();
    testAccount.deposit(depositAmount);

    assertEquals(expectedOutput, outContent.toString().trim());
  }

  @Test
  @Tag("FreezeAccount")
  void testWithdrawWhenFrozen() {
    String expectedOutput = "Deposited: Php 1000.00\n"
        + "Account has been frozen.\r\n"
        + "Account is frozen. Cannot withdraw.";
    final double withdrawAmount = 1500.0;
    testAccount.deposit(depositAmount);
    testAccount.freezeAccount();
    testAccount.withdraw(withdrawAmount);

    assertEquals(expectedOutput, outContent.toString().trim());
  }

  @Test
  @Tag("FreezeAccount")
  void testWithdrawWhenUnFrozen() {
    String expectedOutput = "Deposited: Php 1000.00\n"
        + "Account has been frozen.\r\n" + "Account has been unfrozen.\r\n"
        + "Withdrawn: Php 500.00";
    final double withdrawAmount = 500.0;
    testAccount.deposit(depositAmount);
    testAccount.freezeAccount();
    testAccount.unfreezeAccount();
    testAccount.withdraw(withdrawAmount);

    assertEquals(expectedOutput, outContent.toString().trim());
  }
}
