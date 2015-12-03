package org.example.game.steering;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PathFollowingBehavior implements SteeringBehavior {

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
        reachDistance = 0.2f;
    }

    public PathFollowingBehavior(List<Vector2> pathVector) {
        this();
        setPath(pathVector);
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public Vector2 getCurrentTarget() {
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
    public void apply(Steerable character) {
        if (verifyTarget(character)) {
            seekBehaviour.setTarget(getCurrentTarget());
            seekBehaviour.apply(character);
        }
    }

    boolean verifyTarget(Steerable character) {
        if (hasReachedTarget(character)) {
            if (isLastTarget()) {
            	Gdx.app.log(getClass().getSimpleName(), "Reach last target");
                return false;
            }
            targetIndex += 1;
        }
        return true;
    }

    public boolean isLastTarget() {
        return targetIndex == getPath().size() - 1;
    }

    private boolean hasReachedTarget(Steerable character) {
        return hasReachedTarget(character, getCurrentTarget());
    }

    private boolean hasReachedTarget(Steerable character, Vector2 target) {
    	float distanceToTarget = target.dst(character.getPosition());
        return distanceToTarget <= getReachDistance();
    }
}
