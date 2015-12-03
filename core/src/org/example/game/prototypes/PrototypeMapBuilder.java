package org.example.game.prototypes;

import org.example.game.drawable.DimensionConverter;
import org.example.game.map.GamePathMap;
import org.example.game.pathfinding.NodeFinder;
import org.example.game.pathfinding.PathGraph;
import org.example.game.player.TrackerCamera;
import org.example.game.utils.BooleanMatrixPrinter;
import org.example.game.utils.GetNodeOnClick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alisonbnt
 */
public class PrototypeMapBuilder extends PrototypeGamePlayer {

    GamePathMap gameMap = new GamePathMap("maps/demonstration.tmx");
    TrackerCamera cameraTracker;
    
    @Override
    protected void draw() {
    	cameraTracker.update();
        gameMap.draw();
        batch.setProjectionMatrix(gameMap.getCamera().combined);
        super.draw(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create() {
        super.create();
        OrthographicCamera camera = new OrthographicCamera(800, 600);
        gameMap.initGameMap();

        boolean[][] walkable = gameMap.getMap().getWalkablePath();
        PathGraph graph = gameMap.getGraph();
        NodeFinder nodeFinder = new NodeFinder(graph, gameMap.getTileSize());
        
        player.setFinder(nodeFinder);
        player.setPosition(8.5f, 87.5f);
        player.getCharacter().setVelocityMag(6f);
//        player.getCharacter().setDeltaVelocity();
        
        DimensionConverter converter = gameMap.getDimensionConverter();
        player.setDimensionConverter(converter);
        
        camera.position.x = converter.convertToScreen(8.5f);
        camera.position.y = converter.convertToScreen(87.5f);
        camera.update();
        gameMap.setCamera(camera);
        cameraTracker = new TrackerCamera(camera, player.getCharacter(), converter);
        
        
        GetNodeOnClick getNodeOnClick = new GetNodeOnClick(camera, nodeFinder);
        Gdx.input.setInputProcessor(getNodeOnClick);

//        getNodeOnClick.addGetNodeListener(new GetNodeOnClick.GetNodeListener() {
//            @Override
//            public void onGetNode(MapNode node) {
//                System.out.println("Clicked on node: " + node);
//            }
//        });

        getNodeOnClick.addGetNodeListener(player);
        getNodeOnClick.setDimensionConverter(gameMap.getDimensionConverter());

       
        
        BooleanMatrixPrinter printer = new BooleanMatrixPrinter();
        printer.printMatrix(walkable);
    }

}
