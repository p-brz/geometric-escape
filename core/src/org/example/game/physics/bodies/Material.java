package org.example.game.physics.bodies;

/** Uma classe que encapsula as propriedades físicas utilizadas para criar um body*/
public class Material {
	private float density;
	private boolean allowRotation;
	
	public Material() {
		this(1);
	}
	public Material(float density) {
		this.density = density;
		this.allowRotation = false;
	}
	
	public float getDensity() {
		return density;
	}
	public void setDensity(float density) {
		this.density = density;
	}
	
	public boolean getAllowRotation() {
		return allowRotation;
	}
	public void setAllowRotation(boolean allowRotation) {
		this.allowRotation = allowRotation;
	}
	
	
}
