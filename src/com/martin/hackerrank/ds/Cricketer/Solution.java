package com.martin.hackerrank.ds.Cricketer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String input = bufferedReader.readLine();
		String[] tokens = input.split(" ");
		int shots = Integer.parseInt(tokens[0]);
		int field = Integer.parseInt(tokens[1]);
		Range[] shotRanges = new Range[shots];
		for (int i = 0; i < shots; i++) {
			String shotRange = bufferedReader.readLine();
			String[] shotRangeSplit = shotRange.split(" ");
			shotRanges[i] = new Range(Integer.parseInt(shotRangeSplit[0]), Integer.parseInt(shotRangeSplit[1]));
		}
		Range[] fieldRange = new Range[field];
		for (int i = 0; i < field; i++) {
			String fielsRange = bufferedReader.readLine();
			String[] fielRange = fielsRange.split(" ");
			fieldRange[i] = new Range(Integer.parseInt(fielRange[0]), Integer.parseInt(fielRange[1]));
		}
		Arrays.sort(fieldRange);
		Arrays.sort(shotRanges);
//		int count = 0;
//		for (Range range : shotRanges) {
//			count += binarySearch(fieldRange, 0, fieldRange.length - 1, range);
//		}
//		System.out.println(count);
		
		findTheCount(fieldRange, shotRanges);
		
	}
	
	
	
	public static void findTheCount(Range[] fields,Range[] shot)
	{
		int fieldCount = 0;
		int shotsCount = 0;
		int count = 0;
		while(fieldCount < fields.length && shotsCount < shot.length)
		{
			if(fields[fieldCount].end < shot[shotsCount].start)
			{
				fieldCount++;
			}else if(fields[fieldCount].start > shot[shotsCount].end)
			{
				shotsCount++;
			}
			else
			{
				
				int tempShotCount = shotsCount;
				while(tempShotCount < shot.length && fields[fieldCount].end >= shot[tempShotCount].start)
				{
					if(fields[fieldCount].isWithinRange(shot[tempShotCount]))
					{
						count++;
					}
					tempShotCount++;
				}
				fieldCount++;
				
				
				
			}
			
		}
		
		System.out.println(count);
		
	}

	private static int binarySearch(Range[] field, int start, int end, Range shot) {
		if (start <= end) {
			int mid = (start + end) / 2;
			if (field[mid].isWithinRange(shot)) {
				int first = getFirst(field, start, mid, shot);
				int last = getLast(field, mid, end, shot);
				return last - first + 1;
			} else if (field[mid].compareTo(shot) == 1) {
				return binarySearch(field, start, mid - 1, shot);
			} else {
				return binarySearch(field, mid + 1, end, shot);
			}

		}

		return 0;
	}

	private static int getFirst(Range[] field, int start, int end, Range shot) {
		if (start <= end) {
			int mid = (start + end) / 2;
			if ((mid > start && (field[mid].isWithinRange(shot) && !field[mid - 1].isWithinRange(shot)))
					|| (mid == start && field[mid].isWithinRange(shot))) {
				return mid;
			} else if (shot.isWithinRange(field[mid]) && field[mid].compareTo(shot) == 1) {
				return getFirst(field, start, mid - 1, shot);
			} else if (!shot.isWithinRange(field[mid]) && field[mid].compareTo(shot) == 1) {
				return getFirst(field, mid + 1, end, shot);
			} else if (shot.isWithinRange(field[mid]) && field[mid].compareTo(shot) == -1) {
				return getFirst(field, start, mid - 1, shot);
			} else {
				return getFirst(field, mid + 1, end, shot);
			}
		}
		return -1;
	}

	private static int getLast(Range[] field, int start, int end, Range shot) {
		if (start <= end) {
			int mid = (start + end) / 2;
			if ((mid < end && (field[mid].isWithinRange(shot) && !field[mid + 1].isWithinRange(shot))
					|| (mid == end && field[mid].isWithinRange(shot)))) {
				return mid;
			} else if (shot.isWithinRange(field[mid]) && field[mid].compareTo(shot) == 1) {
				return getLast(field, mid + 1, end, shot);
			} else if (!shot.isWithinRange(field[mid]) && field[mid].compareTo(shot) == 1) {
				return getLast(field, start, mid - 1, shot);
			} else if (shot.isWithinRange(field[mid]) && field[mid].compareTo(shot) == -1) {
				return getLast(field, mid + 1, end, shot);
			} else {
				return getLast(field, start, mid - 1, shot);
			}
		}
		return -1;
	}

	static class Range implements Comparable<Range> {

		private int start;
		private int end;

		public Range(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public boolean isWithinRange(Range range) {
			if (this.end < range.start || this.start > range.end) {
				return false;
			}
			return true;
		}

		@Override
		public int compareTo(Range o) {
			if (this.start < o.start) {
				return -1;
			} else if (this.start > o.start) {
				return 1;
			} else {
				return this.end - o.end;
			}

		}

		public String toString() {
			return "Range : " + start + " - " + end;
		}

	}

}
