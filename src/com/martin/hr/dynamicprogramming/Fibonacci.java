package com.martin.hr.dynamicprogramming;

public class Fibonacci {
	public static void main(String[] args) {
		NthTerm(1, 0, 5);
	}
	
	
	public static void NthTerm(long nPlus,long n, long numberOfterms)
	{
		for(int i=2;i<=numberOfterms;i++)
		{
			long temp = nPlus;
			nPlus = n+nPlus;
			n=temp;
		}
		System.out.println(nPlus);
		
		
	}

}
