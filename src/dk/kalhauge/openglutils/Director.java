package dk.kalhauge.openglutils;

import java.util.Collection;

import android.content.Entity;

import dk.kalhauge.openglutils.core.Output;
import dk.kalhauge.openglutils.core.ShaderProgram;
import dk.kalhauge.openglutils.core.ShaderSetup;

public class Director {
	private ShaderProgram program;
	private Output output;
	private ShaderSetup setup;
	
	public void draw(Collection<Entity> entities){
		program.use();
		
		
	}

}
