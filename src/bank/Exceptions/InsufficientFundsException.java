package bank.Exceptions;

public class InsufficientFundsException extends Exception {
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * No argument constructor.
   */
  public InsufficientFundsException() {
    super("Insufficient balance.");
  }
}
