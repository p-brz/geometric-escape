package org.example.game.prototypes;

import org.example.game.drawable.BasicDimensionConverter;
import org.example.game.physics.GameWorld;
import org.example.game.physics.bodies.RectangularBody;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PrototypeBox2D extends ApplicationAdapter{
	public class Constants {
		public static final float GROUND_X = 0;
	    public static final float GROUND_Y = 0;
	    public static final float GROUND_WIDTH = 50f;
	    public static final float GROUND_HEIGHT = 2f;
	    
		public static final float RUNNER_X = 2;
	    public static final float RUNNER_Y = GROUND_Y + GROUND_HEIGHT;
	    public static final float RUNNER_WIDTH = 1f;
	    public static final float RUNNER_HEIGHT = 1f;
	    public static final float RUNNER_DENSITY = 0.5f;
	    
	    public static final float PIXELS_PER_WORLD_UNIT = 32;

	}

	/** Medidas do Viewport utilizadas com o debug renderer
	  	Viewport corresponde ao tamanho da tela em uma unidade qualquer (?).
	  	Ele permite, neste caso, mapear as dimensões do mundo (Box2d) para a tela.
	  	Valores maiores farão com que a câmera exiba uma área maior do mundo e, com isto,
	  	os objetos do mundo parecerão menores (como se reduzisse o zoom).*/
    protected float viewportWidth, viewportHeight;
    protected Viewport camViewport;
    private OrthographicCamera camera;

    private GameWorld gameWorld;
    
    protected Body dynamicBody;
    protected Body staticBody;
    
    protected BasicDimensionConverter dimensionConverter;
    
    protected GameWorld getGameWorld(){
    	return gameWorld;
    }
    
    @Override
    public void create() {
    	super.create();
    	
    	dimensionConverter = new BasicDimensionConverter(Constants.PIXELS_PER_WORLD_UNIT);
    	
    	gameWorld = new GameWorld();
        staticBody = gameWorld.createStatic(new Vector2(Constants.GROUND_X, Constants.GROUND_Y)
							, new RectangularBody(Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT));
        dynamicBody = gameWorld.createDynamic(new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y)
        					, new RectangularBody(Constants.RUNNER_WIDTH, Constants.RUNNER_HEIGHT));
        
        setupCamera();
    }
    
	private void setupCamera() {
		viewportWidth = dimensionConverter.convertToWorld(Gdx.graphics.getWidth());
		viewportHeight = dimensionConverter.convertToWorld(Gdx.graphics.getHeight());
		
		
		System.out.println("Viewport width: " + viewportWidth + " viewport height: " + viewportHeight);
		
        camera = new OrthographicCamera(viewportWidth, viewportHeight);
        
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();

		camViewport = new FitViewport(viewportWidth, viewportHeight);
        camViewport.setCamera(camera);
    }

    @Override
    public void render() {
    	float delta = Gdx.graphics.getDeltaTime();
    	
        update(delta);
        draw();
    }

    @Override
    public void resize(int width, int height) {
    	super.resize(width, height);
    	camViewport.update(width, height);
    }
    
	public void update(float delta) {
		gameWorld.update(delta);
	}

    public void draw() {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0.7f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	gameWorld.drawDebug(camera);
    }
}
