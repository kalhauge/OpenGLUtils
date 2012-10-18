package dk.kalhauge.openglutils.core;

import java.nio.ShortBuffer;

import static android.opengl.GLES20.*;


public class Draworder{
	private ShortBuffer buffer;
	private int length;
	private int mode;
	
	public Draworder(int mode, short[] indicies) {
		buffer = Utils.createBufferFrom(indicies);
		length = indicies.length;
		this.mode = mode; 
	}
	
	public Draworder(int mode) {
		this.mode = mode;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public void draw(){
		if(buffer != null) 
			glDrawElements(mode, length, GL_UNSIGNED_SHORT, buffer);
		else 
			glDrawArrays(mode, 0, length);
	}
}
