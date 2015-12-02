package org.example.game.prototypes;

import org.example.game.pathfinding.MapNode;
import org.example.game.pathfinding.NodeFinder;
import org.example.game.pathfinding.PathGraph;
import org.example.game.utils.GetNodeOnClick;
import org.example.game.utils.GetNodeOnClick.GetNodeListener;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PrototypeFindNode extends ApplicationAdapter{

	int tileSize = 32;
	
	ShapeRenderer shapeRenderer;

	int widthTiles, heightTiles;
	
	
	PathGraph pathGraph;
	NodeFinder nodeFinder;
	
	OrthographicCamera camera;
	Viewport viewport;
	
	GetNodeOnClick getNodeOnClick;
	
	@Override
	public void create() {
		super.create();
		
		camera = new OrthographicCamera();
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport.setCamera(camera);
		camera.position.x = viewport.getWorldWidth() /2f;
		camera.position.y = viewport.getWorldHeight() /2f;
		camera.update();
		
		shapeRenderer = new ShapeRenderer();

		widthTiles = Gdx.graphics.getWidth()/tileSize + 1;
		heightTiles = Gdx.graphics.getHeight()/tileSize + 1;
		
		pathGraph = new PathGraph();
		nodeFinder = new NodeFinder(pathGraph, tileSize);
		
		buildGraph(pathGraph, widthTiles, heightTiles);
		
		getNodeOnClick = new GetNodeOnClick(camera, nodeFinder);
		Gdx.input.setInputProcessor(getNodeOnClick);
		
		getNodeOnClick.addGetNodeListener(new GetNodeListener() {
			@Override
			public void onGetNode(MapNode node) {
				System.out.println("Clicked on node: " + node);
			}
		});
	}

	protected void buildGraph(PathGraph graph, int widthTiles, int heightTiles) {
		int startRow = heightTiles/4;
		int startCol = widthTiles/4;
		int endRow = heightTiles - startRow;
		int endCol = widthTiles - startCol;
		
		for(int row=startRow; row< endRow; ++row){
			for(int col=startCol; col < endCol; ++col){
				pathGraph.addNode(new MapNode(col * tileSize + tileSize/2, row * tileSize + tileSize/2));
			}
		}
	}
	
	@Override
	public void render() {
		super.render();

		drawShapes();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width, height);
	}
	
	private void drawShapes() {
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		drawTiles(shapeRenderer);


		shapeRenderer.setColor(Color.BLACK);
		for(MapNode node : pathGraph.getNodes()){
			shapeRenderer.circle(node.getX(), node.getY(), 2f);
		}
		
		shapeRenderer.end();
	}

	private void drawTiles(ShapeRenderer shapeRenderer) {
		for(int row=0; row< heightTiles; ++row){
			for(int col=0; col < widthTiles; ++col){
				if(col%2 == row%2){//alterna cores de tiles
					shapeRenderer.setColor(Color.LIGHT_GRAY);
				}
				else{
					shapeRenderer.setColor(Color.WHITE);
				}
				
				shapeRenderer.rect(col*tileSize, row*tileSize, tileSize, tileSize);
			}
		}
	}
	
}
