package org.example.game.pathfinding;

public class MapNode {
	float x, y;
	final float epsilon;
	
	public MapNode(float x, float y) {
		this.x = x;
		this.y = y;
		epsilon = 0.005f;
	}

	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		MapNode other = (MapNode) obj;
		if(Math.abs(other.getX() - this.getX()) > epsilon) return false;
		if(Math.abs(other.getY() - this.getY()) > epsilon) return false;		
		
		return true;
	}

	@Override
	public String toString() {
		return String.format("(%f, %f)", x, y);
	}
	
}
