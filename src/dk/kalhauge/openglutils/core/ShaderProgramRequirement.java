package dk.kalhauge.openglutils.core;

import java.util.ArrayList;

import dk.kalhauge.openglutils.exceptions.AttributeDoesNotExistException;
import dk.kalhauge.openglutils.exceptions.UniformDoesNotExistException;

public class ShaderProgramRequirement {

	static final int ATTRIBUTE_COLOR = 0;
	
	ArrayList<Requirement> attributes = new ArrayList<Requirement>();
	ArrayList<Requirement> setup_uniforms = new ArrayList<Requirement>();
	ArrayList<Requirement> entity_uniforms = new ArrayList<Requirement>();
	
	public void addAttributeRequirement(String name, int function) {
		attributes.add(new Requirement(name, function));
	}
	
	public void addSetupUniformRequirement(String name, int function) {
		setup_uniforms.add(new Requirement(name, function));
	}
	
	public void addEntityUniformRequirement(String name, int function) {
		entity_uniforms.add(new Requirement(name, function));
	}
	
	public void attachToProgram(ShaderProgram program) throws AttributeDoesNotExistException, UniformDoesNotExistException{
		for(Requirement re : attributes) {
			re.location = program.getAttributeLocation(re.name);
		}
		for(Requirement re : setup_uniforms) {
			re.location = program.getUniformLocation(re.name);
		}
		for(Requirement re : entity_uniforms) {
			re.location = program.getUniformLocation(re.name);
		}
	}
	
	private class Requirement {
		public Requirement(String name, int function) {
			this.name = name;
			this.function = function;
		}
		public String name;
		public int function;
		public int location;
	}
}
