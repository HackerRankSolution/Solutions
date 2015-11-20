package com.martin.hackerrank.algorithm.strings.prefixmatching;

import java.io.File;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Solution solution = new Solution();
		//char[][] input = new char[1][];
//		input[0]="aabeaabecdaba".toCharArray();
//		//input[0]="ababcd".toCharArray();		
	//input[0]="aaaaaaaaaaasefg".toCharArray();
//		input[1] = "ababcaa".toCharArray();
//		input[2] = "aa".toCharArray();
//		input[3] = "abcde".toCharArray();

		Scanner scanner = null;
		try {
			 scanner = new Scanner(new File("//development//hr//input.txt"));
			 int len = scanner.nextInt();
			 scanner.nextLine();
			 char[][] input = new char[len][];
			 for (int i = 0; i < input.length; i++) {
			 input[i] = scanner.nextLine().toCharArray();
			 }

			long[] res = solution.matchSimilarity(input);
			for (long num : res) {
				System.out.println(num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		//	scanner.close();
		}

	}

	public long[] matchSimilarity(char[][] strs) {
		long[] resPrefixMatch = new long[strs.length];

		for (int i = 0; i < strs.length; i++) {
			char[] str = strs[i];
			// int res = strs[i].length;
			// for (int j = 1; j < str.length; j++) {
			// res += matchPrefix(str, j);
			// }
			resPrefixMatch[i] = matchPrefix(str);

		}

		return resPrefixMatch;

	}

	public int matchPrefix(char[] str, int startIndex) {
		int temp = startIndex;
		for (int i = 0; i < str.length && startIndex < str.length; i++, startIndex++) {
			if (str[i] != str[startIndex]) {
				return i;
			}
		}
		return str.length - temp;

	}

	public long matchPrefix(char[] s) {
		long[] z = new long[s.length];
		z[0] = s.length;
		int L = 0, R = 0;
		for (int i = 1; i < s.length; i++) {
			if (i > R) {
				L = R = i;
				while (R < s.length && s[R - L] == s[R])
					R++;
				z[i] = R - L;
				R--;
			} else {
				int k = i - L;
				int val = R-i+1;
				if (z[k] < val)
					z[i] = z[k];
				else {
					L = i;
					while (R < s.length && s[R - L] == s[R])
						R++;
					z[i] = R - L;
					R--;
				}
			}
		}
		long sum = 0;
		for (int i = 0; i < s.length; i++) {
			sum += z[i];
		}

		return sum;
	}
}
