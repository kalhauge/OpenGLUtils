package dk.kalhauge.openglutils.core;

import static java.lang.Math.PI;
import dk.kalhauge.openglutils.math.Matrix;

public class ArcBallCamera {
	
	private float distance;
	private float angle_x;
	private float angle_y;
	
	private Matrix view;
	
	public void reset(){
		distance = 0;
		angle_x = 0;
		angle_y = 0;
	}
	
	public ArcBallCamera(float distance){
		setDistance(distance);
	}
	
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public void rotateX(float rad){
		angle_x += rad;
		if(angle_x > PI/2.0) angle_x = (float) (PI/2.0);
		else if(angle_x < -PI/2.0) angle_x = (float) (-PI/2.0 );
	}
	
	public void rotateY(float rad){
		angle_y += rad;
	}
		
	public Matrix getViewMatrix(){
		view = Matrix.lookAt(0, 0, distance, 0, 0, 0, 0, 1, 0);
		
		view.rotate((float) (angle_x*180/PI), 1, 0,0);
		view.rotate((float) (angle_y*180/PI), 0, 1,0);
		
		return view;
	}
}
