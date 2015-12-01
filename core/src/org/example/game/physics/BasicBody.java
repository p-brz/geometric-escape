package org.example.game.physics;

public abstract class BasicBody implements PhysicalBody{
	private Material material;

	public BasicBody() {
		material = new Material();
	}
	
	@Override
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
}
