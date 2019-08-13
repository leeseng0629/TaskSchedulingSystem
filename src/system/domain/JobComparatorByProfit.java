package system.domain;

import java.util.Comparator;

public class JobComparatorByProfit implements Comparator<Job> {

	@Override
	public int compare(Job job1, Job job2) {
		// TODO Auto-generated method stub
		return (job1.getProfit() - job2.getProfit());
	}

}
