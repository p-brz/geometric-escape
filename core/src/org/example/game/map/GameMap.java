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
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author alisonbnt
 */
public class GameMap {
    
    public GameMap(String mapFile){
        this.mapFile = mapFile;
    }
    
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
    
    private void setupLayers() {
        List<Integer> bgLayers = new ArrayList<Integer>();

        Iterator<MapLayer> layersIterator = map.getLayers().iterator();
        int index = 0;
        while (layersIterator.hasNext()) {
            layersIterator.next();
            bgLayers.add(index);
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
