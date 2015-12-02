package org.example.game.prototypes;

import org.example.game.drawable.SteerableDrawer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PrototypeDrawBox2DSteerable extends PrototypeBox2DSteerable{
	SteerableDrawer drawableSteerable;
	
	SpriteBatch batch;
	
	@Override
	public void create() {
		super.create();
		
		Texture texture = new Texture("badlogic.jpg");
		drawableSteerable = new SteerableDrawer(new TextureRegion(texture)
								, super.b2dSteerable, super.dimensionConverter);
		
		drawableSteerable.setSize(Constants.RUNNER_WIDTH, Constants.RUNNER_HEIGHT);
		
		batch = new SpriteBatch();
	}
	
	@Override
	public void draw() {
		super.draw();
		
		batch.begin();
		drawableSteerable.draw(batch);
		batch.end();
	}
}
