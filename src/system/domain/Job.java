package system.domain;

public class Job {
	private int jobId;
	private int startTime;
	private int finishTime;
	private int duration;
	private int requiredHR;
	private int profit;
	
	public Job() {
	}
	
	public Job(int jobId, int startTime, int finishTime, int profit) {
		this.jobId = jobId;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.setDuration();
		this.profit = profit;
	}
	
	public Job(int jobId, int startTime, int finishTime, int requiredHR, int profit) {
		this.jobId = jobId;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.setDuration();
		this.requiredHR = requiredHR;
		this.profit = profit;
	}

	public int getJobId() {
		return this.jobId;
	}
	
	public boolean setJobId(int jobId) {
		this.jobId = jobId;
		return true;
	}
	
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
	
	public int getDuration() {
		return this.duration;
	}
	
	public boolean setDuration() {
		this.duration = this.finishTime - this.startTime + 1;
		return true;
	}
	
	public int getRequiredHR() {
		return this.requiredHR;
	}
	
	public boolean setRequiredHR(int requiredHR) {
		this.requiredHR = requiredHR;
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
