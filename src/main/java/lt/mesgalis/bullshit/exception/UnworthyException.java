package lt.mesgalis.bullshit.exception;

/**
 * Thrown if user isn't allowed to call an operation.
 */
public class UnworthyException extends RuntimeException {

	public UnworthyException() {
		super();
	}

	public UnworthyException(String s) {
		super(s);
	}

	public UnworthyException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public UnworthyException(Throwable throwable) {
		super(throwable);
	}

	protected UnworthyException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
	}
}
