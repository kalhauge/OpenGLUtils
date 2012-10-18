package dk.kalhauge.openglutils.intances;

import android.opengl.GLES20;
import dk.kalhauge.openglutils.core.Draworder;
import dk.kalhauge.openglutils.core.Entity;
import dk.kalhauge.openglutils.core.Utils;
import dk.kalhauge.openglutils.core.VertexAttributeArray;
import dk.kalhauge.openglutils.math.Color;

public class FullScreenEntity extends Entity {
	
	public FullScreenEntity() {
		short[] indices = {0,1,2,2,0,3};
		float[] positions = {
				-1f,-1f,
				-1f, 1f,
				 1f, 1f,
				 1f,-1f};
		float[] texcoord = {
				0f,1f,
				0f,0f,
				1f,0f,
				1f,1f};
		
		VertexAttributeArray pos = new VertexAttributeArray();
		pos.fillBuffer(positions,2);
		
		VertexAttributeArray texcoords = new VertexAttributeArray();
		texcoords.fillBuffer(texcoord, 2);
		
		setDraworder(new Draworder(GLES20.GL_TRIANGLES, indices));
		registerAttachable(Utils.VERTEX_POS, pos);
		registerAttachable(Utils.VERTEX_TEX_COORD, texcoords);
		registerAttachable(Utils.ENTITY_COLOR, Color.RED);
		
	}

}
		