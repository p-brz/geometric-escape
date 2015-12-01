package org.example.game.prototypes;

import org.example.game.physics.GameWorld;
import org.example.game.physics.RectangularBody;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class PrototypeBox2D extends ApplicationAdapter{
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

    private OrthographicCamera camera;

    private GameWorld gameWorld;
    
    @Override
    public void create() {
    	super.create();
    	
    	gameWorld = new GameWorld();
        gameWorld.createStatic(new Vector2(Constants.GROUND_X, Constants.GROUND_Y)
							, new RectangularBody(Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT));
        gameWorld.createDynamic(new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y)
        					, new RectangularBody(Constants.RUNNER_WIDTH, Constants.RUNNER_HEIGHT));
        
        setupCamera();
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

	private void update(float delta) {
		gameWorld.update(delta);
	}

    public void draw() {
    	gameWorld.drawDebug(camera);
    }
}
