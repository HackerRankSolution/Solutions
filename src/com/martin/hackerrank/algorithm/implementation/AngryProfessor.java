package com.martin.hackerrank.algorithm.implementation;

import java.util.Scanner;

public class AngryProfessor {
	
	public static void main(String[] args) {
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(System.in);
			int count = scanner.nextInt();
			scanner.nextLine();
			class InputTuple
			{
				long[] arrivalTime;
				long threshold;
			}
			InputTuple[] inputTuples = new InputTuple[count];
			int indexCount = 0;
			while(indexCount < count)
			{
				InputTuple inputTuple = new InputTuple();
				String input = scanner.nextLine();
			    long threshold = Long.parseLong(input.split(" ")[1]);
			    String arrivalTime = scanner.nextLine();
			    String[] arrivalTokens = arrivalTime.split(" ");
			    long[] arrivalTimes = new long[arrivalTokens.length];
			    int index = 0;
			    for(String token : arrivalTokens)
			    {
			    	arrivalTimes[index++]=Long.parseLong(token);
			    }
			    inputTuple.arrivalTime=arrivalTimes;
			    inputTuple.threshold=threshold;
			    inputTuples[indexCount]=inputTuple;
			    indexCount++;
			}
			for(InputTuple inputTuple : inputTuples)
			{
				classCancelledOrNot(inputTuple.arrivalTime, inputTuple.threshold);
			}
		}
		catch(Exception e)
		{
			
		}
	}

	private static void classCancelledOrNot(long[] arrivalTime, long threshold) {

		int count = 0;
		int index = 0;
		while (index < arrivalTime.length) {
			if (arrivalTime[index] <= 0) {
				count++;
			}
			if (count >= threshold) {
				System.out.println("NO");
				return;
			}
			index++;
		}
		System.out.println("YES");
	}

}
