package org.example.game.physics;

public class Material {
	float density;

	public Material() {
		this(1);
	}
	public Material(float density) {
		this.density = density;
	}
	
	public float getDensity() {
		return density;
	}
	public void setDensity(float density) {
		this.density = density;
	}
}
