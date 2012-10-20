package dk.kalhauge.openglutils.intances;

import android.opengl.GLES20;
import dk.kalhauge.openglutils.core.Draworder;
import dk.kalhauge.openglutils.core.Entity;
import dk.kalhauge.openglutils.core.Utils;
import dk.kalhauge.openglutils.core.VertexAttributeArray;
import dk.kalhauge.openglutils.extras.PositionEntity;
import dk.kalhauge.openglutils.math.Color;
import dk.kalhauge.openglutils.math.Matrix;
import dk.kalhauge.openglutils.math.Vec4;

public class CubeEntity extends PositionEntity {
	
	private float[] vPos = {
			1,1,1,
			1,-1,1,
			-1,1,1,
			-1,-1,1,
			
			1,1,1,
			1,1,-1,
			-1,1,1,
			-1,1,-1,
			
			1,1,-1,
			1,-1,-1,
			-1,1,-1,
			-1,-1,-1,
			
			1,-1,1,
			1,-1,-1,
			-1,-1,1,
			-1,-1,-1,
			
			-1,1,1,
			-1,1,-1,
			-1,-1,-1,
			-1,-1,1,
			
			1,1,1,
			1,1,-1,
			1,-1,-1,
			1,-1,1,
			
	};
	
	private float[] vTexCoord = {
			0,0,
			0,1,
			1,0,
			1,1,

			0,0,
			0,1,
			1,0,
			1,1,
			
			0,0,
			0,1,
			1,0,
			1,1,
			
			0,0,
			0,1,
			1,0,
			1,1,
			
			0,0,
			0,1,
			1,0,
			1,1,
			
			0,0,
			0,1,
			1,0,
			1,1,
	};
	
	private float[] vNorm = {
			0,0,1,
			0,0,1,
			0,0,1,
			0,0,1,
			
			0,1,0,
			0,1,0,
			0,1,0,
			0,1,0,
			
			0,0,-1,
			0,0,-1,
			0,0,-1,
			0,0,-1,
			
			0,-1,0,
			0,-1,0,
			0,-1,0,
			0,-1,0,
			
			-1,0,0,
			-1,0,0,
			-1,0,0,
			-1,0,0,
			
			1,0,0,
			1,0,0,
			1,0,0,
			1,0,0,
	};
	
	private Vec4[] vColor = {
			Color.BLUE,
			Color.BLUE,
			Color.BLUE,
			Color.BLUE,
			
			Color.GREEN,
			Color.GREEN,
			Color.GREEN,
			Color.GREEN,
			
			Color.BLUE,
			Color.BLUE,
			Color.BLUE,
			Color.BLUE,
			
			Color.GREEN,
			Color.GREEN,
			Color.GREEN,
			Color.GREEN,
			
			Color.RED,
			Color.RED,
			Color.RED,
			Color.RED,
		
			Color.RED,
			Color.RED,
			Color.RED,
			Color.RED
	};
	
	private short[] indicies = {
			0,1,2,
			2,1,3,
			
			4,6,5,
			6,7,5,
			
			8,10,9,
			10,11,9,
			
			12,13,14,
			14,13,15,
			
			17,16,18,
			18,16,19,
			
			20,21,22,
			20,22,23,			
	};

	
	public CubeEntity() {
		VertexAttributeArray pos = new VertexAttributeArray();
		pos.fillBuffer(vPos, 3);
		
		VertexAttributeArray normal = new VertexAttributeArray();
		normal.fillBuffer(vNorm, 3);
		
		VertexAttributeArray texture = new VertexAttributeArray();
		texture.fillBuffer(vTexCoord, 2);
		
		VertexAttributeArray color = new VertexAttributeArray();
		color.fillBuffer(vColor);
		
		setDraworder(new Draworder(GLES20.GL_TRIANGLES, indicies));
		
		registerAttachable(Utils.VERTEX_POS,pos);
		registerAttachable(Utils.VERTEX_NORMAL, normal);
		registerAttachable(Utils.VERTEX_TEX_COORD, texture);
		registerAttachable(Utils.VERTEX_COLOR, color);
		registerAttachable(Utils.ENTITY_COLOR, Color.GREEN);
	}
	
}
