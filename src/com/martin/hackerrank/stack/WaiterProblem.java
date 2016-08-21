package com.martin.hackerrank.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class WaiterProblem {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		int count = Integer.parseInt(line.split(" ")[1]);
		String nums = scanner.nextLine();
		Stack<Integer> numList = new Stack<>();
		String[] numbers = nums.split(" ");
		for (String number : numbers) {
			numList.add(Integer.parseInt(number));
		}
		WaiterProblem waiterProblem = new WaiterProblem();
		waiterProblem.solve(numList, count);
	}

	public void solve(Stack<Integer> inp, int q) {
		List<Stack<Integer>> stackList = new ArrayList();
		int count = 0;
		int prime = 1;
		while (count < q) {
			prime = getNextPrime(prime);
			Stack<Integer> stack = new Stack<>();
			Stack<Integer> temp = new Stack<>();
			for (;!inp.isEmpty();) {
				int num = inp.pop();
				if (num % prime == 0) {
					stack.push(num);
				} else {
					temp.add(num);
				}
			}
			stackList.add(stack);
			inp = temp;
			count++;

		}

		for (Stack<Integer> stack : stackList) {
			while (!stack.isEmpty()) {
				System.out.println(stack.pop());
			}
		}
		while (!inp.isEmpty()) {
			System.out.println(inp.pop());
		}

	}

	private int getNextPrime(int n) {
		if (n == 1) {
			return 2;
		}

		for (int j = n + 1;; j++) {
			if (j % 2 != 0) {
				int mid = j / 2;
				boolean notFound = false;
				for (int i = 3; i <= mid; i++) {
					if (j % i == 0) {
						notFound = true;
						break;
					}
				}
				if (!notFound) {
					return j;
				}
			}
		}

	}

}
