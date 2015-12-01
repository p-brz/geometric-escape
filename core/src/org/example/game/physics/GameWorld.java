package org.example.game.physics;

import org.example.game.physics.bodies.BodyCreator;
import org.example.game.physics.bodies.PhysicalBody;
import org.example.game.physics.collision.CollisionListener;
import org.example.game.physics.collision.CollisionManager;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameWorld {
    private World world;
    
    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private BodyCreator bodyCreator;
    private final CollisionManager collisionManager;
    
    private Box2DDebugRenderer renderer;
    
    public GameWorld() {
    	world = new World(Vector2.Zero, true);
    	//world = new World(new Vector2(20, 20), true);// gravidade enclinada
    	renderer = new Box2DDebugRenderer();
    	bodyCreator = new BodyCreator(world);
    	collisionManager = new CollisionManager();
    	
    	world.setContactListener(collisionManager);
    }
    
    public CollisionManager getCollisionManager() {
		return collisionManager;
	}

	public void update(float delta){
		// Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
    }
    
    public void drawDebug(Camera camera){
        renderer.render(world, camera.combined);
    }

    public Body createStatic(Vector2 pos, PhysicalBody pBody) {
        return createStatic(pos, pBody, null);
    }
    public Body createStatic(Vector2 pos, PhysicalBody pBody, Object userData) {
        return bodyCreator.createStatic(pos, pBody, userData);
    }

    public Body createDynamic(Vector2 pos, PhysicalBody pBody) {
        return createDynamic(pos, pBody, null);
    }
    public Body createDynamic(Vector2 pos, PhysicalBody pBody, Object userData) {
        return bodyCreator.createDynamic(pos, pBody, userData);
    }

    public void addCollisionListener(CollisionListener listener){
    	getCollisionManager().addListener(listener);
    }
    public void removeCollisionListener(CollisionListener listener){
    	getCollisionManager().removeListener(listener);
    }
    
	public World getWorld() {
		return world;
	}
}
