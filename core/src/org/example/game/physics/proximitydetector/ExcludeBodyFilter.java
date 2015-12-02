package org.example.game.physics.proximitydetector;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public class ExcludeBodyFilter implements ProximityFilter {
	private final Body bodyToExclude;

	public ExcludeBodyFilter(Body bodyToExclude) {
		this.bodyToExclude = bodyToExclude;
	}

	@Override
	public boolean acceptFixture(Fixture fixture) {
		return bodyToExclude != fixture.getBody();
	}
}