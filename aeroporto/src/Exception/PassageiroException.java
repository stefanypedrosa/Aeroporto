package Exception;

public class PassageiroException extends Exception {

	public PassageiroException() {
	}

	public PassageiroException(String message) {
		super(message);
	}

	public PassageiroException(Throwable cause) {
		super(cause);
	}

	public PassageiroException(String message, Throwable cause) {
		super(message, cause);
	}

	public PassageiroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
