package dk.kalhauge.openglutils.core;

import dk.kalhauge.openglutils.math.Color;
import dk.kalhauge.openglutils.math.Matrix;
import dk.kalhauge.openglutils.math.Vec3;
import dk.kalhauge.openglutils.math.Vec4;

public class Light {
	private Color ambient = Color.BLACK;
	private Color diffuse = Color.BLACK;
	private Color specular = Color.BLACK;
	
	private Vec3 position = new Vec3(0,0,0);

	public Color getAmbient() {
		return ambient;
	}

	public void setAmbient(Color ambient) {
		this.ambient = ambient;
	}

	public Color getDiffuse() {
		return diffuse;
	}

	public void setDiffuse(Color diffuse) {
		this.diffuse = diffuse;
	}

	public Color getSpecular() {
		return specular;
	}

	public void setSpecular(Color specular) {
		this.specular = specular;
	}

	public Vec3 getPosition() {
		return position;
	}

	public void setPosition(Vec3 position) {
		this.position = position;
	}
	
	public void attachTo(Matrix mvMatrix, ShaderProgram p){
		p.setup(Utils.AMBIENT_COLOR, ambient);
		p.setup(Utils.DIFFUSE_COLOR, diffuse);
		p.setup(Utils.SPECULAR_COLOR, specular);
		p.setup(Utils.LIGHT_POS, new Vec3(Matrix.multiply(mvMatrix, position)));
	}

}
