package com.martin.hackerrank.directConnection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Solution {
	final long MODULO = 1000000007;
	static Map<Character, Character> maps = new HashMap<>();
	static {

		maps.put(']', '[');
		maps.put('}', '{');
		maps.put(')', '(');
	}

	public static void main(String[] args) {
		int[] inp = {6,6,3,9,3,5,1};
		Arrays.sort(inp);
		System.out.println(numberOfPairs(inp, 12));
		
		
	}
	
	
	/* Write your custom functions here */
	static void mergeArray(int []a, int []b, int M ){
	    int[] output = new int[a.length+b.length];
	    int indexA = 0;
	    int indexB = 0;
	    int index =0;
	    while(indexA < a.length && indexB < b.length)
	    {
	    	if(a[indexA] <= b[indexB])
	    	{
	    		output[index]=a[indexA++];
	    		
	    	}
	    	else
	    	{
	    		output[index]=a[indexB++];
	    	}
	    	index++;
	    }
	    if(indexA == a.length)
	    {
	    	for(int j=indexB;j<b.length;j++)
	    	{
	    		output[index++]=b[j];
	    	}
	    }
	    else if(indexB == b.length)
	    {
	    	for(int j=indexA;j<a.length;j++)
	    	{
	    		output[index++]=a[j];
	    	}
	    }
	    System.out.print('{');
	    for(int i=0;i<output.length;i++)
	    {
	    	
	    	if(i < output.length-1)
	    	{
	    	System.out.print(output[i]+',');
	    	}
	    	
	    }
	    
	    System.out.print(output[output.length-1]+'');
	    System.out.print('}');
	    
	    
	    
	    
	}
	
	
	
	
	public static int numberOfPairs(int[] arr, long k) {
	    int start = 0;
	    int end = arr.length - 1;
	    int sum = 0;
	 
	    
	   int count = 0;
	 
	    while (start < end) {
	        sum = arr[start] + arr[end];
	        if (sum == k) {
	     
	            count++;
	            start++;
	            end--;
	        } else if (sum < k) {
	           
	            start++;
	        } else {
	            
	            end--;
	        }
	    }
	  return count;
	}
	public static void verify(String str) {
		char[] inp = str.toCharArray();
try{
		Stack<Character> stack = new Stack<>();
		for (char ch : inp) {
			switch (ch) {
			case '[':
			case '{':
			case '(':
				stack.push(ch);
				break;
			case '}':
			case ']':
			case ')':
				if (!maps.get(ch).equals(stack.pop())) {
					System.out.print("NO");
					return;
				}
				break;
			}
		}
		if (stack.isEmpty()) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
}
catch(Exception e)
{
	System.out.print("NO");
}

	}


	public static void divide(int a, int b) {

		try {
			int c = a / b;
		} catch (Exception e) {
			System.out.print("Exception");

		} finally {
			System.out.print("Finally");
		}

	}

	public long getWireLength(City[] cities) {
		Arrays.sort(cities);
		for (int i = 0; i < cities.length; i++) {
			cities[i].setIdx(i + 1);
		}

		Arrays.sort(cities, new PopulationComparator());

		SegmentTree segmentTreeX = new SegmentTree(cities.length);
		SegmentTree segmentTreeCount = new SegmentTree(cities.length);
		long res = 0;
		for (int i = 0; i < cities.length; i++) {
			long suml = segmentTreeX.query(1, cities[i].getIdx(), 1, cities.length, 1);
			long sumr = segmentTreeX.query(cities[i].getIdx(), cities.length, 1, cities.length, 1);
			long numl = segmentTreeCount.query(1, cities[i].getIdx(), 1, cities.length, 1);
			long numr = segmentTreeCount.query(cities[i].getIdx(), cities.length, 1, cities.length, 1);
			res += ((numl * cities[i].getX() - suml) + (sumr - numr * cities[i].getX())) * cities[i].getP();
			res %= MODULO;
			segmentTreeX.update(cities[i].getIdx(), cities[i].getX(), 1, cities.length, 1);
			segmentTreeCount.update(cities[i].getIdx(), 1, 1, cities.length, 1);

		}
		return res;

	}

}

class City implements Comparable<City> {
	private long x;
	private long p;
	private int idx;

	public City(int x, int p) {
		super();
		this.x = x;
		this.p = p;
	}

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public long getP() {
		return p;
	}

	public void setP(long p) {
		this.p = p;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	@Override
	public int compareTo(City o) {
		// TODO Auto-generated method stub
		return (int) (x - o.x);
	}

}

class PopulationComparator implements Comparator<City> {

	@Override
	public int compare(City o1, City o2) {
		// TODO Auto-generated method stub
		return (int) (o1.getP() - o2.getP());
	}

}

class SegmentTree {

	private long[] segmentTree;

	public SegmentTree(int len) {
		segmentTree = new long[(int) (2 * Math.pow(2.0, Math.ceil(Math.log((double) len) / Math.log(2.0d)))) + 1];
	}

	public long query(int qStart, int qEnd, int aStart, int aEnd, int segmentTreeIndex) {
		if (qStart <= aStart && aEnd <= qEnd) {
			return segmentTree[segmentTreeIndex];
		} else if (qStart > aEnd || qEnd < aStart) {
			return 0;
		} else {
			int mid = (aStart + aEnd) / 2;
			return query(qStart, qEnd, aStart, mid, 2 * segmentTreeIndex + 1)
					+ query(qStart, qEnd, mid + 1, aEnd, 2 * segmentTreeIndex);

		}

	}

	public void update(int index, long updateValue, int aStart, int aEnd, int segmentIndex) {
		if (index < aStart || index > aEnd) {
			return;
		}

		segmentTree[segmentIndex] += updateValue;

		if (aStart != aEnd) {
			int mid = (aStart + aEnd) / 2;
			update(index, updateValue, aStart, mid, 2 * segmentIndex + 1);
			update(index, updateValue, mid + 1, aEnd, 2 * segmentIndex);
		}

	}

}
