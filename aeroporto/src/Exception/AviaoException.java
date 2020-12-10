package Exception;

public class AviaoException extends Exception {

	public AviaoException() {
	}

	public AviaoException(String message) {
		super(message);
	}

	public AviaoException(Throwable cause) {
		super(cause);
	}

	public AviaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public AviaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
