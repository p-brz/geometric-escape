package org.example.game.pathfinding;

import org.junit.Assert;
import org.junit.Test;


public class MapNodeTest {

	@Test
	public void equalsTest() {
		Assert.assertEquals(new MapNode(0, 0), new MapNode(0,0));
		Assert.assertNotEquals(new MapNode(0, 0), new MapNode(0,1));
	}

}
