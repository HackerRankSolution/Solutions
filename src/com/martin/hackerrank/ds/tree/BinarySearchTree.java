package com.martin.hackerrank.ds.tree;

public class BinarySearchTree {

	
	
	boolean checkBST(Node root) {
		if(root!=null)
		{
			if(checkIfLess(root.left, root.data)&&checkIfMore(root.right, root.data))
			{
				return checkBST(root.left) && checkBST(root.right);
			}
			else
			{
				return false;
			}
		}
		return true;
        
    }
	
	
	public boolean checkIfLess(Node node, int val)
	{
		if(node==null)
		{
			return true;
		}
		else if(node.data < val)
		{
			return checkIfLess(node.left, val) && checkIfLess(node.right, val);
		}
		else
		{
			return false;
		}
		
	}
	Node root = null;
	Node first = null;
	Node second = null;
	Node last = null;
	Node prev = null;
	
	public void fixBST()
	{
		
		
		fixBSTUtil(root);
		if(first!=null&&last!=null)
		{
			swap(first,last);
		} else if(last == null)
		{
			swap(first,second);
		}
		
		
	}
	
	
	
	
	private void swap(Node first, Node last) {
		int temp = first.data;
		first.data=last.data;
		last.data = temp;
		
	}


	private void fixBSTUtil(Node root) {
		if(root!=null)
		{
			
			fixBSTUtil(root.left);
			if(prev!=null&&prev.data > root.data)
			{
				if(first==null)
				{
					first = root;
					second = prev;
				}
				else{
					last = root;
				}
					
				
			}
			prev =root;
			fixBSTUtil(root.right);
		}
		
	}


	public boolean checkIfMore(Node node, int val)
	{
		if(node==null)
		{
			return true;
		}
		else if(node.data > val)
		{
			return checkIfMore(node.left, val)&&checkIfMore(node.right, val);
		}
		else
		{
			return false;
		}
		
	}
	
	
	public void inOrder(Node node_1,Node node_2,Node node_3)
	{
		if(node_1!=null)
		{
			
			inOrder(node_1.left, node_1, node_2);
			inOrder(node_1.right, node_1, node_1.left);
		}
		
		
	}
	
	
	static class Node
	{
		private int data;
		private Node left;
		private Node right;
		
		public Node(int val)
		{
			this.data = val;
		}

		public int getVal() {
			return data;
		}

		public void setVal(int val) {
			this.data = val;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}
		
		
	}
}
