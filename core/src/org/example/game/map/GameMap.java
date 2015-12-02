/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.game.map;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.example.game.config.GameConfig;

/**
 *
 * @author alisonbnt
 */
public class GameMap {
    
    public GameMap(String mapFile){
        this.mapFile = mapFile;
    }
    
    private int pathLayerIndex = 0;
    private final String mapFile;
    private Map map;
    float unitScale = 1f;
    MapRenderer mapRenderer;
    private OrthographicCamera mapCamera;

    private int[] backgroundLayers;
    
    public void initMap(){
        loadMap(new TmxMapLoader(new InternalFileHandleResolver()), mapFile);
        setup();
    }
    
    public void setCamera(OrthographicCamera mapCamera) {
        this.mapCamera = mapCamera;
    }
    
    public OrthographicCamera getCamera(){
        return this.mapCamera;
    }

    public void loadMap(TmxMapLoader tmxMapLoader, String mapFilename) {
        TiledMap tiledMap = tmxMapLoader.load(mapFilename);
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, unitScale);
        this.map = tiledMap;
    }

    public void setUnitScale(float unitScale) {
        this.unitScale = unitScale;
    }
    
    public float getUnitScale(){
        return this.unitScale;
    }

    public void setup() {
        setupLayers();
    }
    
    public boolean[][] getWalkablePath(){
        TiledMapTileLayer tiledLayer = (TiledMapTileLayer) map.getLayers().get(pathLayerIndex);
        int tileWidth = tiledLayer.getWidth();
        int tileHeight = tiledLayer.getHeight();
        
        System.out.println(tileWidth);
        System.out.println(tileHeight);
        
        boolean[][] path = new boolean[tileWidth][tileHeight];
        
        for(int x = 0; x < tileWidth; x++){
            for(int y = 0; y < tileHeight; y++){
                Cell cell = tiledLayer.getCell(x, y);
                path[x][y] = (cell != null);
            }
        }
        
        return path;
    }
    
    private void setupLayers() {
        List<Integer> bgLayers = new ArrayList<Integer>();

        Iterator<MapLayer> layersIterator = map.getLayers().iterator();
        int index = 0;
        while (layersIterator.hasNext()) {
            MapLayer layer = layersIterator.next();
            if(layer.getName().equals(GameConfig.MAP_PATH_LAYER)){
                this.pathLayerIndex = index;
            }else{
                bgLayers.add(index);
            }
            ++index;
        }

        this.backgroundLayers = new int[bgLayers.size()];
        for (int i = 0; i < bgLayers.size(); ++i) {
            this.backgroundLayers[i] = bgLayers.get(i);
        }
    }

    public void draw() {
        if (mapCamera != null) {
            mapRenderer.setView(mapCamera);
        }
        mapRenderer.render(backgroundLayers);
    }
    
}
