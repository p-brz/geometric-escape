package org.example.game.player;

import org.example.game.steering.BasicSteerable;

import com.badlogic.gdx.physics.box2d.Body;

public class Box2DSteerable extends BasicSteerable
{
	Body body;
	
	public Box2DSteerable(Body body)
	{
		this.body = body;
	}
	
	@Override
	public void update() 
	{
		super.update();
		body.setLinearVelocity(getVelocity());
		super.getPosition().set(body.getPosition());
		
	}
}
