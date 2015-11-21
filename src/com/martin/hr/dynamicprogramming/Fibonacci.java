package com.martin.hr.dynamicprogramming;

import java.math.BigInteger;
import java.util.Scanner;

public class Fibonacci {
	public static void main(String[] args) {
		
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(System.in);
			String line = scanner.nextLine();;
			String[] tokens = line.split(" ");
			long nthTerm = Long.parseLong(tokens[0]);
			long nthPlusTerm = Long.parseLong(tokens[1]);
			int numOfTerms = Integer.parseInt(tokens[2]);
			NthTerm(tokens[1],tokens[0] ,numOfTerms);
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		
	}
	
	
	public static void NthTerm(String nPlus,String n, int numberOfterms)
	{
		BigInteger nPlusBig = new BigInteger(nPlus);
		BigInteger nBig = new BigInteger(n);
		for(int i=3;i<=numberOfterms;i++)
		{
			BigInteger temp = nPlusBig;
			nPlusBig = nBig.add(nPlusBig.multiply(nPlusBig));
			nBig=temp;
		}
		System.out.println(nPlusBig.toString());
		
		
	}

}
