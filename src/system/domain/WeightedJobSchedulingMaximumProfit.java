package system.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class WeightedJobSchedulingMaximumProfit {
	private Comparator<Job> byFinishTime;
	private List<Job> jobList;
	private int counter;
	
	// Results of profit and sequenced job is store in this ArrayList
	private List<Integer> maxProfit;
	private List<List<Job>> jobSequenced;
	
	public WeightedJobSchedulingMaximumProfit() {
		this.byFinishTime = new JobComparatorByFinishTime();
		this.counter = 0;
		
		// Results of profit and sequenced job is store in this ArrayList
		this.jobSequenced = new ArrayList<>();
		this.maxProfit = new ArrayList<>();
	}
	
	public WeightedJobSchedulingMaximumProfit(List<Job> jobList) {
		this.byFinishTime = new JobComparatorByFinishTime();
		this.jobList = jobList;
		this.counter = 0;
		
		// Results of profit and sequenced job is store in this ArrayList
		this.jobSequenced = new ArrayList<>();
		this.maxProfit = new ArrayList<>();
	}
	
	public void calculateMaxProfitAndSequencingJob() {
		int counter = 0;
		while (jobList.size() != 0) {
			List<Integer> profitList = new ArrayList<>();
			List<LinkedList<Job>> jobSequencedList = new ArrayList<>();
			
			int jobSequencedIndex = 0;
			
			int maxProfit = Integer.MIN_VALUE;
			List<Job> jobSequenced = new LinkedList<>();
			
			Collections.sort(jobList, byFinishTime);
			profitList.add(jobList.get(0).getProfit());
			jobSequencedList.add(new LinkedList<>());
			jobSequencedList.get(0).add(jobList.get(0));
			
			for (int i = 1; i < jobList.size(); i++) {
				profitList.add(jobList.get(i).getProfit());
				jobSequencedList.add(new LinkedList<>());
				jobSequencedList.get(i).add(jobList.get(i));
				
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
							LinkedList<Job> previousList = jobSequencedList.get(j);
							jobSequencedList.get(i).clear();
							jobSequencedList.get(i).add(jobList.get(i));
							jobSequencedList.get(i).addAll(0, previousList);
						}
					}
				}
			}
			
			for (int i = 0; i < profitList.size(); i++) {
				if (maxProfit <= profitList.get(i)) {
					maxProfit = profitList.get(i);
					jobSequencedIndex = i;
				}
			}
			
			jobSequenced = jobSequencedList.get(jobSequencedIndex);
			this.jobList.removeAll(jobSequenced);
			
			this.maxProfit.add(counter, maxProfit);
			this.jobSequenced.add(counter, jobSequenced);
			counter++;
		}
		this.counter = counter;
	}
	
	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}
	
	public List<List<Job>> getJobSequenced() {
		return this.jobSequenced;
	}
	
	public List<Job> getSpecifiedJobSequenced(int index) {
		return this.jobSequenced.get(index);
	}
	
	public List<Integer> getMaxProfit() {
		return this.maxProfit;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
}
