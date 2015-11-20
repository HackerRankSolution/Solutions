package com.martin.hackerrank.numbers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Long[] inputLongArr = {-1l,2l,-3l};
		long[] queryLongArr = {1,-2,3};

		Scanner scanner = null;
		try {
			//scanner = new Scanner(new File("//development//hr//input.txt"));
			scanner = new Scanner(System.in);
			int lengthInput = scanner.nextInt();
			scanner.nextLine();
			String input = scanner.nextLine();
			inputLongArr = new Long[lengthInput];
			int i = 0;
			for (String inputToken : input.split(" ")) {
				inputLongArr[i++] = Long.parseLong(inputToken);
			}

			int lengthQuery = scanner.nextInt();
			scanner.nextLine();
			String inputQuery = scanner.nextLine();
			queryLongArr = new long[lengthQuery];
			int k = 0;
			for (String inputToken : inputQuery.split(" ")) {
				queryLongArr[k++] = Long.parseLong(inputToken);
			}
			playWIthNumber(inputLongArr, queryLongArr);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void playWIthNumber(Long[] num, long[] queies) {
		Result res = preProcess(num);
		long qsum = 0;

		for (long query : queies) {
			long sum = 0;
			qsum += query;
			if (qsum < 0) {
				sum = Math.abs(res.sumOfNegative + qsum * res.numOfnegative);
				int index = binarySearch(res.arrOfPositive, qsum, 0, res.arrOfPositive.size() - 1);
				
				if(index>=0)
				{
					sum += Math.abs(res.positivePrefixSum[index] + qsum*(index+1));
				}

				sum += Math.abs((res.positivePrefixSum[res.positivePrefixSum.length - 1]
						- (index >= 0 ? res.positivePrefixSum[index] : 0)
						+ qsum * (res.positivePrefixSum.length - (index < 0 ? 0 : index+1))));

				System.out.println(sum);

			} else {

				sum = res.sumOfPositive + Math.abs(qsum * res.numOfPositive);
				int index = binarySearch(res.arrOfNegative, qsum, 0, res.arrOfNegative.size() - 1);
			if(index>=0)
			{
					sum += Math.abs(res.negativePrefixSum[index] * -1 + qsum*(index+1));
			}	
				sum += Math.abs(-(res.negativePrefixSum[res.negativePrefixSum.length - 1]
						- (index >= 0 ? res.negativePrefixSum[index] : 0))
						+ qsum * (res.negativePrefixSum.length - (index < 0 ? 0 : index+1)));
				System.out.println(sum);

			}
		}

	}

	public static int binarySearch(List<Long> numList, long query, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			if (Math.abs(numList.get(start)) > Math.abs(query)) {
				return -1;
			} else if (Math.abs(numList.get(end)) < Math.abs(query)) {
				return end ;
			} else if ((Math.abs(numList.get(mid)) <= Math.abs(query)
					&& Math.abs(numList.get(mid + 1)) >= Math.abs(query))) {
				return mid;
			} else if (mid > 0 && (Math.abs(numList.get(mid)) >= Math.abs(query)
					&& Math.abs(numList.get(mid - 1)) <= Math.abs(query))) {
				return mid - 1;
			}

			else if (mid < numList.size() && Math.abs(numList.get(mid)) < Math.abs(query)
					&& Math.abs(numList.get(mid + 1)) < Math.abs(query)) {
				return binarySearch(numList, query, mid + 1, end);
			} else if (mid > 0 && Math.abs(numList.get(mid)) > Math.abs(query)
					&& Math.abs(numList.get(mid - 1)) > Math.abs(query)) {
				return binarySearch(numList, query, start, mid - 1);
			}
		}
		return -1;
	}

//	private int binarySearch(long[] num,int start,int end)
//	{
//		if(start < end)
//		{
//			int mid = (start+end)/2;
//			if(num[mid]>=0 && (num[mid -1]) <0)
//			{
//				return mid-1;
//			} else if(num[mid] <0 && num[mid+1]>=0)
//			{
//				return mid;
//			} else if(num[mid]>0 && num[mid-1]>0)
//			{
//				return binarySearch(num, start, mid-1);
//			}
//			else 
//			
//		}
//	}
	
	
	private static Result preProcess(Long[] nums) {
		Result result = new Result();
		Arrays.sort(nums,new Comparator<Long>() {

			@Override
			public int compare(Long o1, Long o2) {
				// TODO Auto-generated method stub
				if(o1.longValue()<0)
				{
					o1*=-1;
				}
				if(o2.longValue()<0)
				{
					o2*=-1;
				}
				return o1.compareTo(o2);
			}
		});
		result.arrOfNegative = new ArrayList<Long>();
		result.arrOfPositive = new ArrayList<>();
		for (long num : nums) {
			if (num < 0) {
				result.arrOfNegative.add(Math.abs(num));
				result.numOfnegative++;
				result.sumOfNegative += num;
			} else {
				result.arrOfPositive.add(num);
				result.sumOfPositive += num;
				result.numOfPositive++;
			}

		}
//		Collections.sort(result.arrOfNegative);
//		Collections.sort(result.arrOfPositive);

		long[] prefixSUmForNegative = new long[result.arrOfNegative.size()];
		int i = 1;
		prefixSUmForNegative[0] = result.arrOfNegative.get(0);
		for (; i < result.arrOfNegative.size(); i++) {
			prefixSUmForNegative[i] = result.arrOfNegative.get(i) + prefixSUmForNegative[i - 1];
		}
		result.negativePrefixSum = prefixSUmForNegative;

		long[] prefixSUmForPositive = new long[result.arrOfPositive.size()];
		i = 1;
		prefixSUmForPositive[0] = result.arrOfPositive.get(0);
		for (; i < result.arrOfPositive.size(); i++) {
			prefixSUmForPositive[i] = result.arrOfPositive.get(i) + prefixSUmForPositive[i - 1];
		}
		result.positivePrefixSum = prefixSUmForPositive;
		return result;

	}

	static class Result {
		long sumOfNegative;
		int numOfnegative;
		long sumOfPositive;
		int numOfPositive;
		List<Long> arrOfPositive;
		List<Long> arrOfNegative;
		long[] negativePrefixSum;
		long[] positivePrefixSum;

		public long[] getNegativePrefixSum() {
			return negativePrefixSum;
		}

		public void setNegativePrefixSum(long[] negativePrefixSum) {
			this.negativePrefixSum = negativePrefixSum;
		}

		public long[] getPositivePrefixSum() {
			return positivePrefixSum;
		}

		public void setPositivePrefixSum(long[] positivePrefixSum) {
			this.positivePrefixSum = positivePrefixSum;
		}

		public long getSumOfNegative() {
			return sumOfNegative;
		}

		public void setSumOfNegative(long sumOfNegative) {
			this.sumOfNegative = sumOfNegative;
		}

		public int getNumOfnegative() {
			return numOfnegative;
		}

		public void setNumOfnegative(int numOfnegative) {
			this.numOfnegative = numOfnegative;
		}

		public long getSumOfPositive() {
			return sumOfPositive;
		}

		public void setSumOfPositive(long sumOfPositive) {
			this.sumOfPositive = sumOfPositive;
		}

		public int getNumOfPositive() {
			return numOfPositive;
		}

		public void setNumOfPositive(int numOfPositive) {
			this.numOfPositive = numOfPositive;
		}

		public List<Long> getArrOfPositive() {
			return arrOfPositive;
		}

		public void setArrOfPositive(List<Long> arrOfPositive) {
			this.arrOfPositive = arrOfPositive;
		}

		public List<Long> getArrOfNegative() {
			return arrOfNegative;
		}

		public void setArrOfNegative(List<Long> arrOfNegative) {
			this.arrOfNegative = arrOfNegative;
		}

	}

}
