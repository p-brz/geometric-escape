package org.example.game.player;

import org.example.game.steering.Steerable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayPlayer 
{
	public void controllPlayer(Steerable character)
	{
		boolean up = Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP);
		boolean left = Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean rigth = Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean down = Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN);
		
		
		if(up)
			character.getVelocity().y = 1;
		else if(down)  
			character.getVelocity().y = -1;
		else character.getVelocity().y = 0;
		
		if(left)
			character.getVelocity().x = -1;
		else if(rigth)  
			character.getVelocity().x = 1;
		else character.getVelocity().x = 0;
		
		character.getVelocity().nor().scl(character.getVelocityMag());
		character.update(Gdx.graphics.getDeltaTime());
	}

}
