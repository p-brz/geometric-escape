package org.example.game.pathfinding;

public class EstimatedNode implements Comparable<EstimatedNode>{
	private float moveCost, heuristic;
	private MapNode node;
	private EstimatedNode parent;

	public EstimatedNode(float moveCost, float heuristic
			, MapNode node, EstimatedNode parent) {
		super();
		this.moveCost = moveCost;
		this.heuristic = heuristic;
		this.node = node;
		this.parent = parent;
	}
	
	public MapNode getNode()         { return node;	}
	public EstimatedNode getParent() { return parent;}
	public float getMoveCost()       { return moveCost; }
	public float getHeuristic()      { return heuristic; }
	
	public float getCost(){
		return getMoveCost() + getHeuristic();
	}

	public void setNode(MapNode node) {
		this.node = node;
	}
	public void setMoveCost(float moveCost) {
		this.moveCost = moveCost;
	}
	public void setHeuristic(float heuristic) {
		this.heuristic = heuristic;
	}

	public void setParent(EstimatedNode parent) {
		this.parent = parent;
	}
	
	@Override
	public int compareTo(EstimatedNode other) {
		if(getCost() < other.getCost()){
			return -1;
		}
		else if(getCost() > other.getCost()){
			return 1;
		}
		return 0;
	}
}