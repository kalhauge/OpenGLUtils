package dk.kalhauge.openglutils.core;

import android.opengl.GLES20;
import dk.kalhauge.openglutils.exceptions.EntityDoesNotHaveAbilityException;

public class Entity{
	private ShaderAttachable[] attachables = new ShaderAttachable[Ability.size]; 
	public Entity() {
		
	}

	public ShaderAttachable getAttachable(int ability) throws EntityDoesNotHaveAbilityException {
		if(attachables[ability] == null) 
			throw new EntityDoesNotHaveAbilityException();
		else return attachables[ability];
	}
		
	public void registerAttachable(int ability, ShaderAttachable attachable){
		attachables[ability] = attachable;
	}
	
	public void draw() {
	}
}
