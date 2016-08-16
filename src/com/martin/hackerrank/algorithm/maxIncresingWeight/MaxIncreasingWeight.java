package com.martin.hackerrank.algorithm.maxIncresingWeight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * 
 * 
 * A subsequence of a sequence is a sequence which is obtained by deleting zero
 * or more elements from the sequence.
 * 
 * You are given a sequence A in which every element is a pair of integers i.e A
 * = [(a1, w1), (a2, w2),..., (aN, wN)].
 * 
 * For a subseqence B = [(b1, v1), (b2, v2), ...., (bM, vM)] of the given
 * sequence :
 * 
 * We call it increasing if for every i (1 <= i < M ) , bi < bi+1. Weight(B) =
 * v1 + v2 + ... + vM. Task: Given a sequence, output the maximum weight formed
 * by an increasing subsequence.
 * 
 * Input: The first line of input contains a single integer T. T test-cases
 * follow. The first line of each test-case contains an integer N. The next line
 * contains a1, a2 ,... , aN separated by a single space. The next line contains
 * w1, w2, ..., wN separated by a single space.
 * 
 * @author mmathew
 *
 */

public class MaxIncreasingWeight {
	private Result max = new Result(Integer.MIN_VALUE, Integer.MIN_VALUE);

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int count = scanner.nextInt();
		scanner.nextLine();
		Tuple[][] tuples = new Tuple[count][];
		int index = 0;
		while (index < count) {
			int range = scanner.nextInt();
			scanner.nextLine();
			tuples[index] = new Tuple[range];
			String[] seq = scanner.nextLine().split(" ");
			String[] weight = scanner.nextLine().split(" ");
			for (int i = 0; i < range; i++) {
				tuples[index][i] = new Tuple(Long.parseLong(seq[i]), Long.parseLong(weight[i]));
			}
			index++;
		}

		for (Tuple[] tuple : tuples) {
			Map<Integer, Result> res = new HashMap<>();
			MaxIncreasingWeight maxIncreasingWeight = new MaxIncreasingWeight();
		//	 maxIncreasingWeight.maxIncreasingWeight(tuple, tuple.length,
			// res);
			System.out.println(maxIncreasingWeight.maxIncreasingWeight(tuple));
			//System.out.println(res.get(tuple.length).weight);
			 //System.out.println(maxIncreasingWeight.maxWeight(tuple));

		}
	}

	public long maxIncreasingWeight(Tuple[] tuple) {
		Map<Long, Long> resMap = new HashMap<>();
		long sum = tuple[tuple.length - 1].weight;
		long max = 0;
		resMap.put(tuple[tuple.length - 1].sequence, sum);
		for (int i = tuple.length - 2; i >= 0; i--) {
			if (tuple[i].sequence <= tuple[i + 1].sequence) {
				sum += tuple[i].weight;
			} else {
				if (resMap.containsKey(tuple[i].sequence)) {
					sum = Math.max(resMap.get(tuple[i].sequence), tuple[i].weight);
				} else {
					sum = tuple[i].weight;
				}
			}
			max = Math.max(sum, max);
			resMap.put(tuple[i].sequence, max);
		}

		return max;
	}

	public long maxWeight(Tuple[] tuple) {
		long[] maxWt = new long[tuple.length];
		for (int i = 0; i < tuple.length; i++) {
			maxWt[i] = tuple[i].weight;
		}

		for (int i = 1; i < tuple.length; i++) {
			for (int j = 0; j < i; j++) {
				if (tuple[j].sequence < tuple[i].sequence && maxWt[i] < maxWt[j] + tuple[i].weight) {
					maxWt[i] = maxWt[j] + tuple[i].weight;
				}
			}
		}

		long maxWts = 0;
		for (int i = 0; i < maxWt.length; i++) {
			if (maxWts < maxWt[i]) {
				maxWts = maxWt[i];
			}
		}
		return maxWts;
	}


	public Result maxIncreasingWeight(Tuple[] tuple, int length, Map<Integer, Result> resultMap) {

		if (resultMap.containsKey(length)) {
			return resultMap.get(length);
		} else if (length == 1) {

			return new Result(tuple[length - 1].weight, tuple[length - 1].sequence);
		} else {
			Result maxResult = new Result(Integer.MIN_VALUE, Integer.MIN_VALUE);
			for (int i = 1; i < length; i++) {
				Result res = maxIncreasingWeight(tuple, i, resultMap);
				if (tuple[i - 1].sequence < tuple[length - 1].sequence && maxResult.weight < res.weight + tuple[length-1].weight) {
					maxResult = new Result(res.weight + tuple[length - 1].weight, tuple[length - 1].sequence);
				}
			}

			if (maxResult.weight > max.weight) {
				max = maxResult;
			}
			resultMap.put(length, max);
			return maxResult;
		}

	}

	static class Result {

		private long weight;
		private long maxSequence;

		Result(long weight2, long maxSequence) {
			this.weight = weight2;
			this.maxSequence = maxSequence;
		}

		public long getWeight() {
			return weight;
		}

		public long getMaxSequence() {
			return maxSequence;
		}

	}

	static class Tuple {

		private long sequence;
		private long weight;

		Tuple(long sequence, long weight) {
			this.weight = weight;
			this.sequence = sequence;
		}

		public long getSequence() {
			return sequence;
		}

		public long getWeight() {
			return weight;
		}

		public String toString() {
			return sequence + ":" + weight;
		}

	}

	static class SequenceRes {
		private long weight;
		private List<Tuple> tupleList;

		public SequenceRes() {
			tupleList = new ArrayList<>();
		}

		public long getWeight() {
			return weight;
		}

		public void setWeight(long weight) {
			this.weight = weight;
		}

		public List<Tuple> getTupleList() {
			return tupleList;
		}

		public void setTupleList(Tuple tuple) {
			tupleList.add(tuple);
		}

		public String toString() {
			return tupleList.toString();
		}
	}
}