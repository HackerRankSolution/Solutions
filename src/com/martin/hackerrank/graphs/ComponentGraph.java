package com.martin.hackerrank.graphs;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ComponentGraph {

	// private Set<Node> edgeList;
	private Map<Integer, Node> nodeMap;

	public ComponentGraph() {
		// edgeList = new HashSet<>();
		nodeMap = new HashMap<>();
	}

	public static void main(String[] args) {

		ComponentGraph componentGraph = new ComponentGraph();
		Scanner scanner = new Scanner(System.in);
		int count = scanner.nextInt();
		scanner.nextLine();
		while (count > 0) {
			String[] values = scanner.nextLine().split(" ");
			componentGraph.createGraph(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
			count--;
		}
		componentGraph.countComponents();
	}

	public void countComponents() {

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		Collection<Node> nodeCollection = nodeMap.values();
		for (Node node : nodeCollection) {
			if (!node.isVisited) {
				int count = dfs(node)+1;
				min = Math.min(min, count);
				max = Math.max(max, count);
			}
		}
		System.out.println(min + " " + max);
	}

	public int dfs(Node node) {
		if (node.isVisited) {
			return 0;
		} else {
			node.setVisited(true);
			Set<Node> edges = node.edgeSet;
			int count = 0;
			for (Node edge : edges) {
				if (!edge.isVisited) {

					count += (dfs(edge) + 1);
				}
			}
			return count;

		}

	}

	public void createGraph(int node1, int node2) {
		if (nodeMap.containsKey(node1)) {
			Node node = nodeMap.get(node1);
			if (nodeMap.containsKey(node2)) {
				Node edge = nodeMap.get(node2);
				node.setEdge(edge);
				edge.setEdge(node);
			} else {
				Node newNode = new Node(node2);
				node.setEdge(newNode);
				newNode.setEdge(node);
				nodeMap.put(node2, newNode);

			}
		} else {

			Node newNode = new Node(node1);
			if (nodeMap.containsKey(node2)) {
				Node edge = nodeMap.get(node2);
				newNode.setEdge(edge);
				edge.setEdge(newNode);
			} else {
				Node newEdge = new Node(node2);
				newNode.setEdge(newEdge);
				newEdge.setEdge(newNode);
				nodeMap.put(node2, newEdge);

			}
			nodeMap.put(node1, newNode);

		}

	}

	static class Node {

		private int val;
		private boolean isVisited;
		private Set<Node> edgeSet;

		public boolean isVisited() {
			return isVisited;
		}

		public void setVisited(boolean isVisited) {
			this.isVisited = isVisited;
		}

		Node(int val) {
			this.val = val;
			edgeSet = new HashSet<>();
		}

		public int getVal() {
			return val;
		}

		public void setVal(int val) {
			this.val = val;

		}

		public void setEdge(Node node) {
			edgeSet.add(node);
		}

		public Set<Node> getEdge() {
			return edgeSet;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + val;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (val != other.val)
				return false;
			return true;
		}

		public String toString() {
			return "val : " + val;
		}
	}

}
