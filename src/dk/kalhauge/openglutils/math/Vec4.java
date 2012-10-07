package dk.kalhauge.openglutils.math;

import java.nio.FloatBuffer;
import java.util.Arrays;

import android.opengl.GLES20;
import dk.kalhauge.openglutils.ShaderAttachable;
import dk.kalhauge.openglutils.Utils;

public class Vec4 implements ShaderAttachable{
	
	public float[] values;
	
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
	public String toString() {
		return Arrays.toString(values);
	}
	
	public void attach(int location) {
		GLES20.glUniform4fv(location,1, values, 0);	
	}
	
	public static FloatBuffer createBuffer(Vec4[] array) {
		FloatBuffer buffer = Utils.createBuffer(array.length*4*4).asFloatBuffer();
		for(Vec4 v : array) {
			buffer.put(v.values);
		}
		buffer.flip();
		return buffer;
	}

}
