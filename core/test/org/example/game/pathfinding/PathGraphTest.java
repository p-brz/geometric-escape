package org.example.game.pathfinding;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PathGraphTest {
	PathGraph pathGraph;
	
	@Before
	public void setup(){
		pathGraph = new PathGraph();
	}

	@Test
	public void addNodeTest() {
		addSomeNode();
		assertEquals(1, pathGraph.count());
	}

	@Test
	public void graphShouldNotAllowdDuplicatedNodes() {
		addSomeNode();
		addSomeNode();
		
		assertEquals(1, pathGraph.count());
	}
	
	@Test
	public void addAdjacent_PutTheNodesAsAdjacentes(){
		MapNode node = addSomeNode(), other = addSomeNode(0,1);
		pathGraph.addAdjacent(node, other);
		assertThat(pathGraph.adjacents(node), hasItem(other));
		assertThat(pathGraph.adjacents(other), hasItem(node));
	}

	private MapNode addSomeNode() {
		return addSomeNode(0,0);
	}
	private MapNode addSomeNode(int x, int y) {
		MapNode node = new MapNode(x,y);
		pathGraph.addNode(node);
		return node;
	}
}
