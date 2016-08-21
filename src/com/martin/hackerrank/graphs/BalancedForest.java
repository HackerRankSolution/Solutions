package com.martin.hackerrank.graphs;

import java.util.ArrayList;
import java.util.List;

public class BalancedForest {


public boolean dfs(GraphNode node, int count)
{
	if(count == 0)
	{
		return true;
	}
	else if(node!=null)
	{
		List<GraphNode> edges = node.getEdges();
		boolean isCut = false;
		for(GraphNode edge :edges)
		{
			if(isCut)
			{
				edges.remove(edge);
                dfs(edge, count);
			}
			if(dfs(edge, count-edge.getCoins()))
			{
			isCut = true;
				
			}
		}
		
	}
	
	
}


}



class GraphNode
{
	private int coins;
	private List<GraphNode> edges;
	
	public GraphNode(int coins) {
		// TODO Auto-generated constructor stub
	this.coins = coins;
	edges = new ArrayList<>();
	}
	public int getCoins()
	{
		return coins;
	}
	
	public void setEdges(GraphNode edge)
	{
		edges.add(edge);
	}
	public List<GraphNode> getEdges()
	{
		return edges;
	}
	
}
