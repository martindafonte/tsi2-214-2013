package excepciones;

public class MultipleRegistersExists extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MultipleRegistersExists() {
	}

	public MultipleRegistersExists(String message) {
		super(message);
	}

	public MultipleRegistersExists(Throwable cause) {
		super(cause);
	}

	public MultipleRegistersExists(String message, Throwable cause) {
		super(message, cause);
	}

	public MultipleRegistersExists(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
