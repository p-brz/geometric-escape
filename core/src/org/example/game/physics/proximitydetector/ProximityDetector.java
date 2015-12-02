package org.example.game.physics.proximitydetector;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;

public class ProximityDetector{
	protected World world;

	public ProximityDetector (World world) {
		this.world = world;
	}

	public void findBodies (Rectangle queryArea, final ProximityFilter filter, final ProximityListener proximityListener) {
		float lowerX = queryArea.getX();
		float lowerY = queryArea.getY();
		float upperX = queryArea.getX() + queryArea.getWidth();
		float upperY = queryArea.getY() + queryArea.getHeight();
		
		world.QueryAABB(new ProximityCallback(filter, proximityListener)
						, lowerX, lowerY, upperX, upperY);
	}
	

	final static class ProximityCallback implements QueryCallback {
		private final ProximityFilter filter;
		private final ProximityListener proximityListener;

		private ProximityCallback(ProximityFilter filter, ProximityListener proximityListener) {
			this.filter = filter;
			this.proximityListener = proximityListener;
		}

		@Override
		public boolean reportFixture (Fixture fixture) {
			if (acceptFixture(fixture, filter)) {
				if(proximityListener != null){
					proximityListener.onFindFixture(fixture);
				}
			}
			return true;
		}

		protected boolean acceptFixture(Fixture fixture, ProximityFilter filter) {
			return !fixture.isSensor() && fixture.getBody().isActive()
					&& (filter == null || filter.acceptFixture(fixture));
		}
	}
}