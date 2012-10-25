package dk.kalhauge.openglutils.core;

import java.util.HashMap;
import java.util.Map.Entry;

import dk.kalhauge.openglutils.exceptions.EntityDoesNotHaveAbilityException;
import dk.kalhauge.openglutils.interfaces.ShaderAttachable;
import dk.kalhauge.openglutils.math.Color;
import dk.kalhauge.openglutils.math.Scalar;

public class Entity{
	private HashMap<Integer, ShaderAttachable> attachables = new HashMap<Integer, ShaderAttachable>();
	private Draworder order;
	
	public Entity(){
		registerAttachable(Utils.ENTITY_COLOR, Color.GREEN);
		registerAttachable(Utils.ENTITY_SHININESS,Scalar.ONE);
	}
	
	public ShaderAttachable getAttachable(int ability) {
		ShaderAttachable a = attachables.get(ability);
		if(a == null) 
			throw new EntityDoesNotHaveAbilityException();
		else return a;
	}
		
	public void registerAttachable(int ability, ShaderAttachable attachable){
		attachables.put(ability, attachable);
	}
	
	public void setDraworder(Draworder draworder){
		order = draworder;
	}
	
	public void draw(ShaderProgram program) {
		for(Entry<Integer, ShaderAttachable> entry : attachables.entrySet()) {
			program.setup(entry.getKey(), entry.getValue());
		}
		order.draw();
	}
	
	public void refresh(){
		// donothing
	}
}
