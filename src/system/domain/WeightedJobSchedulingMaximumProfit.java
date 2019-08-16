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
	
	// These ArrayList store the value that used to process
	private List<List<Integer>> profitList;
	private List<List<LinkedList<Job>>> jobSequencedList;
	
	// This ArrayList is to store the index to get the sequenced job
	private List<Integer> jobSequencedIndex;
	
	// Results of profit and sequenced job is store in this ArrayList
	private List<Integer> maxProfit;
	private List<List<Job>> jobSequenced;
	
	public WeightedJobSchedulingMaximumProfit(List<Job> jobList) {
		this.byFinishTime = new JobComparatorByFinishTime();
		this.jobList = jobList;
		counter = 0;
		
		// These ArrayList store the value that used to process
		this.profitList = new ArrayList<>();
		this.jobSequencedList = new ArrayList<>();
		
		// This ArrayList is to store the index to get the sequenced job
		this.jobSequencedIndex = new ArrayList<>();
		
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
			
			this.profitList.add(counter, profitList);
			this.jobSequencedList.add(counter, jobSequencedList);
			this.jobSequenced.add(counter, jobSequenced);
			this.maxProfit.add(counter, maxProfit);
			this.jobSequencedIndex.add(counter, jobSequencedIndex);
			counter++;
			
			/*System.out.println(counter + " maxProfit: " + maxProfit);
			System.out.println(counter + " jobList: " + jobList);
			System.out.println(counter + " jobSequencedList: " + jobSequencedList);
			System.out.println(counter + " jobSequenced: " + jobSequenced);
			System.out.println(counter + " profitList: " + profitList);*/
		}
		this.counter = counter;
	}
	
	public List<List<Job>> getJobSequenced() {
		return this.jobSequenced;
	}
	
	public List<Integer> getMaxProfit() {
		return this.maxProfit;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
	/*public List<List<LinkedList<Job>>> getJobSequencedList() {
		return this.jobSequencedList;
	}
	
	public List<List<Integer>> getProfitList() {
		return this.profitList;
	}*/
	
}
