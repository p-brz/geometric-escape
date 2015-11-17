package org.example.game.prototypes;

import org.example.game.steering.ControlledCharacter;

import com.badlogic.gdx.math.Vector2;

public class PrototypeCharacter implements ControlledCharacter{
	Vector2 position, velocity;
	float velocityMagnitude;
	float deltaVelocity;
	
	public PrototypeCharacter() {
		position = new Vector2(0,0);
		velocity = new Vector2(0,0);
		velocityMagnitude = 5f;
		deltaVelocity = 1f;
	}
	
	
	@Override
	public Vector2 getPosition() {
		return position;
	}

	@Override
	public Vector2 getVelocity() {
		return velocity;
	}

	@Override
	public float getVelocityMag() {
		return velocityMagnitude;
	}

	@Override
	public float getDeltaVelocity() {
		return deltaVelocity;
	}

	@Override
	public void applyForce(Vector2 force) {
		velocity.add(force);
	}
	void update(){
		position.add(velocity);
	}
}