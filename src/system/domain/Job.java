package system.domain;

public class Job {
	private int jobId;
	private int startTime;
	private int finishTime;
	// private int deadline;
	private int profit;
	
	public Job() {
	}
	
	public Job(int jobId, int startTime, int finishTime, int deadline, int profit) {
		this.jobId = jobId;
		this.startTime = startTime;
		this.finishTime = finishTime;
		// this.deadline = deadline;
		this.profit = profit;
	}

	public int getJobId() {
		return this.jobId;
	}
	
	public boolean setJobId(int jobId) {
		this.jobId = jobId;
		return true;
	}
	
	/*
	public int getDeadLine() {
		return this.deadline;
	}
	
	
	public boolean setDeadline(int deadline) {
		this.deadline = deadline;
		return true;
	}
	*/
	
	public int getStartTime() {
		return this.startTime;
	}
	public boolean setStartTime(int startTime) {
		this.startTime = startTime;
		return true;
	}
	
	public int getFinishTime() {
		return this.finishTime;
	}
	
	public boolean setFinishTime(int finishTime) {
		this.finishTime = finishTime;
		return true;
	}
	
	public int getProfit() {
		return this.profit;
	}
	
	public boolean setProfit (int profit) {
		this.profit = profit;
		return true;
	}
	
	public String toString() {
		return Integer.toString(this.jobId) + " " +
				Integer.toString(this.startTime) + " " +
				Integer.toString(this.finishTime) + " " +
				Integer.toString(this.profit);
	}
	
}
