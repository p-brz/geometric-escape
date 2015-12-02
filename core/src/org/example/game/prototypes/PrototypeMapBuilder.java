package org.example.game.prototypes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import org.example.game.config.GameConfig;
import org.example.game.map.GamePathMap;
import org.example.game.pathfinding.MapNode;
import org.example.game.pathfinding.NodeFinder;
import org.example.game.pathfinding.PathGraph;
import org.example.game.utils.BooleanMatrixPrinter;
import org.example.game.utils.GetNodeOnClick;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alisonbnt
 */
public class PrototypeMapBuilder extends ApplicationAdapter {

//    GameMap gameMap = new GameMap("maps/demonstration.tmx");
    GamePathMap gameMap = new GamePathMap("maps/demonstration.tmx");

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0.7f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameMap.draw();
    }

    @Override
    public void create() {
        OrthographicCamera camera = new OrthographicCamera(800, 600);
        camera.position.x = 0;
        camera.position.y = (100 - 7) * GameConfig.TILE_IN_PIXELS;
        camera.update();
        gameMap.setCamera(camera);
        gameMap.initGameMap();
        boolean[][] walkable = gameMap.getMap().getWalkablePath();
        PathGraph graph = gameMap.getGraph();
        NodeFinder nodeFinder = new NodeFinder(graph, gameMap.getTileSize());

        GetNodeOnClick getNodeOnClick = new GetNodeOnClick(camera, nodeFinder);
        Gdx.input.setInputProcessor(getNodeOnClick);

        getNodeOnClick.addGetNodeListener(new GetNodeOnClick.GetNodeListener() {
            @Override
            public void onGetNode(MapNode node) {
                System.out.println("Clicked on node: " + node);
            }
        });

        getNodeOnClick.setDimensionConverter(gameMap.getDimensionConverter());

        BooleanMatrixPrinter printer = new BooleanMatrixPrinter();
        printer.printMatrix(walkable);
    }

}
