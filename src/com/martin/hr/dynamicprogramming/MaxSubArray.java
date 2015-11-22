package com.martin.hr.dynamicprogramming;

import java.util.Scanner;

public class MaxSubArray {

	public static void main(String[] args) {

		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			int numOfInput = scanner.nextInt();
			scanner.nextLine();
			long[][] inputArr = new long[numOfInput][];
			for (int i = 0; i < numOfInput; i++) {
				int len = scanner.nextInt();
				scanner.nextLine();
				String inputStr = scanner.nextLine();
				String[] inputTokens = inputStr.split(" ");
				inputArr[i] = new long[inputTokens.length];

				for (int j = 0; j < inputArr[i].length; j++) {
					inputArr[i][j] = Long.parseLong(inputTokens[j]);
				}
			}

			for (int k = 0; k < inputArr.length; k++) {
				maxSubArray(inputArr[k]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Kadaners Algo
	 * 
	 * @param input
	 * @return
	 */
	public static void maxSubArray(long[] input) {
		long maxContinous = Long.MIN_VALUE;

		long nonContinouSum = Long.MIN_VALUE;

		long sum = 0;

		for (int i = 0; i < input.length; i++) {

			if (input[i] < 0) {
				if (nonContinouSum < input[i]) {
					nonContinouSum = input[i];
				}
			} else {
				if (nonContinouSum < 0) {
					nonContinouSum = input[i];
				} else {
					nonContinouSum += input[i];
				}
			}

			sum += input[i];
			if (sum < input[i]) {
				sum = input[i];
			}
			if (sum > maxContinous) {
				maxContinous = sum;
			}
		}
		System.out.print(maxContinous + " " + nonContinouSum);
		System.out.println();
	}

}
