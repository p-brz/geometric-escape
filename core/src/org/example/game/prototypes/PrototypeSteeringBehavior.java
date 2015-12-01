package org.example.game.prototypes;

import org.example.game.steering.SteeringBehavior;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class PrototypeSteeringBehavior extends ApplicationAdapter {
	protected SpriteBatch batch;
	protected Texture img;
	
	protected Sprite spriteChar;
	
	
	protected PrototypeCharacter character;
	protected SteeringBehavior behavior;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		spriteChar = new Sprite(img);
		spriteChar.setSize(80, 80);
		
		character = new PrototypeCharacter();
		
		behavior = createBehavior();
	}

	protected abstract SteeringBehavior createBehavior();

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0.7f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		updateBehavior();
		
		behavior.apply(character);
		character.update();
		spriteChar.setCenter(character.getPosition().x, character.getPosition().y);
		
		draw();
	}

	protected void draw() {
		batch.begin();
		drawAt(batch);
		batch.end();
	}

	protected void drawAt(SpriteBatch batch) {
		spriteChar.draw(batch);
	}

	protected void updateBehavior() 
	{}
	
}