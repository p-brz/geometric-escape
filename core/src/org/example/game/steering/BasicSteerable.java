package org.example.game.steering;

import com.badlogic.gdx.math.Vector2;

public class BasicSteerable implements Steerable{
	Vector2 position, velocity, posIncrement;
	float velocityMagnitude;
	float deltaVelocity;
	
	public BasicSteerable() {
                posIncrement = new Vector2();
		position = new Vector2(0,0);
		velocity = new Vector2(0,0);
		velocityMagnitude = 0.0000000001f;
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
	public void setVelocityMag(float vel){
		this.velocityMagnitude = vel;
	}

	@Override
	public float getDeltaVelocity() {
		return deltaVelocity;
	}

	@Override
	public void applyForce(Vector2 force) {
		velocity.add(force);
	}
	
	@Override
	public void update(float deltaT)
	{
            posIncrement.set(velocity);
            posIncrement.scl(deltaT);
            position.add(velocity);
	}
}