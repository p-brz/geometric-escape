package org.example.game.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BodyCreator {
	private World world;
	
	public BodyCreator(World world) {
		this.world = world;
	}
	
    public Body createStatic(Vector2 pos, PhysicalBody pBody) {
        return createBody(pos, pBody, BodyType.StaticBody);
    }
    
    public Body createDynamic(Vector2 pos, PhysicalBody pBody) {
    	BodyType bodyType = BodyType.DynamicBody;
    	
        return createBody(pos, pBody, bodyType);
    }

	public Body createBody(Vector2 pos, PhysicalBody pBody, BodyType bodyType) {
		BodyDef bodyDef = new BodyDef();
        
        bodyDef.type = bodyType;
        bodyDef.position.set(pos);

        Shape shape = pBody.buildShape();
        
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, pBody.getMaterial().getDensity());
        body.resetMassData();
        
        shape.dispose();
        
        return body;
	}
}
