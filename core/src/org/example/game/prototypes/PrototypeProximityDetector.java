package org.example.game.prototypes;

import org.example.game.physics.bodies.RectangularBody;
import org.example.game.physics.proximitydetector.ExcludeBodyFilter;
import org.example.game.physics.proximitydetector.ProximityFilter;
import org.example.game.physics.proximitydetector.ProximityListener;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public class PrototypeProximityDetector extends PrototypeBox2DSteerable{
	private static final Vector2 ENEMY_POSITION = new Vector2(10, 10);

	private Body detectorBody;

	ProximityFilter proxFilter;
	
	private Rectangle queryArea;
	
	@Override
	public void create() {
		super.create();
		
		detectorBody = getGameWorld().createStatic(ENEMY_POSITION, new RectangularBody(new Vector2(1,1)));
		
		this.queryArea = createQueryArea(detectorBody.getPosition(), 2f);
		
		proxFilter = new ExcludeBodyFilter(detectorBody);
	}

	private Rectangle createQueryArea(Vector2 position, float detectionRadius) {
		Rectangle queryArea = new Rectangle();
		queryArea.setSize(detectionRadius * 2);
		queryArea.setCenter(position);
		return queryArea;
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		getGameWorld().findFixtures(queryArea, proxFilter, new ProximityListener() {
			@Override
			public void onFindFixture(Fixture fixture) {
				System.out.println("Found : " + fixture);
			}
		});
	}
}
