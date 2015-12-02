package org.example.game.pathfinding;

public class PathGraphPopulator 
{
	/**
	 * popula o grafo dos caminhos percorrifeis apartir de uma matris de floats
	 * @param graph = grafo caminhavel resultante
	 * @param worldMap matriz de floats que representam o mundo
	 * @param tileSize = tamanho de cada tile
	 */
	public static void populateFromMatrix(PathGraph graph, boolean[][] worldMap, int tileSize)
	{
		int linSize = worldMap.length;
		int colSize = worldMap[0].length;
		
		for(int lin = 0; lin < linSize ; lin++)
		{
			for(int col = 0; col < colSize ; col++)
			{
				if(worldMap[lin][col])
					addNode(lin, col, tileSize, graph, linSize, colSize, worldMap);
			}
		}
	}
	
	/**
	 * adiciona um node ao grafo de caminhos andaveis em PathGraph
	 * por seguinte chama o metodo addAdjs
	 * 
	 * @param lin =  linha atual
	 * @param col = coluna atual
	 * @param tileSize =  tamanho do tile para achar o x e y
	 * @param graph = o caminho a ser gerado
	 * @param linSize = numero de linhas maximas permitidas
	 * @param colSize = numero de colunas maximas permitidas
	 * @param worldMap = mapa com booleanos indicando os tiles andaveis
	 */
	private static void addNode(int lin, int col, int tileSize, PathGraph graph, int linSize, int colSize, boolean[][] worldMap)
	{
		float x = lin*tileSize + tileSize/2;
		float y = col*tileSize + tileSize/2;
		MapNode mn = new MapNode(x, y);
		graph.addNode(mn);
		addAdjs(mn, lin, col, tileSize, graph, linSize, colSize, worldMap);
	}
	
	/**
	 * adiciona os nodes adjacentes ao mapNode passado
	 * 
	 * @param mn = mapnode em que se vai adicionar os adiacentes
	 * @param lin =  linha atual
	 * @param col = coluna atual
	 * @param tileSize =  tamanho do tile para achar o x e y
	 * @param graph = o caminho a ser gerado
	 * @param linSize = numero de linhas maximas permitidas
	 * @param colSize = numero de colunas maximas permitidas
	 * @param worldMap = mapa com booleanos indicando os tiles andaveis
	 */
	private static void addAdjs(MapNode mn, int lin, int col, int tileSize, PathGraph graph, int linSize, int colSize, boolean[][] worldMap)
	{
		int i = 0, j= 0;
		int cases = 4;
		//int cases = 8; //uncoment se quiser tornar adjacente as diagonais
		for(int k = 0; k < cases; k++)
		{
			switch (k) 
			{
			case 0:
				i = lin -1;
				j = col;
				break;
			case 1:
				i = lin +1;
				j = col;
				break;
			case 2:
				i = lin;
				j = col -1;
				break;
			case 3:
				i = lin;
				j = col +1;
				break;
			/* //ucoment se quier tornar adjacnete as diagonais
			case 4:
				i = lin -1;
				j = col -1;
			case 5:
				i = lin -1;
				j = col +1;
			case 6:
				i = lin +1;
				j = col +1;
			case 7:
				i = lin +1;
				j = col -1;
			 */
			default:
				break;
			}
			
			boolean plusThenZero = i >= 0 && j >= 0;
			boolean lessThenSize = i < linSize && j < colSize;
			if (worldMap[i][j] && plusThenZero && lessThenSize)
			{
				float x = i*tileSize + tileSize/2;
				float y = j*tileSize + tileSize/2;
				MapNode na = new MapNode(x, y);
				graph.addAdjacent(mn, na);
			}
		}
	}
}
