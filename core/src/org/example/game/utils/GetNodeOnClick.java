package org.example.game.utils;

import java.util.ArrayList;
import java.util.Collection;

import org.example.game.drawable.DimensionConverter;
import org.example.game.drawable.TrivialDimensionConverter;
import org.example.game.pathfinding.MapNode;
import org.example.game.pathfinding.NodeFinder;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public final class GetNodeOnClick extends InputAdapter {
	public static interface GetNodeListener{
		public void onGetNode(MapNode  node);
	}
	
	private final Collection<GetNodeOnClick.GetNodeListener> listeners;

	private Camera camera;
	private NodeFinder nodeFinder;
	private DimensionConverter dimensionConverter;
	
	public GetNodeOnClick(Camera camera, NodeFinder nodeFinder) {
		listeners = new ArrayList<GetNodeOnClick.GetNodeListener>();
		this.camera = camera;
		this.nodeFinder = nodeFinder;
		dimensionConverter = new TrivialDimensionConverter();
	}
	
	public void addGetNodeListener(GetNodeOnClick.GetNodeListener listener){
		this.listeners.add(listener);
	}
	public void removeGetNodeListener(GetNodeOnClick.GetNodeListener listener){
		this.listeners.remove(listener);
	}
	
	public Camera getCamera() {
		return camera;
	}
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public NodeFinder getNodeFinder() {
		return nodeFinder;
	}
	public void setNodeFinder(NodeFinder nodeFinder) {
		this.nodeFinder = nodeFinder;
	}

	public DimensionConverter getDimensionConverter() {
		return dimensionConverter;
	}
	public void setDimensionConverter(DimensionConverter dimensionConverter) {
		this.dimensionConverter = dimensionConverter;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 vec = camera.unproject(new Vector3(screenX, screenY, 0));
		Vector2 vec2D = new Vector2(vec.x, vec.y);
		vec2D = dimensionConverter.convertToWorld(vec2D);
		
		MapNode node = nodeFinder.findNode(vec2D);
		
		notifyListeners(node);
		
		return super.touchUp(screenX, screenY, pointer, button);
	}

	private void notifyListeners(MapNode node) {
		if(node != null){
			for(GetNodeOnClick.GetNodeListener listener : this.listeners){
				listener.onGetNode(node);
			}
		}
	}
}