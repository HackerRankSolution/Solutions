package com.martin.hackerrank.datastructure.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Minimum Average Waiting Time
 * 
 * Tieu owns a pizza restaurant and he manages it in his own way. While in a
 * normal restaurant, a customer is served by following the first-come,
 * first-served rule, Tieu simply minimizes the average waiting time of his
 * customers. So he gets to decide who is served first, regardless of how sooner
 * or later a person comes.
 * 
 * Different kinds of pizzas take different amounts of time to cook. Also, once
 * he starts cooking a pizza, he cannot cook another pizza until the first pizza
 * is completely cooked. Let's say we have three customers who come at time t=0,
 * t=1, & t=2 respectively, and the time needed to cook their pizzas is 3, 9, &
 * 6 respectively. If Tieu applies first-come, first-served rule, then the
 * waiting time of three customers is 3, 11, & 16 respectively. The average
 * waiting time in this case is (3 + 11 + 16) / 3 = 10. This is not an optimized
 * solution. After serving the first customer at time t=3, Tieu can choose to
 * serve the third customer. In that case, the waiting time will be 3, 7, & 17
 * respectively. Hence the average waiting time is (3 + 7 + 17) / 3 = 9.
 * 
 * Help Tieu achieve the minimum average waiting time. For the sake of
 * simplicity, just find the integer part of the minimum average waiting time.
 * 
 **/
public class MinAverageWaitingTime {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int query = scanner.nextInt();
		scanner.nextLine();

		Customer[] customers = new Customer[query];
		int index = 0;
		while (index < query) {
			String[] line = scanner.nextLine().split(" ");
			customers[index] = new Customer(Long.parseLong(line[0]), Long.parseLong(line[1]));
			index++;
		}
		MinAverageWaitingTime minAverageWaitingTime = new MinAverageWaitingTime();
		System.out.println(minAverageWaitingTime.getMinAvgWaitingTime(customers));

	}

	public long getMinAvgWaitingTime(Customer[] customers) {
		Comparator<Customer> arrivalTimeC = (Customer c1, Customer c2) -> c1.arrivalTime.compareTo(c2.arrivalTime);

		Queue<Customer> arrivalTimeQ = new PriorityQueue<>(arrivalTimeC);

		Comparator<Customer> cookingTimeC = (Customer c1, Customer c2) -> c1.pizzaCookingTime
				.compareTo(c2.pizzaCookingTime);

		Queue<Customer> cookingTimeQ = new PriorityQueue<>(cookingTimeC);

		for (Customer customer : customers) {
			arrivalTimeQ.add(customer);
		}

		long totalTime = 0;
		long totalWaitTime = 0;

		while (true) {
			Customer customer = null;

			if (!cookingTimeQ.isEmpty()) {
				customer = cookingTimeQ.poll();
			} else if (!arrivalTimeQ.isEmpty()) {
				customer = arrivalTimeQ.poll();
				
			}
			
			if(customer==null)
			{
				break;
			}else
			{
				totalTime =customer.arrivalTime <= totalTime ? (totalTime+customer.pizzaCookingTime):(customer.arrivalTime+customer.pizzaCookingTime);
				totalWaitTime +=(totalTime-customer.arrivalTime);
			}
			
			while(!arrivalTimeQ.isEmpty()&&arrivalTimeQ.peek().arrivalTime < totalTime)
			{
				cookingTimeQ.add(arrivalTimeQ.poll());
			}
			

		}
		return totalWaitTime/customers.length;

	}

	public void sort(Customer[] customers) {
		int mid = customers.length / 2;

		for (int i = mid; i >= 0; i--) {
			heapify(customers, customers.length - 1, i);
		}
		long t1 = 0;
		for (int i = customers.length - 1; i >= 0; i--) {
			swap(customers, 0, i);
			t1 += customers[i].pizzaCookingTime;
			heapify(customers, i, 0);
		}

		long totalTime = 0;
		long pizzaCookTime = 0;
		for (int i = customers.length - 1; i >= 0; i--) {
			pizzaCookTime += customers[i].pizzaCookingTime;
			totalTime += (pizzaCookTime - customers[i].arrivalTime);
		}
		System.out.println(totalTime / customers.length);
	}

	private void heapifyWIthTime(Customer[] customers, int length, int index, long time) {
		int left = 2 * index + 1;
		int right = 2 * index + 2;
		int temp = index;

		if (left <= length && (customers[temp].arrivalTime + time > customers[left].arrivalTime)
				&& (customers[left].pizzaCookingTime < customers[temp].pizzaCookingTime)) {
			temp = left;
		}
		if (right <= length && (customers[temp].arrivalTime + time > customers[right].arrivalTime)
				&& (customers[right].pizzaCookingTime < customers[temp].pizzaCookingTime)) {
			temp = right;
		}
		if (temp != index) {
			swap(customers, temp, index);
			heapifyWIthTime(customers, length, temp, time + customers[temp].pizzaCookingTime);
		}
	}

	public void heapify(Customer[] customers, int length, int index) {
		int left = 2 * index + 1;
		int right = 2 * index + 2;
		int temp = index;

		if (left <= length && isFirstSmall(customers, left, temp)) {
			temp = left;
		}
		if (right <= length && isFirstSmall(customers, right, temp)) {
			temp = right;
		}

		if (temp != index) {
			swap(customers, index, temp);
			heapify(customers, length, temp);
		}

	}

	public boolean isFirstSmall(Customer[] customers, int first, int second) {
		if (customers[first].arrivalTime < customers[second].arrivalTime) {
			return true;
		} else if (customers[first].arrivalTime == customers[second].arrivalTime
				&& customers[first].pizzaCookingTime <= customers[second].pizzaCookingTime) {
			return true;
		}
		return false;
	}

	private void swap(Customer[] customers, int index, int temp) {

		// TODO Auto-generated method stub
		Customer tempC = customers[index];
		customers[index] = customers[temp];
		customers[temp] = tempC;

	}

	static class Customer {

		private Long arrivalTime;
		private Long pizzaCookingTime;

		public Customer(long arrivalTime, long pizzaCookingTime) {
			this.arrivalTime = arrivalTime;
			this.pizzaCookingTime = pizzaCookingTime;
		}

		public long getArrivalTime() {
			return arrivalTime;
		}

		public long getPizzaCookingTime() {
			return pizzaCookingTime;
		}

	}

}
