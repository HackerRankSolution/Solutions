package com.martin.hackerrank.algorithim.bomberman;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SherlockValidString {

	public static void validStringOrNot(String str) {
		char[] chs = str.toCharArray();
		Map<Character, Integer> strMaps = new HashMap<Character, Integer>();
		int numOfChar = 0;
		for (char ch : chs) {
			int count = 1;
			if (strMaps.containsKey(ch)) {
				count = strMaps.get(ch);
				count++;
			}
			strMaps.put(ch, count);
			numOfChar++;
		}
		strMaps = sortByValue(strMaps);
		int numOfDistinctChars = strMaps.size();
		int mod = numOfChar % numOfDistinctChars;
		if (mod <= 1 || (numOfDistinctChars - mod) <= 1) {
			// System.out.println("YES");
			numOfChar += (mod <= (numOfDistinctChars - mod) ? -mod : (numOfDistinctChars - mod));
			int avg = numOfChar / numOfDistinctChars;
			int diff = 0;
			Iterator<Character> keyItr = strMaps.keySet().iterator();
			while (diff <= 1 && keyItr.hasNext()) {
				int temp = numOfChar;
				int count = strMaps.get(keyItr.next());
				if (Math.abs(avg - count) <= count) {
					diff += Math.abs(avg - count);
					numOfChar += Math.abs(avg - count);
				} else {
					diff += count;
					numOfChar -= count;
					numOfDistinctChars--;
				}
				mod = numOfChar % numOfDistinctChars;
				if (!(mod <= 1 || (numOfDistinctChars - mod) <= 1)) {
					diff++;
				} else if (temp != numOfChar) {
					numOfChar += (mod <= (numOfDistinctChars - mod) ? -mod : (numOfDistinctChars - mod));
					avg = numOfChar / numOfDistinctChars;
				}
			}
			if (diff <= 1) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}

		} else {
			System.out.println("NO");
		}
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		validStringOrNot(scanner.nextLine());
	}

}
