package org.example.game.prototypes;

import org.example.game.drawable.SteerableDrawer;
import org.example.game.steering.BasicSteerable;
import org.example.game.steering.SteeringBehavior;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PrototypeSteeringBehavior extends ApplicationAdapter {
	protected SpriteBatch batch;
	protected Texture img;
	
	protected BasicSteerable character;
	protected SteeringBehavior behavior;
	private SteerableDrawer characterDrawable;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
//		spriteChar = new Sprite(img);
//		spriteChar.setSize(80, 80);
		
		character = new BasicSteerable();
		characterDrawable = new SteerableDrawer(new TextureRegion(img), character);
		characterDrawable.getSprite().setSize(80, 80);
		
		behavior = createBehavior();
	}

	protected SteeringBehavior createBehavior(){
		return null;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0.7f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update();
		
		draw();
	}

	protected void update() {
		updateBehavior();
		
		if(behavior != null){
			behavior.apply(character);
		}
		character.update(Gdx.graphics.getDeltaTime());
	}

	protected void draw() {
		batch.begin();
		drawAt(batch);
		batch.end();
	}

	protected void drawAt(SpriteBatch batch) {
//		spriteChar.draw(batch);
		characterDrawable.draw(batch);
	}

	protected void updateBehavior() 
	{}
	
}
