package dk.kalhauge.openglutils.core;

import static android.opengl.GLES20.*;
import android.util.Log;
import dk.kalhauge.openglutils.exceptions.AttributeDoesNotExistException;
import dk.kalhauge.openglutils.exceptions.CompileException;
import dk.kalhauge.openglutils.exceptions.UniformDoesNotExistException;

public class ShaderProgram {
	public static int compileShader(int type, String shaderCode) throws CompileException{
		
		
	    // Important stuff.
		int shader = glCreateShader(type);  // Create a Shader object
	    glShaderSource(shader, shaderCode); // Set the source of to compile
	    glCompileShader(shader);			// and compile. Simple stuff.
	    
	    // Check if the compiling went as planned. It seams complicated,
	    // but its really not.
	    int[] info = new int[1];
	    glGetShaderiv(shader, GL_COMPILE_STATUS, info, 0);
	    if(info[0] == GL_FALSE){
	    	glDeleteShader(shader); // In OpenGL always clean up your mess.
	    	throw new CompileException("Compiling failed: " + glGetShaderInfoLog(shader));
	    }
	    
	    return shader;
	}
	
	public static int linkProgram(int vertexShader, int fragmentShader) throws CompileException {
		int program = glCreateProgram(); // Creates a program id.
		glAttachShader(program, vertexShader); // Attaches the vertex Shader
		glAttachShader(program, fragmentShader); // Attached the fragment Shader
		glLinkProgram(program); //Link the program.
		
		 int[] info = new int[1];
		    glGetProgramiv(program, GL_LINK_STATUS, info, 0);
		    if(info[0] == GL_FALSE){
		    	glDeleteProgram(program); // In OpenGL always clean up your mess.
		    	throw new CompileException("Linking failed: " + glGetProgramInfoLog(program));
		    }
		return program;
	}
	
	private String vertexShader = null;
	private String fragmentShader = null;
	private int programId;
	private boolean compiled;
	private int[] attached = new int[Ability.size];
	
	public ShaderProgram(){
		for(int i = 0; i < Ability.size; i++) attached[i] = -1;
	}
	
	public void use() {
		if(isCompiled())
			glUseProgram(programId);
		else throw new RuntimeException("Can't use a program before it's compiled");
	}
	
	public void compile() throws CompileException{
		if(vertexShader == null || fragmentShader == null) {
			// First we load the shaders and compiles them to the GPU.
			int vertexShaderId = compileShader(GL_VERTEX_SHADER, vertexShader);
			int fragmentShaderId = compileShader(GL_FRAGMENT_SHADER, fragmentShader);
			// Then we link the program 
			programId = linkProgram(vertexShaderId, fragmentShaderId);
			
/*TODO			// And since we don't need the shaders anymore we delete them
			// to conserve memory.
			glDeleteShader(vertexShaderId);
			glDeleteShader(fragmentShaderId); */
			compiled = true;
		}else throw new CompileException("Vector or fragmentshader not set");
	}
	
	public void cleanUp(){
		if(isCompiled()){
			glDeleteProgram(programId);
		}
	}
	
	public void setAttributeAbility(int ability, String name) throws AttributeDoesNotExistException{
		attached[ability] = getAttributeLocation(name);
	}
	
	public void setUniformAbility(int ability, String name) throws UniformDoesNotExistException{
		attached[ability] = getUniformLocation(name);
	}
	
	public int getAbility(int ability) {
		return attached[ability];
	}
	
	private int getUniformLocation(String name) throws UniformDoesNotExistException{
		int uniform = glGetUniformLocation(programId, name);
		if(uniform == -1) throw new UniformDoesNotExistException(name);
		return uniform;
	}
	
	private int getAttributeLocation(String name) throws AttributeDoesNotExistException{
		int attribute =  glGetAttribLocation(programId, name);
		if(attribute == -1) Log.e("ShaderProgram","Attribute does not exist : " + name);
		return attribute;
	}
	
	public void setVectorShader(String vectorShader) {
		this.vertexShader = vectorShader;
	}
	
	public void setFragmentShader(String fragmentShader) {
		this.fragmentShader = fragmentShader;
	}
	
	public boolean isCompiled() {
		return compiled;
	}

	
}

