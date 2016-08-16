package com.martin.hackerrank.datastructure;

public class BinaryIndexedTree {

	
	private int[] bit = null;
	
	public void update(int index,int val)
	{
		
		for(;index < bit.length;index += index&-index)
		{
			bit[index]+=val;
			
		}
		
	}
	
	
	public int query(int index)
	{
		int sum = 0;
		for(;index > 0;index -=index&-index)
		{
			sum+=bit[index];
			
		}
		
		return sum;
	}
	
	public BinaryIndexedTree(int[] arr) {
		// TODO Auto-generated constructor stub
		bit = new int[arr.length+1];
	}
	
	public static void main(String[] args) {
		int[] arr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(arr);
		for(int i=1;i<arr.length;i++)
		{
			binaryIndexedTree.update(i, arr[i]);
			
		}
		
		
		System.out.println(binaryIndexedTree.query(7));
	}
	
	
	
	
	
	
}
