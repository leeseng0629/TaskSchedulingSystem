package system.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Worker {
	private int workerId;
	private List<Boolean> ScheduledTime;
	private int idleTime;
	private int profitWorkerMade;
	private final static int MAX_SIZE = 10;
	
	public Worker(int workerId) {
		this.workerId = workerId;
		ScheduledTime = new ArrayList<>();
	}
	
	public void assginJob(LinkedList<Job> jobSequenced, int maxProfit) {
		for (int i = 0; i < MAX_SIZE; i++) {
			ScheduledTime.add(null);
		}
		
		for (Job job : jobSequenced) {
			int startTime, duration, assigningTime;
			startTime = job.getStartTime();
			duration = job.getDuration();
			assigningTime = startTime;
			
			while (!(duration == 0)) {
				ScheduledTime.set(assigningTime, true);
				--duration;
				++assigningTime;
			}
		}
		
		for (int i = 0; i < MAX_SIZE; i++) {
			if (ScheduledTime.get(i) == null) ScheduledTime.set(i, false);
		}
		
		this.calculateIdleTime();
		this.calculateProfitWorkerMade(maxProfit);
	}
	
	private void calculateIdleTime() {
		this.idleTime = 0;
		for (Boolean time : ScheduledTime) {
			if (time == false) idleTime++;
		}
	}
	
	private void calculateProfitWorkerMade(int maxProfit) {
		this.profitWorkerMade = maxProfit - this.idleTime;
	}
	
	public int getWorkerId() {
		return this.workerId;
	}
	
	public int getIdleTime() {
		return this.idleTime;
	}
	
	public int getProfitWorkerMade() {
		return this.profitWorkerMade;
	}
	
	public List<Boolean> getScheduledTime() {
		return this.ScheduledTime;
	}
	
	public String toString() {
		return "Worker " + this.getWorkerId() + " can make profit of " + this.getProfitWorkerMade() + " unit of profit. \n" + 
				"Worker " + this.getWorkerId() + " idle for " + this.getIdleTime() + " unit of time. \n" + 
				this.ScheduledTime + "\n";
	}
	
}
