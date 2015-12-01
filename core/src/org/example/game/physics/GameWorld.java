package org.example.game.physics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class GameWorld {
    private World world;
    
    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private BodyCreator bodyCreator;
    
    private Box2DDebugRenderer renderer;
    
    public GameWorld() {
    	world = new World(Vector2.Zero, true);
    	renderer = new Box2DDebugRenderer();
    	bodyCreator = new BodyCreator(world);
    }
    
    public void update(float delta){
		// Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

        //TODO: Implement interpolation
    }
    
    public void drawDebug(Camera camera){
        renderer.render(world, camera.combined);
    }
    
    public Body createStatic(Vector2 pos, PhysicalBody pBody) {
        return bodyCreator.createStatic(pos, pBody);
    }
    
    public Body createDynamic(Vector2 pos, PhysicalBody pBody) {
        return bodyCreator.createDynamic(pos, pBody);
    }
}
