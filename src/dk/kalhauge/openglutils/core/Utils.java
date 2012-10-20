package dk.kalhauge.openglutils.core;

import static android.opengl.GLES20.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.graphics.Bitmap;
import android.opengl.GLUtils;
import android.util.Log;

public class Utils {
	
public static int size = 0;
	
	public static final int 
	ENTITY_COLOR = size++,
	VERTEX_POS = size++,
	VERTEX_NORMAL = size++,
	VERTEX_COLOR = size++,
	VERTEX_TEX_COORD = size++,
	VERTEX_TEX_COORD1 = size++;
	
	public static final int
	MODELVIEW_PROJECTION_MATRIX = size++,
	MODELVIEW_MATRIX = size++,
	NORMAL_MATRIX = size++;

	
	public static final int
	TEXTURE_0 = size++,
	TEXTURE_1 = size++;
	
	public static final int
	SPECIAL_UNIFORM_0 = size++,
	SPECIAL_UNIFORM_1 = size++,
	SPECIAL_UNIFORM_2 = size++,
	SPECIAL_UNIFORM_3 = size++;
	
	public static final int
	SPECIAL_ATTRIBUTE_0 = size++,
	SPECIAL_ATTRIBUTE_1 = size++,
	SPECIAL_ATTRIBUTE_2 = size++;
	
	
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
	

}
