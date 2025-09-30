package bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TransactionTest {

  @Test
  void testSetAndGetType() {
    final int depositAmount = 500;
    Transaction transaction = new Transaction("Deposit", depositAmount);
    String newType = "Deposit";

    transaction.setType(newType);
    String actualType = transaction.getType();

    assertEquals(newType, actualType,
        "Getter should return the value set by the setter.");
  }

  @Test
  void testSetAndGetAmount() {
    final int depositAmount = 500;
    Transaction transaction = new Transaction("Deposit", depositAmount);
    final double newAmount = 1234.56;

    transaction.setAmount(newAmount);
    double actualAmount = transaction.getAmount();

    assertEquals(newAmount, actualAmount,
        "Getter should return the amount set by the setter.");
  }

  @Test
  void testToStringFormat() {
    String type = "Deposit";
    final double amount = 750.00;
    Transaction transaction = new Transaction(type, amount);

    String expectedString = "Deposit: Php 750.0\n";
    String actualString = transaction.toString();

    assertEquals(expectedString, actualString,
        "toString() format is incorrect.");
  }

}
