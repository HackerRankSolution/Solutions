package com.martin.hackerrank.algorithim.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class TaskSchedule {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int count = scanner.nextInt();
		scanner.nextLine();
		List<Task> taskList = new ArrayList<>();
		int seq = 0;
		while (count > 0) {
			seq++;
			String[] token = scanner.nextLine().split(" ");
			taskList.add(new Task(seq, Integer.parseInt(token[0]), Integer.parseInt(token[1])));
			count--;
		}
		scehduleTask(taskList);
	}

	public static void scehduleTask(List<Task> taskList) {
		// Collections.sort(taskList);

		int len = 0;
		while (len < taskList.size()) {
			int start = 0;
			int time = 1;
			List<Task> tempList = new ArrayList<>(taskList.subList(0, len+1));
			Collections.sort(tempList);
			int max = Integer.MIN_VALUE;
			Deque<Task> queue = new LinkedList<>();
			int index = -1;

			
			
			
			
			index = binarySearch(tempList, time, start, len );
			if (index >= start) {
				for (int i = start; i <= index; i++) {
					tempList.get(i).reset();
					queue.add(tempList.get(i));
				}
				start = index + 1;
			} else if(start<tempList.size()) {
				tempList.get(start).reset();
				queue.add(tempList.get(start));
				start++;
			}
			
			
			while (start < tempList.size()||!queue.isEmpty()) {
				
				Task task = queue.poll();
				task.decrementTime();
				if (task.getTimeNeeded() <= 0) {
					max = Math.max((time - task.getDeadline()), max);
				} else {
					queue.add(task);
				}
				
				index = binarySearch(tempList, time, start, len );
				if (index >= start) {
					for (int i = start; i <= index; i++) {
						tempList.get(i).reset();
						queue.addFirst(tempList.get(i));
					}
					start = index + 1;
				} else if(queue.isEmpty()&&start<tempList.size()) {
		
					tempList.get(start).reset();
					queue.addFirst(tempList.get(start));
					start++;
				}
				
				time++;
			}
			len++;
			System.out.println(max);
		}

	}

	public static int binarySearch(List<Task> taskList, int time, int start, int end) {
		

	 if (start < end) {
			int mid = (start + end) / 2;
			Task task = taskList.get(mid);
			Task nextTask = taskList.get(mid + 1);

			if (task.getDeadline() <= time && nextTask.getDeadline() > time) {
				return mid;
			} else if (task.getDeadline() <= time) {
				return binarySearch(taskList, time, mid, end);
			} else {
				return binarySearch(taskList, time, start, mid);
			}

		}
		return -1;

	}
	
	
	
	
	static class Task implements Comparable<Task> {

		private int seq;
		private int deadline;
		private int timeNeeded;
		private int deadlineExceeded;
		private int tempTimeNeeded;

		Task(int seq, int deadline, int timeNeeded) {
			this.seq = seq;
			this.deadline = deadline;
			this.timeNeeded = timeNeeded;
		}

		public void reset() {
			tempTimeNeeded = timeNeeded;
		}

		public void decrementTime() {
			tempTimeNeeded--;
		}

		public int getSeq() {
			return seq;
		}

		public int getDeadline() {
			return deadline;
		}

		public int getTimeNeeded() {
			return tempTimeNeeded;
		}

		public int getDeadlineExceeded() {
			return deadlineExceeded;
		}

		public void setDeadlineExceeded(int deadlineExceeded) {
			this.deadlineExceeded = deadlineExceeded;
		}

		@Override
		public int compareTo(Task o) {
			if (this.deadline < o.deadline) {
				return -1;
			} else if (this.deadline == o.deadline) {
				return this.timeNeeded - o.timeNeeded;
			}
			return 1;
		}

	}


}

