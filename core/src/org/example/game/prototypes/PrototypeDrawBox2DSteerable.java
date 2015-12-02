package org.example.game.prototypes;

import org.example.game.drawable.SteerableDrawer;
import org.example.game.drawable.TrivialDimensionConverter;
import org.example.game.player.TrackerCamera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PrototypeDrawBox2DSteerable extends PrototypeBox2DSteerable{
	SteerableDrawer drawableSteerable;
	
	SpriteBatch batch;
	
//	Camera cam;
//	TrackerCamera cameraTracker;
	
	@Override
	public void create() {
		super.create();
		
		Texture texture = new Texture("badlogic.jpg");
		drawableSteerable = new SteerableDrawer(new TextureRegion(texture)
								, super.b2dSteerable, super.dimensionConverter);
		
		drawableSteerable.setSize(Constants.RUNNER_WIDTH, Constants.RUNNER_HEIGHT);

		float vPortW = dimensionConverter.convertToScreen(viewportWidth);
		float vPortH = dimensionConverter.convertToScreen(viewportHeight);
//		cam = new OrthographicCamera(vPortW, vPortH);
		batch = new SpriteBatch();
//		batch.setProjectionMatrix(cam.combined);
		
//		cameraTracker = new TrackerCamera(super.getCamera(), b2dSteerable
//										, new TrivialDimensionConverter());
	}
	
	@Override
	public void draw() {
//		cameraTracker.update();
		
		super.draw();
//		batch.setProjectionMatrix(super.getCamera().combined);
		batch.begin();
		drawableSteerable.draw(batch);
		batch.end();
	}
}
