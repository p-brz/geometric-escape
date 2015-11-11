package org.example.game.pathfinding;

import static org.junit.Assert.*;

import java.util.PriorityQueue;

import org.junit.Test;

public class EstimatedNodeTest {

	@Test
	public void orderingTest() {
		PriorityQueue<EstimatedNode> queue = new PriorityQueue<EstimatedNode>();
		EstimatedNode n1 = putNode(queue, 1, 10);
		EstimatedNode n2 = putNode(queue, 7, 2);
		EstimatedNode n3 = putNode(queue, 7, 0);
		EstimatedNode n4 = putNode(queue, 6, 1);

		assertEquals(n3, queue.poll());
		assertEquals(n4, queue.poll());
		assertEquals(n2, queue.poll());
		assertEquals(n1, queue.poll());
	}

	private EstimatedNode putNode(PriorityQueue<EstimatedNode> queue, int moveCost, int heuristic) {
		EstimatedNode node = new EstimatedNode(moveCost, heuristic, null, null);
		queue.add(node);
		return node;
	}

}
