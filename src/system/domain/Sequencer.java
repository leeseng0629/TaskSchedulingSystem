package system.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Sequencer {
	private Comparator<Job> byFinishTime;
	
	// Raw Input
	private List<Job> jobList;
	
	// To count the number of worker we need
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
			// A list of profit, that can be produced by several of job sequences.
			List<Integer> profitList = new ArrayList<>();
			// A list of sequences of job, the sequence can produce maximum profit will be selected.
			List<LinkedList<Job>> jobSequencedToBeSelected = new ArrayList<>();
			
			int jobSequencedIndex = 0;
			
			int maxProfit = Integer.MIN_VALUE;
			LinkedList<Job> jobSequenced = new LinkedList<>();
			
			// Sorting
			Collections.sort(jobList, byFinishTime);
			
			// Initialize profitList and jobSequencedToBeSelected
			profitList.add(jobList.get(0).getProfit());
			jobSequencedToBeSelected.add(new LinkedList<>());
			jobSequencedToBeSelected.get(0).add(jobList.get(0));
			
			for (int i = 1; i < jobList.size(); i++) {
				profitList.add(jobList.get(i).getProfit());
				jobSequencedToBeSelected.add(new LinkedList<>());
				jobSequencedToBeSelected.get(i).add(jobList.get(i));
				
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
							LinkedList<Job> previousList = jobSequencedToBeSelected.get(j);
							jobSequencedToBeSelected.get(i).clear();
							jobSequencedToBeSelected.get(i).add(jobList.get(i));
							jobSequencedToBeSelected.get(i).addAll(0, previousList);
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
			
			jobSequenced = jobSequencedToBeSelected.get(jobSequencedIndex);
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
