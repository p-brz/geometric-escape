package org.example.game.steering;

import com.badlogic.gdx.math.Vector2;

public class SeekBehavior implements SteeringBehavior{
	private Vector2 target;
	
	public Vector2 getTarget() {
		return target;
	}
	public void setTarget(float x, float y) {
		this.setTarget(new Vector2(x,y));
	}
	public void setTarget(Vector2 target) {
		this.target = target;
	}

	@Override
	public void apply(ControlledCharacter character) {
		Vector2 force = calculateSteeringForce(character);
		character.applyForce(force);
	}
	public Vector2 calculateSteeringForce(ControlledCharacter character) {
		return calculateSteeringDir(character).scl(character.getDeltaVelocity());
	}
	public Vector2 calculateSteeringDir(ControlledCharacter character){
		return calculateSeekVelocity(character).sub(character.getVelocity()).nor();
	}
	public Vector2 calculateSeekVelocity(ControlledCharacter character) {
		return calculateSeekDir(character).scl(character.getVelocityMag());
	}
	
	public Vector2 calculateSeekDir(ControlledCharacter character) {
		Vector2 diff = getTarget().cpy().sub(character.getPosition());
		return diff.nor();
	}
}
