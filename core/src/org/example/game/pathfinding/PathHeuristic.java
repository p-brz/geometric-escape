package org.example.game.pathfinding;

public class PathHeuristic{

	public EstimatedNode estimate(MapNode source, EstimatedNode parent
			, MapNode target) 
	{
		float moveCost = parent == null ? 0 : parent.getMoveCost() + 1;
		float estimate = makeEstimate(source, target);
		
		return new EstimatedNode(moveCost, estimate,source, parent);
	}

	private float makeEstimate(MapNode source, MapNode target) {
		float deltaX = target.getX() - source.getX();
		float deltaY = target.getY() - source.getY();
		return Math.abs(deltaX) + Math.abs(deltaY);
	}		
}