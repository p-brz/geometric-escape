package org.example.game.prototypes;

import org.example.game.player.Box2DSteerable;
import org.example.game.player.PlayPlayer;

import com.badlogic.gdx.Gdx;

public class PrototypeBox2DSteerable extends PrototypeBox2D{
    Box2DSteerable b2dSteerable;
    protected PlayPlayer controller;
    
    @Override
    public void create() {
    	super.create();
    	
        b2dSteerable = new Box2DSteerable(super.dynamicBody);
        b2dSteerable.setVelocityMag(9);
        controller = new PlayPlayer();
    }

    @Override
    public void render() {
    	float delta = Gdx.graphics.getDeltaTime();
    	
        update(delta);
        draw();
    }

	public void update(float delta) 
	{
		super.update(delta);
		
		controller.controllPlayer(b2dSteerable);
	}
}
