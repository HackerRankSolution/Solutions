package com.martin.hackerrank.queues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SegmentTree {

	private long[] segment = null;

	public SegmentTree(int n) {
		segment = new long[(int) (2 * Math.pow(2.0, Math.ceil(Math.log((double) n) / Math.log(2.0d))))+n];
		for (int i = 0; i < segment.length; i++) {
			segment[i] = Long.MAX_VALUE;
		}
	}

	public long buildBT(int start, int end, long[] arr, int stIndex) {
		if (start == end) {
			segment[stIndex] = arr[start];
			return arr[start];
		} else {
			int mid = (start + end) / 2;
			return segment[stIndex] = Math.max(buildBT(start, mid, arr, 2 * stIndex ),
					buildBT(mid + 1, end, arr, 2 * stIndex + 1));
		}

	}

	public long query(int start, int end, int qStart, int qEnd, int stIndex) {
		if (qStart <= start && qEnd >= end) {
			return segment[stIndex];
		} else if (qStart > end || qEnd < start) {
			return Long.MAX_VALUE;

		} else {

			int mid = (start + end) / 2;
			if (qEnd <= mid) {
				return query(start, mid, qStart, qEnd, 2 * stIndex );

			} else if (qStart >= mid+1) {
				return query(mid+1, end, qStart, qEnd, 2 * stIndex + 1);
			} else {
				return Math.max(query(start, mid, qStart, qEnd, 2 * stIndex ),
						query(mid + 1, end, qStart, qEnd, 2 * stIndex + 1));
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedRead = new BufferedReader(new InputStreamReader(System.in));
		String firstToken[] = bufferedRead.readLine().split(" ");
		int count = Integer.parseInt(firstToken[0]);
		int qCount = Integer.parseInt(firstToken[1]);
		String[] input = bufferedRead.readLine().split(" ");
		long[] inputArr = new long[input.length];
		for (int i = 0; i < input.length; i++) {
			inputArr[i] = Integer.parseInt(input[i]);
		}
		SegmentTree segmentTree = new SegmentTree(inputArr.length);
		segmentTree.buildBT(0, inputArr.length - 1, inputArr, 1);
		while (qCount > 0) {
			int d = Integer.parseInt(bufferedRead.readLine());
			long min = Long.MAX_VALUE;
			for (int i = 0; i <= inputArr.length - d; i++) {
				min = Math.min(min, segmentTree.query(0, inputArr.length - 1, i, i + d - 1, 1));
			}
			System.out.println(min);
		}

	}

}
