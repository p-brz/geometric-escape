package org.example.game.prototypes;

import org.example.game.physics.bodies.RectangularBody;
import org.example.game.physics.collision.CollisionEvent;
import org.example.game.physics.collision.CollisionListener;

import com.badlogic.gdx.math.Vector2;

public class PrototypeCollisionDetection extends PrototypeBox2DSteerable{

	private Integer target = 1, enemy = 2;
	
	@Override
	public void create() {
		super.create();

		Vector2 obstaclePos = new Vector2(viewportWidth/2f, viewportHeight/2f);
		Vector2 victoryPos = new Vector2(viewportWidth* 0.8f, viewportHeight* 0.8f);
		
		RectangularBody rectBody = new RectangularBody(new Vector2(1,1));

		//Cria objetos passando um 'userData'
		getGameWorld().createStatic(obstaclePos, rectBody, enemy);
		getGameWorld().createStatic(victoryPos, rectBody, target);
		
		//Adiciona CollisionListener
		getGameWorld().addCollisionListener(new ExampleCollisionListener());
	}
	
	private final class ExampleCollisionListener implements CollisionListener {
		@Override
		public void onCollision(CollisionEvent collEvent) {
			
			if(collEvent.hasData(enemy)){
				if(collEvent.isTouching()){
					System.out.println("You touch an enemy");
				}
			}
			else if(collEvent.hasData(target)){
				if(collEvent.isTouching()){
					System.out.println("You touch the target!");
				}
			}
			else{

				System.out.println(collEvent.getCollisionType() + " between " 
						+ collEvent.getColliderA().getBody()
						+ " and "
						+ collEvent.getColliderB().getBody());
			}
		}
	}
}
