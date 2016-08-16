package com.martin.hackerrank.algorithm.maxIncresingWeight;

import java.util.Scanner;

public class CubeSummation {

	private long[][][] cube;

	public CubeSummation(int n) {
		cube = new long[n][n][n];
	}

	public void update(int x, int y, int z, long value) {
		cube[x - 1][y - 1][z - 1] = value;
	}

	public long add(int x1, int x2, int y1, int y2, int z1, int z2) {
		long sum = 0;
		for (int x = x1 - 1; x < x2; x++) {
			for (int y = y1 - 1; y < y2; y++) {
				for (int z = z1 - 1; z < z2; z++) {
					sum += cube[x][y][z];

				}
			}
		}

		return sum;

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int count = scanner.nextInt();
		scanner.nextLine();
		int indexCount = 0;
		while (indexCount < count) {
			String[] sizeQuery = scanner.nextLine().split(" ");
			int size = Integer.parseInt(sizeQuery[0]);
			int numQuery = Integer.parseInt(sizeQuery[1]);
			CubeSummation cubeSummation = new CubeSummation(size);
			while (numQuery > 0) {
				String operation = scanner.nextLine();
				String[] splits = operation.split(" ");
				if (operation.startsWith("UPDATE")) {

					int x = Integer.parseInt(splits[1]);
					int y = Integer.parseInt(splits[2]);
					int z = Integer.parseInt(splits[3]);
					cubeSummation.update(x, y, z, Long.parseLong(splits[4]));
				} else if (operation.startsWith("QUERY")) {
					int x1 = Integer.parseInt(splits[1]);
					int y1 = Integer.parseInt(splits[2]);
					int z1 = Integer.parseInt(splits[3]);

					int x2 = Integer.parseInt(splits[4]);
					int y2 = Integer.parseInt(splits[5]);
					int z2 = Integer.parseInt(splits[6]);
					System.out.println(cubeSummation.add(x1, x2, y1, y2, z1, z2));

				}
				numQuery--;
			}
			indexCount++;
		}

	}

	

}
