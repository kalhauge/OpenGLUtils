package dk.kalhauge.openglutils.exceptions;

public class AttributeDoesNotExistException extends Exception {
	private static final long serialVersionUID = -4861781047578555240L;

	private String name;
	public AttributeDoesNotExistException(String name) {
		this.name = name;
	}
	
	@Override
	public String getMessage() {
		return "Uniform "+ name + " does not exist";
	}

}
