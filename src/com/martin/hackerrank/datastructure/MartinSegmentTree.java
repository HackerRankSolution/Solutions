package com.martin.hackerrank.datastructure;

public class MartinSegmentTree {

	private int[] segmentTree;

	public MartinSegmentTree(int[] arr) {
		segmentTree = new int[(int) (2 * Math.pow(2.0, Math.ceil(Math.log((double) arr.length) / Math.log(2.0d))))+1];
	}

	public int buildST(int first, int last, int[] arr, int stIndex) {
		if (first == last) {
			segmentTree[stIndex] = arr[first];
			return arr[first];
		} else {
			int mid = (first + last) / 2;
			segmentTree[stIndex] = buildST(first, mid, arr, 2 * stIndex + 1)
					+ buildST(mid + 1, last, arr, 2 * stIndex);
			return segmentTree[stIndex];
		}

	}

	public int query(int qStart, int qEnd, int aStart, int aEnd, int segmentTreeIndex) {
		if (qStart <= aStart && aEnd <= qEnd) {
			return segmentTree[segmentTreeIndex];
		} else if (qStart > aEnd || qEnd < aStart) {
			return 0;
		} else {
			int mid = (aStart + aEnd) / 2;
			return query(qStart, qEnd, aStart, mid, 2 * segmentTreeIndex + 1)
					+ query(qStart, qEnd, mid + 1, aEnd, 2 * segmentTreeIndex + 2);

		}

	}

	public void update(int index, int updateValue, int aStart, int aEnd, int segmentIndex) {
		if (index < aStart || index > aEnd) {
			return;
		}

		segmentTree[segmentIndex] += updateValue;

		if (aStart != aEnd) {
			int mid = (aStart + aEnd) / 2;
			update(index, updateValue, aStart, mid, 2 * segmentIndex + 1);
			update(index, updateValue, mid + 1, aEnd, 2 * segmentIndex + 2);
		}

	}

	public static void main(String[] args) {
		int[] arr = { 1, 3, 5, 7, 9, 11 };
		MartinSegmentTree mst = new MartinSegmentTree(arr);
		mst.buildST(0, arr.length - 1, arr, 1);
		System.out.println(mst.segmentTree);
		System.out.println(mst.query(1, 3, 0, arr.length - 1, 0));
		mst.update(2, 2, 0, arr.length - 1, 0);
		System.out.println(mst.query(1, 3, 0, arr.length - 1, 0));
	}

}
