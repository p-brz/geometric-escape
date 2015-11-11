package org.example.game.pathfinding;

import java.util.LinkedList;
import java.util.List;

public class PathBuilder{
	public List<MapNode> makePath(MapNode source, EstimatedNode target) {
		List<MapNode> path = makePathTo(target);
		
		if(!path.isEmpty() && path.get(0) != source){
			//Não encontrou source
			path.clear();
		}
		
		return path;
	}
	public List<MapNode> makePathTo(EstimatedNode initial) {
		List<MapNode> path = new LinkedList<MapNode>();
		
		EstimatedNode current = initial;
		while(current != null){
			path.add(0, current.getNode());
			current = current.getParent();
		}
		
		return path;
	}
}