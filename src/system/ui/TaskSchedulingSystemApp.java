package system.ui;

import system.domain.*;
import java.util.*;

public class TaskSchedulingSystemApp {
	
	private static final Random intRandom = new Random();

	public static void main(String[] args) {
		
		Job jobs[] = new Job[6];
        jobs[0] = new Job(0, 1, 3, 5);
        jobs[1] = new Job(1, 2, 5, 6);
        jobs[2] = new Job(2, 4, 6, 5);
        jobs[3] = new Job(3, 6, 7, 4);
        jobs[4] = new Job(4, 5, 8, 11);
        jobs[5] = new Job(5, 7, 9, 2);
        WeightedJobSchedulingMaximumProfit mp = new WeightedJobSchedulingMaximumProfit();
        System.out.println(mp.maximum(jobs));
		
		/*
		Random intRandom = new Random();
		JobComparatorByDeadline byDeadline = new JobComparatorByDeadline();
		JobComparatorByProfit byProfit = new JobComparatorByProfit();
		ArrayList<Job> jobList = new ArrayList<>();
		*/
		/*
		jobList.add(new Job(0, getRandomInteger(1, 5), getRandomInteger(1, 1000)));
		jobList.add(new Job(1, getRandomInteger(1, 5), getRandomInteger(1, 1000)));
		jobList.add(new Job(2, getRandomInteger(1, 5), getRandomInteger(1, 1000)));
		*/
		
		/*
		Collections.sort(jobList, byDeadline);
		System.out.println(jobList);
		
		Collections.sort(jobList, byProfit);
		System.out.println(jobList);
		
	*/
		
	}
	
	public static int getRandomInteger(int start, int end) {
		return start + intRandom.nextInt(end - start + 1);
	}

}
