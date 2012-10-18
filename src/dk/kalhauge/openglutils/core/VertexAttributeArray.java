package dk.kalhauge.openglutils.core;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glVertexAttribPointer;

import java.nio.FloatBuffer;

import dk.kalhauge.openglutils.interfaces.ShaderAttachable;
import dk.kalhauge.openglutils.math.Vec3;
import dk.kalhauge.openglutils.math.Vec4;


public class VertexAttributeArray implements ShaderAttachable {
	private FloatBuffer buffer;
	private int floatsPerVertex;
	
	public void setFloatsPerVertex(int floatsPerVertex) {
		this.floatsPerVertex = floatsPerVertex;
	}

	public void fillBuffer(float[] array,int floatsPerVertex){
		setFloatsPerVertex(floatsPerVertex);
		buffer = Utils.createBufferFrom(array);
	}
	
	public void fillBuffer(Vec3[] array){
		setFloatsPerVertex(3);
		buffer = Vec3.createBuffer(array);
	}
	
	public void fillBuffer(Vec4[] array){
		setFloatsPerVertex(4);
		buffer = Vec4.createBuffer(array);
	}
	
	public void fillBuffer(FloatBuffer buffer,int floatsPerVertex){
		setFloatsPerVertex(floatsPerVertex);
		this.buffer = buffer;
	}
	
	public void attach(int location) {
		glEnableVertexAttribArray(location);
		glVertexAttribPointer(location, floatsPerVertex, GL_FLOAT, false, 0, buffer);
	}
	
	public void put(int index, Vec3 point) {
		buffer.position(index*floatsPerVertex);
		buffer.put(point.getValues(), 0, 3);
		buffer.position(0);
	}
	
	public void put(int index, Vec4 point) {
		buffer.position(index*floatsPerVertex);
		buffer.put(point.getValues(), 0, floatsPerVertex);
		buffer.position(0);
	}
	
}
