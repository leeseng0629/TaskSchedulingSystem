package system.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class WeightedJobSchedulingMaximumProfit {
	private Comparator<Job> byFinishTime;
	private List<Integer> profitList;
	private List<LinkedList<Integer>> jobLinkedList;
	
	private List<Integer> jobSequenced;
	
	private int maxProfit;
	private int jobSequencedIndex;
	
	public WeightedJobSchedulingMaximumProfit() {
		byFinishTime = new JobComparatorByFinishTime();
		profitList = new ArrayList<>();
		jobLinkedList = new ArrayList<>();
		
		jobSequenced = new LinkedList<>();
		
		maxProfit = Integer.MIN_VALUE;
		jobSequencedIndex = 0;
	}
	
	public int maximum(ArrayList<Job> jobList) {
		Collections.sort(jobList, byFinishTime);
		profitList.add(jobList.get(0).getProfit());
		jobLinkedList.add(new LinkedList<>());
		jobLinkedList.get(0).add(jobList.get(0).getJobId());
		
		for (int i = 1; i < jobList.size(); i++) {
			
			profitList.add(jobList.get(i).getProfit());
			jobLinkedList.add(new LinkedList<>());
			jobLinkedList.get(i).add(jobList.get(i).getJobId());
			
			for (int j = i - 1; j >= 0; j--) {
				
				if (jobList.get(j).getFinishTime() <= jobList.get(i).getStartTime()) {
					
					int previousAndNowProfit = jobList.get(i).getProfit() + profitList.get(j);
					int element = 0;
					boolean changed = false;
					
					if (previousAndNowProfit > profitList.get(i)) {
						element = previousAndNowProfit;
						profitList.set(i, element);
						changed = true;
					}
					
					if (changed) {
						LinkedList<Integer> previousList = jobLinkedList.get(j);
						jobLinkedList.get(i).clear();
						jobLinkedList.get(i).add(jobList.get(i).getJobId());
						jobLinkedList.get(i).addAll(0, previousList);
					}
					break;
					
				}
				
			}
			
		}
		
		for (int i = 0; i < profitList.size(); i++) {
			if (maxProfit < profitList.get(i)) {
				maxProfit = profitList.get(i);
				this.jobSequencedIndex = i;
			}
		}
		
		jobSequenced = jobLinkedList.get(jobSequencedIndex);
		
		return this.maxProfit;
		
	}
	
	public List<Integer> getJobSequenced() {
		return this.jobSequenced;
	}
	
	public List<LinkedList<Integer>> getJobLinkedList() {
		return this.jobLinkedList;
	}
	
	public List<Integer> getProfitList() {
		return this.profitList;
	}
	
//	public void testingLinkedList(ArrayList<Job> jobList) {
//		jobSequenced.add(new LinkedList());
//		LinkedList<Integer> ll = jobSequenced.get(0);
//		
//		ll.add(jobList.get(0).getJobId());
//		ll.add(jobList.get(2).getJobId());
//		
//		System.out.println(ll.get(0));
//		System.out.println(jobSequenced.get(0).get(0));
//		System.out.println(jobSequenced.get(0).get(1));
//		System.out.println(jobSequenced);
//	}
	
//	public int maximum(Job[] jobs) {
//		int T[] = new int[jobs.length];
//		
//		Arrays.sort(jobs, byFinishTime);
//		
//		T[0] = jobs[0].getProfit();
//		
//		for (int i = 1; i < jobs.length; i++) {
//			
//			T[i] = Math.max(jobs[i].getProfit(), T[i - 1]);
//			
//			for (int j = i - 1; j >= 0; j--) {
//				
//				if (jobs[j].getFinishTime() <= jobs[i].getStartTime()) {
//					
//					T[i] = Math.max(T[i], jobs[i].getProfit() + T[j]);
//					break;
//					
//				}
//				
//			}
//			
//		}
//		
//		int maxVal = Integer.MIN_VALUE;
//		
//		for (int val : T) {
//			System.out.print(val + ", ");
//			if (maxVal < val) maxVal = val;
//		}
//		
//		return maxVal;
//	}
	
}
