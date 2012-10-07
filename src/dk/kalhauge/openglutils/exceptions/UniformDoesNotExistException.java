package dk.kalhauge.openglutils.exceptions;

public class UniformDoesNotExistException extends Exception {
	private static final long serialVersionUID = -8300039291812809000L;
	
	private String name;
	public UniformDoesNotExistException(String name) {
		this.name = name;
	}
	
	@Override
	public String getMessage() {
		return "Uniform "+ name + " does not exist";
	}

}
