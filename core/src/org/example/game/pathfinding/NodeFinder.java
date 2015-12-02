package org.example.game.pathfinding;

import com.badlogic.gdx.math.Vector2;

/** Classe capaz de encontrar o nó de um grafo que contém um dado ponto*/
public class NodeFinder{
	private float nodeSize;
	private PathGraph graph;
	
	public NodeFinder(PathGraph graph, float tileSize) {
		super();
		this.graph = graph;
		this.nodeSize = tileSize;
	}

	public float getNodeSize() {
		return nodeSize;
	}

	public void setNodeSize(float tileSize) {
		this.nodeSize = tileSize;
	}

	public PathGraph getGraph() {
		return graph;
	}

	public void setGraph(PathGraph graph) {
		this.graph = graph;
	}

	public MapNode findNode(Vector2 pos) {
		return findNode(pos.x, pos.y);
	}
	public MapNode findNode(float x, float y) {
		
		for(MapNode node : graph.getNodes()){
			if(nodeContainsPoint(x, y, node)){
				return node;
			}
		}
		
		return null;
	}

	protected boolean nodeContainsPoint(float x, float y, MapNode node) {
		float maxDist = nodeSize/2f;
		
		float deltaX = Math.abs(node.getX() - x);
		float deltaY = Math.abs(node.getY() - y);
		
		return (deltaX <= maxDist && deltaY <= maxDist);
	}
}