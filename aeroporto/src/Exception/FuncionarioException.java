package Exception;

public class FuncionarioException extends Exception{

	public FuncionarioException() {
	}

	public FuncionarioException(String message) {
		super(message);
	}

	public FuncionarioException(Throwable cause) {
		super(cause);
	}

	public FuncionarioException(String message, Throwable cause) {
		super(message, cause);
	}

	public FuncionarioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
