package com.martin.hackerrank.algorithm.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class GridSearch {

    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(new File("/development/dump/hr_input/input.txt")));
            int numberOfInputs = scanner.nextInt();
            scanner.nextLine();
            int index = 0;
            int[][][] largeInputArrs = null;
            int[][][] smallInputArrs = null;
            largeInputArrs = new int[numberOfInputs][][];
            smallInputArrs = new int[numberOfInputs][][];
            while (index < numberOfInputs) {
                String largeMatDimension = scanner.nextLine();
                String[] largeMatDimArr = largeMatDimension.split(" ");
                int largRow = Integer.parseInt(largeMatDimArr[0]);
                int largeCol = Integer.parseInt(largeMatDimArr[1]);

                int[][] largeInputArr = new int[largRow][largeCol];
                for (int i = 0; i < largRow; i++) {
                    char[] row = scanner.nextLine().toCharArray();
                    for (int j = 0; j < largeCol; j++) {
                        largeInputArr[i][j] = Integer.parseInt(row[j] + "");
                    }
                }

                largeInputArrs[index] = largeInputArr;
                String[] smallMatDimArr = scanner.nextLine().split(" ");
                int smallRow = Integer.parseInt(smallMatDimArr[0]);
                int smallCol = Integer.parseInt(smallMatDimArr[1]);

                int[][] smallInputArr = new int[smallRow][smallCol];
                for (int i = 0; i < smallRow; i++) {
                    char[] row = scanner.nextLine().toCharArray();
                    for (int j = 0; j < smallCol; j++) {
                        smallInputArr[i][j] = Integer.parseInt(row[j] + "");
                    }
                }

                smallInputArrs[index] = smallInputArr;
                index++;
            }

            for (int k = 0; k < numberOfInputs; k++) {
                gridSearch(largeInputArrs[k], smallInputArrs[k]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gridSearch(int[][] bigArr, int[][] smallArr) {
        int bRow = 0;
        int bCol = 0;
        int sRow = 0;
        int sCol = 0;
        int tempRow = 0;
        int tempCol = 0;
        int initial = 0;
        boolean firtMatch = false;
        while (bRow < bigArr.length) {

            while (bCol < bigArr[bRow].length) {

                if (sRow >= smallArr.length) {
                    System.out.println("YES");
                    return;
                } else if (sCol >= smallArr[sRow].length) {
                    initial = bCol == 0 ? bigArr[bRow].length - sCol : bCol - sCol;
                    break;
                } else if (smallArr[sRow][sCol] == bigArr[bRow][bCol]) {
                    sCol++;
                    if (!firtMatch) {
                        firtMatch = true;
                        tempRow = bRow;
                        tempCol = bCol;
                    }
                } else if (smallArr[sRow][sCol] != bigArr[bRow][bCol]) {
                    if (firtMatch) {
                        firtMatch = false;
                        bRow = tempRow;
                        bCol = tempCol;
                        firtMatch = false;
                    }
                    sCol = 0;
                    sRow = 0;
                }
                bCol++;
            }
            bRow++;
            sRow++;
            sCol = 0;
            bCol = initial;
            initial = 0;
        }

        if (sRow >= smallArr.length) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");


    }
}


