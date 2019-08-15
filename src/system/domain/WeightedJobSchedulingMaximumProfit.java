package system.domain;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class WeightedJobSchedulingMaximumProfit {
	private Comparator<Job> byFinishTime;
	private List<Integer> profitList;
	
	private List<Job> jobsLinkList;
	
	public WeightedJobSchedulingMaximumProfit() {
		byFinishTime = new JobComparatorByFinishTime();
		profitList = new ArrayList<>();
		jobsLinkList = new LinkedList<>();
	}
	
	public int maximum(ArrayList<Job> jobList) {
		Collections.sort(jobList, byFinishTime);
		profitList.add(jobList.get(0).getProfit());
		
		for (int i = 1; i < jobList.size(); i++) {
			
			profitList.add(Math.max(jobList.get(i).getProfit(), profitList.get(i - 1)));
			
			for (int j = i - 1; j >= 0; j--) {
				
				if (jobList.get(j).getFinishTime() <= jobList.get(i).getStartTime()) {
					
					int element = Math.max(profitList.get(i), jobList.get(i).getProfit() + profitList.get(j));
					profitList.set(i, element);
					break;
					
				}
				
			}
			
		}
		
		int maxVal = Integer.MIN_VALUE;
		
		for (int val : profitList) if (maxVal < val) maxVal = val;
		
		return maxVal;
	}
	
/*	public int maximum(Job[] jobs) {
		int T[] = new int[jobs.length];
		
		Arrays.sort(jobs, byFinishTime);
		
		T[0] = jobs[0].getProfit();
		
		for (int i = 1; i < jobs.length; i++) {
			
			T[i] = Math.max(jobs[i].getProfit(), T[i - 1]);
			
			for (int j = i - 1; j >= 0; j--) {
				
				if (jobs[j].getFinishTime() <= jobs[i].getStartTime()) {
					
					T[i] = Math.max(T[i], jobs[i].getProfit() + T[j]);
					break;
					
				}
				
			}
			
		}
		
		int maxVal = Integer.MIN_VALUE;
		
		for (int val : T) if (maxVal < val) maxVal = val;
		
		return maxVal;
	}*/
	
}
