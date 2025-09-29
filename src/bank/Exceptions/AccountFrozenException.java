package bank.Exceptions;

public class AccountFrozenException extends Exception {
  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for InsufficientFundsException.
   * @param msg
   */
  public AccountFrozenException(final String msg) {
    super(msg);
  }

}
