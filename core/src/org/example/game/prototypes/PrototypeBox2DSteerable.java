package org.example.game.prototypes;

import org.example.game.physics.GameWorld;
import org.example.game.physics.RectangularBody;
import org.example.game.player.Box2DSteerable;
import org.example.game.player.PlayPlayer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class PrototypeBox2DSteerable extends ApplicationAdapter{
	public class Constants {
		public static final float GROUND_X = 0;
	    public static final float GROUND_Y = 0;
	    public static final float GROUND_WIDTH = 50f;
	    public static final float GROUND_HEIGHT = 2f;
	    
		public static final float RUNNER_X = 2;
	    public static final float RUNNER_Y = GROUND_Y + GROUND_HEIGHT;
	    public static final float RUNNER_WIDTH = 1f;
	    public static final float RUNNER_HEIGHT = 2f;
	    public static final float RUNNER_DENSITY = 0.5f;
	    
	}

	// This will be our viewport measurements while working with the debug renderer
    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;
    
    Box2DSteerable b2dSteerable;
    PlayPlayer controller;

    private OrthographicCamera camera;
    
    private GameWorld gameWorld;
    
    @Override
    public void create() {
    	super.create();
    	
    	gameWorld = new GameWorld();
        gameWorld.createStatic(new Vector2(Constants.GROUND_X, Constants.GROUND_Y)
							, new RectangularBody(Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT));
        Body body = gameWorld.createDynamic(new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y)
        					, new RectangularBody(Constants.RUNNER_WIDTH, Constants.RUNNER_HEIGHT));
        
        setupCamera();
        
        b2dSteerable = new Box2DSteerable(body);
        controller = new PlayPlayer();
    }
    
	private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    @Override
    public void render() {
    	float delta = Gdx.graphics.getDeltaTime();
    	
        update(delta);
        draw();
    }

	private void update(float delta) 
	{
		controller.controllPlayer(b2dSteerable);
		gameWorld.update(delta);
	}

    public void draw() {
    	Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0.7f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	gameWorld.drawDebug(camera);
    }
}
