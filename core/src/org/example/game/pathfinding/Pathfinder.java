package org.example.game.pathfinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Pathfinder {
	private final PriorityQueue<EstimatedNode> priorityQueue;
	private final Map<MapNode, EstimatedNode> nodeToEstimation;
	private final Map<MapNode, Boolean> visited;

	private final PathBuilder pathBuilder;
	private final PathHeuristic heuristic;

	public Pathfinder() {
		pathBuilder = new PathBuilder();
		heuristic = new PathHeuristic();
		
		priorityQueue = new PriorityQueue<EstimatedNode>();
		nodeToEstimation = new HashMap<MapNode, EstimatedNode>();
		visited = new HashMap<MapNode, Boolean>();
	}
	
	public List<MapNode> findPath(PathGraph graph, MapNode source, MapNode target) {
		estimateAndQueue(source, null, target);
		
		while(!priorityQueue.isEmpty()){
			EstimatedNode node = priorityQueue.poll();

			visit(node);
			if(node.getNode().equals(target)){
				break;
			}
			
			checkAdjacents(graph, target, node);
		}
		return pathBuilder.makePath(source, getEstimate(target));
	}

	private void checkAdjacents(PathGraph graph, MapNode target, EstimatedNode estimatedNode) {
		MapNode node = estimatedNode.getNode();
		System.out.println("Get adjacents of: " + node);
		for(MapNode adjacent : graph.adjacents(node)){
			if(!wasVisited(adjacent)){
				estimateAndQueue(adjacent, estimatedNode, target);
			}
		}
	}

	private void visit(EstimatedNode node) {
		visited.put(node.getNode(), true);
	}
	private boolean wasVisited(MapNode adjacent) {
		Boolean wasVisited = visited.get(adjacent);
		
		return wasVisited != null && wasVisited == true;
	}


	private void estimateAndQueue(MapNode node, EstimatedNode parent
			, MapNode target) 
	{
		EstimatedNode currentEstimate = getEstimate(node);
		EstimatedNode newEstimate = heuristic.estimate(node, parent, target);
		
		if(currentEstimate == null 
				|| newEstimate.getCost() < currentEstimate.getCost()){

			priorityQueue.remove(currentEstimate);
			nodeToEstimation.put(node, newEstimate);
			priorityQueue.add(newEstimate);
		}
	}

	private EstimatedNode getEstimate(MapNode node) {
		return nodeToEstimation.get(node);
	}

}
