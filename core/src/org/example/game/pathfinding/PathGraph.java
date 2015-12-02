package org.example.game.pathfinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class PathGraph {
	final Map<MapNode, Integer> nodeToKey;
	final List<Set<MapNode>> adjacents;
	
	public PathGraph() {
		nodeToKey = new HashMap<MapNode, Integer>();
		adjacents = new ArrayList<Set<MapNode>>();
	}
	
	public Collection<MapNode> getNodes(){
		return nodeToKey.keySet();
	}
	
	public void addNode(MapNode mapNode) {
		if(nodeToKey.containsKey(mapNode)){
			return;
		}
		adjacents.add(new HashSet<MapNode>());
		nodeToKey.put(mapNode, adjacents.size() - 1);
	}

	public int count() {
		return adjacents.size();
	}

	public Collection<MapNode> adjacents(MapNode node) {
		Integer vert = getVertice(node);
		if(vert != null){
			return this.adjacents.get(vert);
		}
		return null;
	}

	private Integer getVertice(MapNode node) {
		return nodeToKey.get(node);
	}

	public void addAdjacent(MapNode node1, MapNode node2) {
		Integer v1 = getVertice(node1), v2 = getVertice(node2);
		putAdjacent(v1, node2);
		putAdjacent(v2, node1);
	}

	private void putAdjacent(Integer vertice, MapNode node) {
		adjacents.get(vertice).add(node);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(MapNode node : nodeToKey.keySet()){
			builder.append(String.format(Locale.US, "(%d,%d) : ", node.getX(), node.getY()));
			
			int vert = nodeToKey.get(node);
			for(MapNode adjacent : adjacents.get(vert)){
				builder.append(String.format(Locale.US, "(%d,%d) ", adjacent.getX(), adjacent.getY()));
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
