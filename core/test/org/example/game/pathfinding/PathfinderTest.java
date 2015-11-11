package org.example.game.pathfinding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.math.Rectangle;

public class PathfinderTest {
	PathGraph graph;
	boolean[][] map;
	Pathfinder pathfinder;
	
	MapNode source, target;

	static final boolean[][] mediumMap = createMap(7, 6);
	static final Rectangle[] mediumMapObstacles = new Rectangle[]{
		new Rectangle(1, 4, 1, 1)
		  , new Rectangle(3, 1, 1, 4)
	};
	
	static final boolean[][] largeMap = createMap(26, 20);
	static final Rectangle[] largeMapObstacles = new Rectangle[]{
		new Rectangle(7, 5, 1, 13)
		  , new Rectangle(8, 17, 7, 1)
		  , new Rectangle(14, 14, 1, 3)
		  , new Rectangle(12, 13, 11, 1)
		  , new Rectangle(12, 4, 1, 9)
		  , new Rectangle(13, 4, 7, 1)
		  , new Rectangle(13, 0, 1, 4)
		  , new Rectangle(19, 5, 1, 7)
		  , new Rectangle(20, 6, 5, 1)
	};
	
	@BeforeClass
	public static void setupClass(){
		addObstacles(mediumMap, mediumMapObstacles);
		System.out.println("Medium map:");
		printMap(mediumMap);
		
		addObstacles(largeMap, largeMapObstacles);
		System.out.println("Large map:");
		printMap(largeMap);
	}
	
	@Before
	public void setup(){
		graph = new PathGraph();
		fillGraph(graph, mediumMap);
		
		pathfinder = new Pathfinder();
		
		source = new MapNode(1, 3);
		target = new MapNode(5, 4);
	}
	
	@Test
	public void findPath_sourceEqualsTarget(){
		List<MapNode> path = findPath(source, source);
		
		assertEquals(1, path.size());
		assertEquals(source, path.get(0));
	}
	
	@Test
	public void findPath_toUnexistentTarget_shouldReturnEmptyPath(){
		List<MapNode> path = findPath(source, new MapNode(100, 100));
		
		assertTrue(path.isEmpty());
	}

	@Test
	public void findPath_toAdjacentTest(){
		MapNode adjacent = new MapNode(source.getX() + 1, source.getY());
		
		List<MapNode> path = findPath(source, adjacent);
		
		assertEquals(2, path.size());
		assertEquals(path.get(0), source);
		assertEquals(path.get(1), adjacent);
	}
	
	@Test
	public void findPath_shouldContourObstacles(){
		List<MapNode> path = findPath(source, target);
		
		printPathInMap(mediumMap, path);
		
		assertThat(path, hasItems(source, target));
	}
	@Test
	public void findPath_inLargeMap(){
		MapNode s = new MapNode(3, 10);
		MapNode t = new MapNode(14, 3);

		graph = new PathGraph();
		fillGraph(graph, largeMap);
		
		List<MapNode> path = pathfinder.findPath(graph, s, t);
		
		printPathInMap(largeMap, path);
		
		assertThat(path.size(), is(greaterThan(2)));
		assertEquals(path.get(0), s);
		assertEquals(path.get(path.size() - 1), t);
	}

	private List<MapNode> findPath(MapNode source, MapNode target) {
		List<MapNode> path = pathfinder.findPath(graph
				, source
				, target);
		return path;
	}
	
	private static void addObstacles(boolean[][] map, Rectangle[] obstacles) {
		for(Rectangle obstacle : obstacles){
			int x = (int)obstacle.x, y = (int)obstacle.y;
			int width = (int)obstacle.width, height = (int)obstacle.height;

			for(int row=y; row < y + height; ++row){
				for(int col= x; col < x + width; ++col){
					try {
						map[row][col] = false;
					} catch (RuntimeException e) {
						System.out.println("Exception at row: " + row + ", col: " + col + " "
								+ "of " + map.length + ", " + map[0].length + ") "
								+ "in obstacle[" + x + ", " + y + ", " + width + ", " + height + "]");
						throw e;
					}
				}
			}
		}
	}

	public static boolean[][] createMap(int width, int height) {
		boolean[][] map = new boolean[height][width];
		
		for(int i=0; i < height; ++i){
			for (int j = 0; j < width; j++) {
				map[i][j] = true;
			}
		}
		
		return map;
	}

	public static void printMap(boolean[][] map) {
		printPathInMap(map, null);
	}
	public static void printPathInMap(boolean[][] map, List<MapNode> path) {
		for(int i=0; i < map.length; ++i){
			System.out.print(String.format("%2d : ", i));
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j]){
					if(path == null || ! path.contains(new MapNode(j, i))){
						System.out.print("[ ] ");
					}
					else{
						System.out.print("[*] ");
					}
				}
				else{
					System.out.print("[X] ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private void fillGraph(PathGraph graph, boolean[][] map) {
		int rows = map.length;
		for (int y = 0; y < rows; y++) {
			int cols = map[y].length;
			for(int x=0; x < cols; ++x){
				addNode(graph, map, x, y, rows, cols);
			}
		}
	}

	private void addNode(PathGraph graph, boolean[][] map, int x, int y, int rows, int cols) {
		MapNode node = new MapNode(x, y);
		graph.addNode(node);
		
		if(!map[node.getY()][node.getX()]){
			return;
		}
		
		for(MapNode neighbour : getNeighbours(x, y)){
			int nx = neighbour.getX();
			int ny = neighbour.getY();
			if(! between(nx, 0, cols) || !between(ny, 0, rows)){
				continue;
			}
			if(map[ny][nx]){
				graph.addNode(neighbour);
				graph.addAdjacent(node, neighbour);
			}
		}
	}

	private boolean between(int value, int lessBound, int greaterBound) {
		return value >= lessBound && value < greaterBound;
	}

	private MapNode[] getNeighbours(int x, int y) {
		return new MapNode[]{
				new MapNode(x - 1, y), new MapNode(x + 1, y)
				, new MapNode(x, y - 1), new MapNode(x, y + 1)};
	}
}
