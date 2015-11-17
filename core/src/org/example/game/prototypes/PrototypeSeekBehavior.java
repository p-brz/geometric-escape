package org.example.game.prototypes;

import org.example.game.steering.SeekBehavior;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PrototypeSeekBehavior extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	Sprite spriteChar;
	
	
	PrototypeCharacter character;
	SeekBehavior seekBehavior;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		spriteChar = new Sprite(img);
		spriteChar.setSize(80, 80);
		
		character = new PrototypeCharacter();
		
		seekBehavior = new SeekBehavior();
		seekBehavior.setTarget(new Vector2(Gdx.graphics.getWidth()*0.8f
											, Gdx.graphics.getHeight()*0.5f));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0.7f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		updateTarget();
		
		seekBehavior.apply(character);
		character.update();
		spriteChar.setCenter(character.getPosition().x, character.getPosition().y);
		
		batch.begin();
		spriteChar.draw(batch);
		batch.end();
	}

	private void updateTarget() {
//		if(Gdx.input.justTouched()){
			float x = Gdx.input.getX();
			float y = Gdx.graphics.getHeight() - Gdx.input.getY();
			Vector2 newPos = new Vector2(x, y);
			
			seekBehavior.setTarget(newPos);
//		}
	}
	
}
