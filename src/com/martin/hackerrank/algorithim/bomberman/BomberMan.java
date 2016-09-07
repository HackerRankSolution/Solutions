package com.martin.hackerrank.algorithim.bomberman;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class BomberMan {

	private int[][] grid;
	private int row;
	private int col;

	BomberMan(int row, int col) {
		grid = new int[row][col];
		this.row = row;
		this.col = col;
	}

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(System.in);
		String[] inp = scanner.nextLine().split(" ");
		BomberMan bomberMan = new BomberMan(Integer.parseInt(inp[0]), Integer.parseInt(inp[1]));
		// bomberMan.checkFinalLayout(Long.parseLong(inp[2]));
		for (int i = 0; i < Integer.parseInt(inp[0]); i++) {
			String[] line = scanner.nextLine().split("");
			for (int j = 0; j < line.length; j++) {
				if (line[j].equals("O")) {
					bomberMan.init(i, j, 0);
				}
				else
				{
					bomberMan.init(i, j, -1);
				}
			}
		}
		bomberMan.checkFinalLayout(Long.parseLong(inp[2]));

	}

	public void init(int row, int col, int time) {
		grid[row][col] = time;
	}

	public void checkFinalLayout(long N) {

		if (N >= 2) {
			if ((N / 2) % 2 == 1) {
				if (N % 2 == 0) {
					layMinesEveryWhere(2);
				} else if (N % 2 == 1) {
					layMinesEveryWhere(2);
					blast(3);
				}
			} else if ((N / 2) % 2 == 0) {
				if (N % 2 == 0) {
					layMinesEveryWhere(2);
					blast(3);
					layMinesEveryWhere(4);
				}else if(N%2==1)
				{
					layMinesEveryWhere(2);
					blast(3);
					layMinesEveryWhere(4);
					blast(5);
				}
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == -1) {
					System.out.print('.');
				} else {
					System.out.print('O');
				}
			}
			System.out.println();
		}

	}

	private void blast(int num) {
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if ((num - grid[r][c]) == 3) {
					bomb(grid, r, c);
				}
			}
		}
	}

	private void layMinesEveryWhere(int num) {
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (grid[r][c] == -1) {
					grid[r][c] = num;
				}
			}
		}
	}

	private void bomb(int[][] grid2, int r, int c) {
		int temp = grid2[r][c];
		grid2[r][c] = -1;
		if (r > 0) {
			grid[r - 1][c] = (grid[r - 1][c] == temp ? temp : -1);
		}
		if (r < grid2.length - 1) {
			grid[r + 1][c] = (grid[r + 1][c] == temp ? temp : -1);
		}
		if (c > 0) {
			grid[r][c - 1] = (grid[r][c - 1] == temp ? temp : -1);
		}
		if (c < grid2[r].length - 1) {
			grid[r][c + 1] = (grid[r][c + 1] == temp ? temp : -1);
		}

	}

}
