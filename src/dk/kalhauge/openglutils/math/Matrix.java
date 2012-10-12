package dk.kalhauge.openglutils.math;

import static android.opengl.Matrix.frustumM;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.multiplyMV;
import static android.opengl.Matrix.rotateM;
import static android.opengl.Matrix.setLookAtM;
import static android.opengl.Matrix.translateM;
import android.opengl.GLES20;
import dk.kalhauge.openglutils.core.ShaderAttachable;
import dk.kalhauge.openglutils.core.ShaderProgram;
public class Matrix implements ShaderAttachable {

	public float[] values;
	
	private Matrix() {
		values = new float[16];
	}

	private Matrix(Matrix m) {
		values = m.values.clone();
	}
	
	public static Matrix fustrum(
			float left,float right,
			float bottom,float top,
			float near,float far){
		Matrix m = new Matrix();
		frustumM(m.values, 0, left, right, bottom, top, near, far);
		return m;
	}
	
	public static Matrix lookAt(
			float eyeX, float eyeY, float eyeZ,
			float centerX, float centerY, float centerZ,
			float upX ,float upY , float upZ){
		Matrix m = new Matrix();
		setLookAtM(m.values, 0, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
		return m;
	}
	
	public static Matrix identity() {
		Matrix m = new Matrix();
		m.values[0]  = 1;
		m.values[5]  = 1;
		m.values[10] = 1;
		m.values[15] = 1;
		return m;
	}
	
	public static Matrix multiply(Matrix m1, Matrix m2) {
		Matrix m = new Matrix();
		multiplyMM(m.values, 0, m1.values, 0, m2.values, 0);
		return m;
	}
	
	public static Vec4 multiply(Matrix m, Vec4 v_in){
		Vec4 v_out = new Vec4();
		multiplyMV(v_out.values, 0, m.values, 0, v_in.values, 0);
		return v_out;
	}
	
	public void rotate(float degrees, float x,float y, float z) {
		rotateM(values, 0, degrees, x, y, z);
	}
	
	public void translate(float x,float y, float z){
		translateM(values, 0, x, y, z);
	}
	
	public Matrix clone(){
		return new Matrix(this);
	};
	
	
	public float[] getValues() {
		return values;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		for(int r = 0; r < 4; r++){
			b.append("[");
			for(int c = 0; c < 4; c++){
				b.append(values[r*4+c]);
				if(c != 3) b.append(" ");
			}
			b.append("]\n");	
		}
		return b.toString();
	}

	public void attach(int location) {
		GLES20.glUniformMatrix4fv(location, 1, false, values, 0);
	}

}
