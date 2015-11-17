package org.example.game.steering;

import com.badlogic.gdx.math.Vector2;

public interface ControlledCharacter {
	public Vector2 getPosition();

	public Vector2 getVelocity();

	public float getVelocityMag();

	public float getDeltaVelocity();

	public void applyForce(Vector2 force);
}
