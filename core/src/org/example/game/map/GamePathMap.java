/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import org.example.game.config.GameConfig;
import org.example.game.drawable.BasicDimensionConverter;
import org.example.game.drawable.DimensionConverter;
import org.example.game.pathfinding.PathGraph;
import org.example.game.pathfinding.PathGraphPopulator;
import org.example.game.physics.GameWorld;

/**
 *
 * @author alisonbnt
 */
public class GamePathMap {
    
    private GameMap map;
    private PathGraph graph;
    private BasicDimensionConverter converter;
    private float tileSize;
    
    public GamePathMap(String mapFile){
        this(mapFile, GameConfig.MAP_PATH_LAYER, GameConfig.MAP_COLLISION_LAYER);
    }
    
    public GamePathMap(String mapFile, String pathLayerName, String collideLayerName){
        this.map = new GameMap(mapFile, pathLayerName, collideLayerName);
        this.graph = new PathGraph();
        this.converter = new BasicDimensionConverter(GameConfig.TILE_IN_PIXELS);
        this.tileSize = converter.convertToWorld(GameConfig.TILE_IN_PIXELS);
    }
    
    public void setWorld(GameWorld world){
        this.map.setWorld(world);
    }
    
    public void setCamera(OrthographicCamera mapCamera) {
        this.map.setCamera(mapCamera);
    }
    
    public OrthographicCamera getCamera(){
        return this.map.getCamera();
    }
    
    public void setUnitScale(float unitScale) {
        this.map.setUnitScale(unitScale);
    }
    
    public float getUnitScale(){
        return this.map.getUnitScale();
    }
    
    public void draw(){
        this.map.draw();
    }
    
    public void initGameMap(){
        map.initMap();
        PathGraphPopulator.populateFromMatrix(this.graph, map.getWalkablePath(), tileSize);
    }
    
    public PathGraph getGraph(){
        return this.graph;
    }
    
    public GameMap getMap(){
        return this.map;
    }
    
    public float getTileSize(){
        return this.tileSize;
    }
    
    public DimensionConverter getDimensionConverter(){
        return this.converter;
    }
    
}
