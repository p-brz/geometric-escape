package org.example.test.helper;

import com.badlogic.gdx.math.Vector2;

public class VectorHelper {
	public static final double EPSILON = 0.000001;
	
	public static boolean isEquals(double d1, double d2){
		return Math.abs(d1 - d2) < EPSILON;
	}
	public static boolean isEquals(Vector2 v1, Vector2 v2) {
		return isEquals(v1.x, v2.x)
				&& isEquals(v1.y, v2.y);
	}

}
