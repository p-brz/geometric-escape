package org.example.game.physics.bodies;

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
	
    public Body createStatic(Vector2 pos, PhysicalBody pBody, Object userData) {
        return createBody(pos, pBody, BodyType.StaticBody, userData);
    }
    
    public Body createDynamic(Vector2 pos, PhysicalBody pBody, Object userData) {
    	BodyType bodyType = BodyType.DynamicBody;
    	
        return createBody(pos, pBody, bodyType, userData);
    }

	public Body createBody(Vector2 pos, PhysicalBody pBody, BodyType bodyType, Object userData) {
		Material bodyMaterial = pBody.getMaterial();
		
		BodyDef bodyDef = createBodyDef(pos, bodyType, bodyMaterial);
        
        Shape shape = pBody.buildShape();
        
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, pBody.getMaterial().getDensity());
        body.resetMassData();
        
        shape.dispose();
        
        if(userData != null){
        	body.setUserData(userData);
        }
        
        return body;
	}

	private BodyDef createBodyDef(Vector2 pos, BodyType bodyType, Material bodyMaterial) {
		BodyDef bodyDef = new BodyDef();
        
        bodyDef.type = bodyType;
        bodyDef.position.set(pos);
        bodyDef.fixedRotation = !bodyMaterial.getAllowRotation();
		return bodyDef;
	}
}
