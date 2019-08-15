package system.ui;

import system.domain.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TaskSchedulingSystemApp {
	
	private static final Random intRandom = new Random();

	public static void main(String[] args) {
		
		ArrayList<Job> jobList = new ArrayList<>();
		jobList.add(new Job(0, 1, 3, 5));
		jobList.add(new Job(1, 2, 5, 6));
		jobList.add(new Job(2, 4, 6, 5));
		jobList.add(new Job(3, 6, 7, 4));
		jobList.add(new Job(4, 5, 8, 11));
		jobList.add(new Job(5, 7, 9, 2));
		
		WeightedJobSchedulingMaximumProfit mp = new WeightedJobSchedulingMaximumProfit(jobList);
		mp.calculateMaxProfitAndSequencingJob();
        /*System.out.println(mp.getProfitList());
        System.out.println(mp.getJobLinkedList());
        System.out.println(mp.getJobSequenced());*/
        
//        mp.testingLinkedList(jobList);
		
//		Job jobs[] = new Job[6];
//        jobs[0] = new Job(0, 1, 3, 5);
//        jobs[1] = new Job(1, 2, 5, 6);
//        jobs[2] = new Job(2, 4, 6, 5);
//        jobs[3] = new Job(3, 6, 7, 4);
//        jobs[4] = new Job(4, 5, 8, 11);
//        jobs[5] = new Job(5, 7, 9, 2);
//        WeightedJobSchedulingMaximumProfit mp = new WeightedJobSchedulingMaximumProfit();
//        System.out.println("\n" + mp.maximum(jobs));
        
		
		/*
		String csvFile = "jobs.txt";
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        
        ArrayList<Job> jobList = new ArrayList<>();
		
		try {
			br = new BufferedReader(new FileReader(csvFile));
			
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] job = line.split(csvSplitBy);
				int job_id =  Integer.parseInt(job[0]);
				int job_start = Integer.parseInt(job[1]);	
				int job_end = Integer.parseInt(job[2]);
				int job_profit = Integer.parseInt(job[3]);
				jobList.add(new Job(job_id, job_start, job_end, job_profit));
			}
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
		
		System.out.println(jobList);
		*/
		
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
