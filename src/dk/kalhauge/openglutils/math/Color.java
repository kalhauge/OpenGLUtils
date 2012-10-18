package dk.kalhauge.openglutils.math;



public class Color extends Vec4 {
	
	public static final Color RED   = new Color(1, 0, 0, 1);
	public static final Color BLUE  = new Color(0, 1, 0, 1);
	public static final Color GREEN = new Color(0, 0, 1, 1);
	public static final Color WHITE = new Color(1, 1, 1, 1);
	public static final Color BLACK = new Color(0, 0, 0, 1);;
	
	public Color(float r, float g, float b, float a){
		super(r,g,b,a);
	}

}
