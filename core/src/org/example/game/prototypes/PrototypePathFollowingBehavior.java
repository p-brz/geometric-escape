package org.example.game.prototypes;

import java.util.Arrays;
import java.util.List;

import org.example.game.steering.PathFollowingBehavior;
import org.example.game.steering.SteeringBehavior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class PrototypePathFollowingBehavior extends PrototypeGamePlayer{
	private List<Vector2> path;
	private ShapeRenderer shapeRenderer;
	
	@Override
	public void create() {
		super.create();
		
		shapeRenderer = new ShapeRenderer();
	}
	
	@Override
	protected SteeringBehavior createBehavior() {
		int screenWidth = Gdx.graphics.getWidth();
		int screenHeight = Gdx.graphics.getHeight();
		
		path = Arrays.asList(new Vector2(20, 20)
							, new Vector2(50, 200)
							, new Vector2(400, 70)
							, new Vector2(screenWidth * 0.7f, screenHeight)
							, new Vector2(screenWidth/2f, screenHeight/2f));
		
		PathFollowingBehavior pathFollowingBehavior = new PathFollowingBehavior();
		pathFollowingBehavior.setPath(path);
		pathFollowingBehavior.setReachDistance(15);
		
		return pathFollowingBehavior;
	}

	@Override
	protected void draw() {
		drawShapes();
		super.draw();
	}

	private void drawShapes() {
//		 shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		 
		 shapeRenderer.begin(ShapeType.Filled);
		 shapeRenderer.setColor(0, 1, 0, 1);
		 
		 for(Vector2 pos : path){
			 drawPoint(pos);
		 }
		 
		 shapeRenderer.end();
	}

	private void drawPoint(Vector2 pos) {
		shapeRenderer.circle(pos.x, pos.y, 10);
	}
}
