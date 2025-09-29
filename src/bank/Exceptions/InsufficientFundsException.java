package bank.Exceptions;

public class InsufficientFundsException extends Exception {
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for InsufficientFundsException.
   * @param msg
   */
  public InsufficientFundsException(final String msg) {
    super(msg);
  }

  /**
   * No argument constructor.
   */
  public InsufficientFundsException() {
    super("Insufficient balance.");
  }
}
