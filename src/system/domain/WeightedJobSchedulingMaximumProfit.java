package system.domain;

import java.util.Arrays;
import java.util.Comparator;

public class WeightedJobSchedulingMaximumProfit {
	private Comparator comparator;
	
	public WeightedJobSchedulingMaximumProfit() {
		comparator = new JobComparatorByFinishTime();
	}
	
	public int maximum(Job[] jobs) {
		int T[] = new int[jobs.length];
		Arrays.sort(jobs, comparator);
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
		for (int val : T) {
			if (maxVal < val) {
				maxVal = val;
			}
		}
		
		return maxVal;
	}
}
