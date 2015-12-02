package org.example.game.physics.proximitydetector;

import com.badlogic.gdx.physics.box2d.Fixture;

public interface ProximityListener{
	public void onFindFixture(Fixture fixture);
}