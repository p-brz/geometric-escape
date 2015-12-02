package org.example.game;

import org.example.game.pathfinding.MapNode;
import org.example.game.pathfinding.NodeFinder;
import org.example.game.pathfinding.PathGraph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestFindNodeOnTileGraph {

	private static int tileSize = 2; //Em coordenadas do mundo
	
	private NodeFinder nodeFinder;
	
	@Before
	public void setup(){
		nodeFinder = new NodeFinder(givenAGraph(), tileSize);
	}
	
	@Test
	public void givenACoordinateEqualsToSomeGraphNode_itShouldReturnThatNode(){
		MapNode nodeFromMap = givenANodeFromGraph(nodeFinder.getGraph());
		
		MapNode node = nodeFinder.findNode(nodeFromMap.getX(), nodeFromMap.getY());
		
		Assert.assertNotNull(node);
		Assert.assertEquals(nodeFromMap, node);
	}	
	
	@Test
	public void givenAPointInsideSomeGraphNode_itShouldReturnThatNode(){
		MapNode nodeFromMap = givenANodeFromGraph(nodeFinder.getGraph());

		//Coordenadas quase na borda superior direita
		float px = nodeFromMap.getX() + tileSize * 0.49f;
		float py = nodeFromMap.getY() + tileSize * 0.49f;
		
		MapNode node = nodeFinder.findNode(px, py);
		
		Assert.assertNotNull(node);
		Assert.assertEquals(nodeFromMap, node);
	}

	private MapNode givenANodeFromGraph(PathGraph graph) {
		return graph.getNodes().iterator().next();
	}

	private PathGraph givenAGraph() {
		PathGraph graph = new PathGraph();
		
		for(int i=3; i < 10; ++i){
			for(int j=4; j < 12; ++j){
				graph.addNode(makeMapNode(i, j));
			}
		}
		
		
		return graph;
	}

	private MapNode makeMapNode(int x, int y) {
		return new MapNode(x * tileSize + tileSize/2, y * tileSize + tileSize/2);
	}
}
