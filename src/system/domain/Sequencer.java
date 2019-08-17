package system.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Sequencer {
	private Comparator<Job> byFinishTime;
	private List<Job> jobList;
	private int counter;
	
	// Results of profit and sequenced job is store in this ArrayList
	private List<Integer> maxProfits;
	private List<LinkedList<Job>> jobSequencedList;
	
	public Sequencer() {
		this.byFinishTime = new JobComparatorByFinishTime();
		this.counter = 0;
		
		// Results of profit and sequenced job is store in this ArrayList
		this.jobSequencedList = new ArrayList<>();
		this.maxProfits = new ArrayList<>();
	}
	
	public Sequencer(List<Job> jobList) {
		this.byFinishTime = new JobComparatorByFinishTime();
		this.jobList = jobList;
		this.counter = 0;
		
		// Results of profit and sequenced job is store in this ArrayList
		this.jobSequencedList = new ArrayList<>();
		this.maxProfits = new ArrayList<>();
	}
	
	public void calculateMaxProfitAndSequencingJob() {
		int counter = 0;
		while (jobList.size() != 0) {
			List<Integer> profitList = new ArrayList<>();
			List<LinkedList<Job>> jobSequencedList = new ArrayList<>();
			
			int jobSequencedIndex = 0;
			
			int maxProfit = Integer.MIN_VALUE;
			LinkedList<Job> jobSequenced = new LinkedList<>();
			
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
			
			this.maxProfits.add(counter, maxProfit);
			this.jobSequencedList.add(counter, jobSequenced);
			counter++;
		}
		this.counter = counter;
	}
	
	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}
	
	public List<LinkedList<Job>> getJobSequenced() {
		return this.jobSequencedList;
	}
	
	public LinkedList<Job> getSpecifiedJobSequenced(int index) {
		return this.jobSequencedList.get(index);
	}
	
	public List<Integer> getMaxProfit() {
		return this.maxProfits;
	}
	
	public int getSpecifiedMaxProfit(int index) {
		return this.maxProfits.get(index);
	}
	
	public int getCounter() {
		return this.counter;
	}
	
}
