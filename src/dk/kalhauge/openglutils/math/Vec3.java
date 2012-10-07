package dk.kalhauge.openglutils.math;

import java.lang.reflect.Array;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.opengl.GLES20;
import dk.kalhauge.openglutils.Utils;
import dk.kalhauge.openglutils.core.ShaderAttachable;

public class Vec3 implements ShaderAttachable{
	
	public static final Vec3 ORIGO = new Vec3(0, 0, 0);
	
	private float[] values;
	
	public Vec3(float[] fs) {
		if(fs.length != 3) throw new IllegalArgumentException("Array must be of lenght 3, not " + fs.length);
		values = fs.clone();
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
	
	public static FloatBuffer createBuffer(Vec3[] array) {
		FloatBuffer buffer = Utils.createBuffer(array.length*3*4).asFloatBuffer();
		for(Vec3 v : array) {
			buffer.put(v.values);
		}
		buffer.flip();
		return buffer;
	}

}
