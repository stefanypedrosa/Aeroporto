package Exception;

public class BilheteException extends Exception {

	public BilheteException() {
	}

	public BilheteException(String message) {
		super(message);
	}

	public BilheteException(Throwable cause) {
		super(cause);
	}

	public BilheteException(String message, Throwable cause) {
		super(message, cause);
	}

	public BilheteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
