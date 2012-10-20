package dk.kalhauge.openglutils.math;

import java.nio.FloatBuffer;
import java.util.Arrays;

import android.opengl.GLES20;
import dk.kalhauge.openglutils.core.Utils;
import dk.kalhauge.openglutils.interfaces.ShaderAttachable;

public class Vec4 implements ShaderAttachable{
	
	float[] values;
	
	public Vec4(){
		values = new float[4];
	}
	
	public Vec4(float[] fs) {
		if(fs.length != 4) throw new IllegalArgumentException("Array must be of lenght 4, not " + fs.length);
		values = fs.clone();
	}
	
	public Vec4(float x, float y, float z, float w) {
		values = new float[4];
		values[0] = x;
		values[1] = y;
		values[2] = z;
		values[3] = w;
	}
	
	public Vec4(Vec3 vec) {
		values = new float[4];
		values[0] = vec.values[0];
		values[1] = vec.values[1];
		values[2] = vec.values[2];
		values[3] = 1;
	}
	
	public String toString() {
		return Arrays.toString(values);
	}
	
	public void attach(int location) {
		GLES20.glUniform4fv(location,1, values, 0);	
	}
	
	public static FloatBuffer createBuffer(int size) {
		FloatBuffer buffer = Utils.createBuffer(size*4*4).asFloatBuffer();
		return buffer;
	}
	
	public static FloatBuffer createBuffer(Vec4[] array) {
		FloatBuffer buffer = Utils.createBuffer(array.length*4*4).asFloatBuffer();
		for(Vec4 v : array) {
			buffer.put(v.values);
		}
		buffer.flip();
		return buffer;
	}

	public float[] getValues() {
		return values.clone();
	}


}
