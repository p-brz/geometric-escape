package org.example.game.prototypes;

import org.example.game.steering.SeekBehavior;
import org.example.game.steering.SteeringBehavior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PrototypeSeekBehavior extends PrototypeSteeringBehavior {
	@Override
	protected void updateBehavior() {
		float x = Gdx.input.getX();
		float y = Gdx.graphics.getHeight() - Gdx.input.getY();
		Vector2 newPos = new Vector2(x, y);
		
		SeekBehavior seekBehavior = (SeekBehavior) behavior;
		seekBehavior.setTarget(newPos);
	}

	@Override
	protected SteeringBehavior createBehavior() {
		SeekBehavior seekBehavior = new SeekBehavior();
		seekBehavior.setTarget(new Vector2(Gdx.graphics.getWidth()*0.8f
											, Gdx.graphics.getHeight()*0.5f));
		return seekBehavior;
	}
	
}
