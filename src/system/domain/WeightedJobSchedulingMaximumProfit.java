package system.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class WeightedJobSchedulingMaximumProfit {
	private Comparator<Job> byFinishTime;
	private List<Job> jobList;
	
	private List<Integer> profitList;
	private int maxProfit;
	
	private List<LinkedList<Job>> jobLinkedList;
	private List<Job> jobSequenced;
	private int jobSequencedIndex;
	
	public WeightedJobSchedulingMaximumProfit(List<Job> jobList) {
		this.byFinishTime = new JobComparatorByFinishTime();
		
		this.profitList = new ArrayList<>();
		this.maxProfit = Integer.MIN_VALUE;
		
		this.jobList = jobList;
		this.jobLinkedList = new ArrayList<>();
		this.jobSequenced = new LinkedList<>();
		this.jobSequencedIndex = 0;
	}
	
	public void calculateMaxProfitAndSequencingJob() {
		int counter = 0;
		while (jobList.size() != 0) {
			profitList = new ArrayList<>();
			jobLinkedList = new ArrayList<>();
			jobSequenced = new LinkedList<>();
			maxProfit = Integer.MIN_VALUE;
			jobSequencedIndex = 0;
			
			Collections.sort(jobList, byFinishTime);
			profitList.add(jobList.get(0).getProfit());
			jobLinkedList.add(new LinkedList<>());
			jobLinkedList.get(0).add(jobList.get(0));
			
			for (int i = 1; i < jobList.size(); i++) {
				profitList.add(jobList.get(i).getProfit());
				jobLinkedList.add(new LinkedList<>());
				jobLinkedList.get(i).add(jobList.get(i));
				
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
							LinkedList<Job> previousList = jobLinkedList.get(j);
							jobLinkedList.get(i).clear();
							jobLinkedList.get(i).add(jobList.get(i));
							jobLinkedList.get(i).addAll(0, previousList);
						}
					}
				}
			}
			
			for (int i = 0; i < profitList.size(); i++) {
				if (maxProfit <= profitList.get(i)) {
					maxProfit = profitList.get(i);
					this.jobSequencedIndex = i;
				}
			}
			
			jobSequenced = jobLinkedList.get(jobSequencedIndex);
			jobList.removeAll(jobSequenced);
			System.out.println(counter + " maxProfit: " + maxProfit);
			System.out.println(counter + " jobList: " + jobList);
			System.out.println(counter + " jobLinkedList: " + jobLinkedList);
			System.out.println(counter + " jobSequenced: " + jobSequenced);
			System.out.println(counter + " profitList: " + profitList);
			counter++;
		}
	}
	
	public List<Job> getJobSequenced() {
		return this.jobSequenced;
	}
	
	public List<LinkedList<Job>> getJobLinkedList() {
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
