package bank.Exceptions;

public class InvalidAmountException extends Exception {
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for InsufficientFundsException.
   * @param msg
   */
  public InvalidAmountException(final String msg) {
    super(msg);
  }

}
