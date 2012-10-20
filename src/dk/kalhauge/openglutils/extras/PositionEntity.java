package dk.kalhauge.openglutils.extras;

import android.util.Log;
import dk.kalhauge.openglutils.core.Entity;
import dk.kalhauge.openglutils.core.Utils;
import dk.kalhauge.openglutils.math.Matrix;

public class PositionEntity extends Entity{
	private Matrix position = Matrix.identity();
	
	public void setPosition(Matrix position) {
		this.position = position;
	}
	
	public Matrix getPosition() {
		return position;
	}
	
	public void createMatrices(Matrix projection, Matrix view) {
		Matrix mvMatrix = Matrix.multiply(view, position);
		Matrix imvMatrix = mvMatrix.invert();
		Matrix nMatrix = imvMatrix.transpose();
				
		registerAttachable(Utils.MODELVIEW_MATRIX, mvMatrix);
		registerAttachable(Utils.MODELVIEW_PROJECTION_MATRIX, Matrix.multiply(projection, mvMatrix));
		
		registerAttachable(Utils.NORMAL_MATRIX, nMatrix);
		registerAttachable(Utils.INVERSE_MODELVIEW_MATRIX,imvMatrix);
	}
	
	public static void createMatrices(Matrix projection, Matrix view, PositionEntity ... entities) {
		for(PositionEntity entity: entities) entity.createMatrices(projection, view);
	}

}
