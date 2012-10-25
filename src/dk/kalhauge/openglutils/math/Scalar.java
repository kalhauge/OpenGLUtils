package dk.kalhauge.openglutils.math;

import android.opengl.GLES20;
import dk.kalhauge.openglutils.interfaces.ShaderAttachable;

public class Scalar implements ShaderAttachable{
	private float data;
	
	public static Scalar NILL = new Scalar(0);
	public static Scalar ONE = new Scalar(1);

	public Scalar(float data) {
		this.data = data;
	}
	
	public void attach(int position) {
		GLES20.glUniform1f(position, data);
	}

}
