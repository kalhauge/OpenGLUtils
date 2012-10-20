package dk.kalhauge.openglutils.math;

import java.nio.FloatBuffer;
import java.util.Arrays;

import android.opengl.GLES20;
import dk.kalhauge.openglutils.core.Utils;
import dk.kalhauge.openglutils.interfaces.ShaderAttachable;

public class Vec3 implements ShaderAttachable{
	
	public static final Vec3 ORIGO = new Vec3(0, 0, 0);
	
	float[] values;
	
	public Vec3(float[] fs) {
		if(fs.length != 3) throw new IllegalArgumentException("Array must be of lenght 3, not " + fs.length);
		values = fs.clone();
	}
	
	public Vec3(Vec3 old) {
		values = old.values.clone();
	}
	
	public Vec3(Vec4 old) {
		this(old.values[0] / old.values[3],old.values[1] / old.values[3],old.values[2] / old.values[3]);
	}
	
	public Vec3(float x, float y, float z) {
		values = new float[3];
		values[0] = x;
		values[1] = y;
		values[2] = z;
	}
	
	public String toString() {
		return Arrays.toString(values);
	}

	public void attach(int location) {
		GLES20.glUniform3fv(location,1, values, 0);	
	}
	
	public float[] getValues(){
		return values.clone();
	}
	
	public static FloatBuffer createBuffer(int size) {
		FloatBuffer buffer = Utils.createBuffer(size*3*4).asFloatBuffer();
		return buffer;
	}
	
	public static FloatBuffer createBuffer(Vec3[] array) {
		FloatBuffer buffer = Utils.createBuffer(array.length*3*4).asFloatBuffer();
		for(Vec3 v : array) {
			buffer.put(v.values);
		}
		buffer.flip();
		return buffer;
	}
	
	public Vec3 clone(){
		return new Vec3(this);
	}

}
