package dk.kalhauge.openglutils.exceptions;

public class CompileException extends Exception {
	private static final long serialVersionUID = 4937862740106607682L;
	private String message;

	public CompileException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
