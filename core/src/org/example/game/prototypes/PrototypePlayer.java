package org.example.game.prototypes;

import org.example.game.player.PlayPlayer;
import org.example.game.steering.BasicSteerable;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PrototypePlayer extends ApplicationAdapter
{

	protected SpriteBatch batch;
	protected Texture img;
	
	protected Sprite spriteChar;
	
	protected BasicSteerable character;
	
	protected Camera cam;
	
	PlayPlayer player;
	
	@Override
	public void create () {
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		cam.update();
		
		img = new Texture("badlogic.jpg");
		spriteChar = new Sprite(img);
		spriteChar.setSize(80, 80);
		player = new PlayPlayer();
		character = new BasicSteerable();
	}

	@Override
	public void render () {
		update();
		draw();
	}

	protected void update() {
		player.controllPlayer(character);
		character.update();
		spriteChar.setCenter(character.getPosition().x, character.getPosition().y);
	}

	protected void draw() {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0.7f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		drawAt(batch);
		batch.end();
	}
	
	protected void drawAt(SpriteBatch batch) {
		spriteChar.draw(batch);
	}
	
}
