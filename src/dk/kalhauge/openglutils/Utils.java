package dk.kalhauge.openglutils;

import static android.opengl.GLES20.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class Utils {
	
	@Deprecated
	public static void put(float[] points, int i, float x, float y, float z) {
		points[i*3] = x;
		points[i*3 + 1] = y;
		points[i*3 + 2] = z;
	}
	
	/**
	 * Creates a ByteBuffer from a size.
	 * @param size
	 * @return
	 */
	
	public static ByteBuffer createBuffer(int size){
		ByteBuffer buffer = ByteBuffer.allocateDirect(size);
		buffer.order(ByteOrder.nativeOrder());
		return buffer;
	}
	
	/**
	 * Creates a FloatBuffer from an array of floats.
	 * @param array
	 * @return
	 */
	
	public static FloatBuffer createBufferFrom(float[] array){
		FloatBuffer buffer = createBuffer(array.length*4).asFloatBuffer();
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	/**
	 * Creates a ShortBuffer from an array of shorts.
	 * @param array
	 * @return
	 */
	
	
	public static ShortBuffer createBufferFrom(short[] array){
		ShortBuffer buffer = createBuffer(array.length*2).asShortBuffer();
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	
	/**
	 * Loads a texture onto the GPU, at position that texture_id points to.
	 * @param bitmap
	 * @param texture_id
	 */
	
	public static void loadTexture(Bitmap bitmap,int texture_id){
		glBindTexture(GL_TEXTURE_2D,texture_id);
		glPixelStorei(GL_UNPACK_ALIGNMENT, GL_TRUE);
		
		GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		Log.v("Utils", bitmap.getWidth() + ", " + bitmap.getHeight());
	    Log.v("Utils", "Texture is at " + texture_id); 
	    bitmap.recycle();
	}	    
}
