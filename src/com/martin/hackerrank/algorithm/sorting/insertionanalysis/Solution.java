package com.martin.hackerrank.algorithm.sorting.insertionanalysis;

import java.io.File;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// // long[] arr = { 2,1,3,1,2};
		// long[] arr = {1,1,1,2,2};
		Solution solution = new Solution();
//		 Result res = solution.mergeSort(arr, 0, arr.length - 1);
//		 System.out.println(res.res);

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("//development//hr//input.txt"));
			int input1 = scanner.nextInt();

			int count = 0;
			while (count < input1) {
				int input = scanner.nextInt();
				scanner.nextLine();
				long[] inLong = new long[input];
				String line = scanner.nextLine();
				String tokens[] = line.split(" ");
				for (int i = 0; i < inLong.length; i++) {
					inLong[i] = Long.parseLong(tokens[i]);
				}
				Result res = solution.mergeSort(inLong, 0, inLong.length - 1);
				System.out.println(res.res);
				count++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class Result {

		long[] resArr;
		long res = 0;

		public Result(long[] resArr, long res) {
			super();
			this.resArr = resArr;
			this.res = res;
		}

		public long[] getResArr() {
			return resArr;
		}

		public void setResArr(long[] resArr) {
			this.resArr = resArr;
		}

		public long getRes() {
			return res;
		}

		public void setRes(long res) {
			this.res = res;
		}
	}

	public Result mergeSort(long[] arr, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			Result leftResult = mergeSort(arr, start, mid);
			Result rightResult = mergeSort(arr, mid + 1, end);
			int leftIndex = 0;
			int rightIndex = 0;
			long[] resArr = new long[end - start + 1];
			long count = 0;
			int index = 0;
			while (leftIndex <= leftResult.resArr.length - 1 && rightIndex <= rightResult.resArr.length - 1) {
				if (leftResult.resArr[leftIndex] > rightResult.resArr[rightIndex]) {
					count += (leftResult.resArr.length - leftIndex);
					resArr[index] = rightResult.resArr[rightIndex++];
				} else {

					resArr[index] = leftResult.resArr[leftIndex++];
				}
				index++;
			}
			int remainingIndex = -1;
			int newEnd = -1;
			long[] newArr = null;
			if (leftIndex > leftResult.resArr.length - 1) {
				remainingIndex = rightIndex;
				newEnd = rightResult.resArr.length - 1;
				newArr = rightResult.resArr;
			} else if (rightIndex > rightResult.resArr.length - 1) {
				remainingIndex = leftIndex;
				newEnd = leftResult.resArr.length - 1;
				newArr = leftResult.resArr;
			}

			for (; remainingIndex <= newEnd; remainingIndex++) {
				resArr[index++] = newArr[remainingIndex];
			}
			return new Result(resArr, count + leftResult.getRes() + rightResult.getRes());

		}
		return new Result(new long[] { arr[start] }, 0);
	}
}
