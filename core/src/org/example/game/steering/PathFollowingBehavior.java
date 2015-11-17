package org.example.game.steering;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class PathFollowingBehavior implements SteeringBehavior{
	private int targetIndex;
	private final List<Vector2> path;
	private float reachDistance;
	
	private SeekBehavior seekBehaviour;

	public PathFollowingBehavior() {
		this(new SeekBehavior());
	}
	public PathFollowingBehavior(SeekBehavior seek) {
		path = new ArrayList<Vector2>();
		this.seekBehaviour = seek;
	}
	
	public int getTargetIndex() {
		return targetIndex;
	}

	public void setTargetIndex(int targetIndex) {
		this.targetIndex = targetIndex;
	}
	public Vector2 getCurrentTarget(){
		return getPath().get(targetIndex);
	}
	
	public List<Vector2> getPath() {
		return path;
	}

	public void setPath(List<Vector2> path) {
		this.path.clear();
		this.path.addAll(path);
	}
	
	public float getReachDistance() {
		return reachDistance;
	}

	public void setReachDistance(float reachDistance) {
		this.reachDistance = reachDistance;
	}

	@Override
	public void apply(ControlledCharacter character) {
		if(verifyTarget(character)){
			seekBehaviour.setTarget(getCurrentTarget());
			seekBehaviour.apply(character);
		}
	}

	boolean verifyTarget(ControlledCharacter character) {
		if(hasReachedTarget(character)){
			if(isLastTarget()){
				return false;
			}
			targetIndex += 1;
		}
		return true;
	}

	private boolean isLastTarget() {
		return targetIndex == getPath().size() - 1;
	}

	private boolean hasReachedTarget(ControlledCharacter character) {
		return hasReachedTarget(character, getCurrentTarget());
	}
	private boolean hasReachedTarget(ControlledCharacter character, Vector2 target) {
		return target.dst(character.getPosition()) <= getReachDistance();
	}
}
