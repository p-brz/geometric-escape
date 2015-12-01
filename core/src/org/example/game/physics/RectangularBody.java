package org.example.game.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class RectangularBody extends BasicBody{
	private Vector2 size;

	public RectangularBody(float width, float height) {
		this(new Vector2(width, height), null);
	}
	public RectangularBody(Vector2 size) {
		this(size, null);
	}
	public RectangularBody(Vector2 size, Material material) {
		this.size = new Vector2(size);
		if(material != null){
			this.setMaterial(material);
		}
	}
	
	public Vector2 getSize() {
		return size;
	}
	public void setSize(Vector2 size) {
		this.size = size;
	}
	
	@Override
	public Shape buildShape() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.x/2f, size.y/2f);
		return shape;
	}
}
