package dk.kalhauge.openglutils.intances;

import android.opengl.GLES20;
import dk.kalhauge.openglutils.core.Draworder;
import dk.kalhauge.openglutils.core.Utils;
import dk.kalhauge.openglutils.core.VertexAttributeArray;
import dk.kalhauge.openglutils.extras.PositionEntity;
import dk.kalhauge.openglutils.math.Vec3;

public class SphereEntity extends PositionEntity{

	
	public SphereEntity(int rings, int pieces) {
		int nodes = (rings+1)*pieces +2;
		Vec3[] positions = new Vec3[nodes];
		
		positions[0] = new Vec3(0, 1, 0);
		positions[1] = new Vec3(0, -1, 0);
		
	
		double a_y = Math.PI/(2+rings);
		double a_x = Math.PI*2/pieces;
		
		
		for(int p = 0; p < pieces; ++p) {
			for(int r = 0; r < rings+1; r++) {
				double size = Math.sin(a_y*(r+1));
				positions[2+r+p*(rings+1)] = 
						new Vec3(
								(float) (size*Math.cos(a_x*p)),
								(float) (Math.cos(a_y*(r+1))),
								(float) (size*Math.sin(a_x*p)));
			}
		}
		

		int indicis_pr_piece = (2 * rings + 2) * 3;
		
		short[] indicies = new short[indicis_pr_piece*pieces];
		
		for(int p = 0; p < pieces; ++p) {
			int i = 0; 
			indicies[p*indicis_pr_piece + i++] = 0;
			indicies[p*indicis_pr_piece + i++] = (short) (2 + p*(rings+1));
			indicies[p*indicis_pr_piece + i++] = (short) (2 + ((p+1)%pieces)*(rings+1));
			
			for(int r = 0; r < rings; ++r ){
				
				indicies[p*indicis_pr_piece + i++] = (short) (2 + r + ((p+1)%pieces)*(rings+1));
				indicies[p*indicis_pr_piece + i++] = (short) (2 + r + p*(rings+1));
				indicies[p*indicis_pr_piece + i++] = (short) (2 + r+1 + p*(rings+1));
				
				indicies[p*indicis_pr_piece + i++] = (short) (2 + r + ((p+1)%pieces)*(rings+1));
				indicies[p*indicis_pr_piece + i++] = (short) (2 + r+1 + p*(rings+1));
				indicies[p*indicis_pr_piece + i++] = (short) (2 + r+1 + ((p+1)%pieces)*(rings+1));
			}
			

			indicies[p*indicis_pr_piece + i++] = (short) (2 + rings + ((p+1)%pieces)*(rings+1));
			indicies[p*indicis_pr_piece + i++] = (short) (2 + rings + p*(rings+1));
			indicies[p*indicis_pr_piece + i++] = 1;
			
		}
		
		
		
		setDraworder(new Draworder(GLES20.GL_TRIANGLES,indicies));
		
		VertexAttributeArray pos = new VertexAttributeArray();
		pos.fillBuffer(positions);

		
		registerAttachable(Utils.VERTEX_POS, pos);
		registerAttachable(Utils.VERTEX_NORMAL, pos);
		registerAttachable(Utils.VERTEX_COLOR, pos);
		
		
	}
	
}
