package system.ui;

import system.domain.*;
import java.util.*;

public class TaskSchedulingSystemApp {
	
	private static final Random intRandom = new Random();

	public static void main(String[] args) {
		
		Random intRandom = new Random();
		JobComparatorByDeadline byDeadline = new JobComparatorByDeadline();
		JobComparatorByProfit byProfit = new JobComparatorByProfit();
		ArrayList<Job> jobList = new ArrayList<>();
		jobList.add(new Job(0, getRandomInteger(1, 5), getRandomInteger(1, 1000)));
		jobList.add(new Job(1, getRandomInteger(1, 5), getRandomInteger(1, 1000)));
		jobList.add(new Job(2, getRandomInteger(1, 5), getRandomInteger(1, 1000)));
		
		Collections.sort(jobList, byDeadline);
		System.out.println(jobList);
		
		Collections.sort(jobList, byProfit);
		System.out.println(jobList);
		
	}
	
	public static int getRandomInteger(int start, int end) {
		return start + intRandom.nextInt(end - start + 1);
	}

}
