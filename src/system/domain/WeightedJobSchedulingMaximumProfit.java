package system.domain;

import java.util.Arrays;

public class WeightedJobSchedulingMaximumProfit {
	public int maximum(Job[] jobs) {
		int T[] = new int[jobs.length];
		JobComparatorByFinishTime comparator = new JobComparatorByFinishTime();
		Arrays.sort(jobs, comparator);
		
		T[0] = jobs[0].getProfit();
		for (int i = 0; i < jobs.length; i++) {
			T[i] = Math.max(jobs[i].getProfit(), T[i - 1]);
			for (int j = i - 1; j >= 0; j--) {
				if (jobs[j].getFinishTime() <= jobs[i].getStartTime()) {
					T[i] = Math.max(T[i], jobs[i].getProfit() + T[j]);
					break;
				}
			}
		}
		
		int maxVal = Integer.MIN_VALUE;
		for (int val : T) {
			if (maxVal < val) {
				maxVal = val;
			}
		}
		
		return maxVal;
	}
}
