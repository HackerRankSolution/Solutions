package com.martin.booking;

import java.util.Scanner;

public class JohnZizi {

	
  public static long numberOfDestination(int n,int m,int c)
  {
	  
	  int val = n-c+m-1;
	  long perm = 1;
	  for(int i=val;i>1;i--)
	  {
		  perm *=i;
	  }
	  return perm;
	  
  }
	
  
  public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	String[] token = scanner.nextLine().split(" ");
	System.out.println(numberOfDestination(Integer.parseInt(token[0]), Integer.parseInt(token[1]), Integer.parseInt(token[2])));
			
}
  
  
	
	
}
