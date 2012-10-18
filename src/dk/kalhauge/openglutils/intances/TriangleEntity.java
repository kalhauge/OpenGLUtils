package dk.kalhauge.openglutils.intances;

import android.opengl.GLES20;
import dk.kalhauge.openglutils.core.Draworder;
import dk.kalhauge.openglutils.core.Entity;
import dk.kalhauge.openglutils.core.Utils;
import dk.kalhauge.openglutils.core.VertexAttributeArray;
import dk.kalhauge.openglutils.math.Color;

public class TriangleEntity extends Entity {
	
	public TriangleEntity() {
		short[] indices = {0,1,2};
		float[] positions = {0,0.5f,
							-0.5f,-0.5f,
							 0.5f,-0.5f};
		float[] texcoord = {0.5f,1f,
				0f,0f,
				 1f,0f};
		
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
		