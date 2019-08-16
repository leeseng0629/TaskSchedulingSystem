package system.domain;

public class Job {
	private int jobId;
	private int startTime;
	private int finishTime;
	private int duration;
	private int profit;
	
	public Job() {
	}
	
	public Job(int jobId, int startTime, int finishTime, int profit) {
		if (startTime < 0 || finishTime < 0) {
			throw new TimeConstraintException("Start Time and Finish Time must be positive number");
		}
		else if (startTime > finishTime) {
			throw new TimeConstraintException("Start Time is larger than Finish Time");
		}
		else if (profit < 0) {
			throw new ProfitNegativeException("Profit must be positive number");
		}
		else {
			this.jobId = jobId;
			this.startTime = startTime;
			this.finishTime = finishTime;
			this.setDuration();
			this.profit = profit;			
		}
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
		if (startTime < 0) {
			throw new TimeConstraintException("Start Time and Finish Time must be positive number");
		}
		else if (startTime > this.finishTime) {
			throw new TimeConstraintException("Start Time is larger than Finish Time");
		}
		else {
			this.startTime = startTime;
			return true;			
		}
	}
	
	public int getFinishTime() {
		return this.finishTime;
	}
	
	public boolean setFinishTime(int finishTime) {
		if (finishTime < 0) {
			throw new TimeConstraintException("Start Time and Finish Time must be positive number");
		}
		if (this.startTime > finishTime) {
			throw new TimeConstraintException("Start Time is larger than Finish Time");
		}
		else {
			this.finishTime = finishTime;
			return true;			
		}
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public boolean setDuration() {
		this.duration = this.finishTime - this.startTime + 1;
		return true;
	}
	
	public int getProfit() {
		return this.profit;
	}
	
	public boolean setProfit (int profit) {
		if (profit < 0) {
			throw new ProfitNegativeException("Profit must be positive number");
		}
		else {
			this.profit = profit;
			return true;
		}
	}
	
	public String toString() {
		return Integer.toString(this.jobId) + " " +
				Integer.toString(this.startTime) + " " +
				Integer.toString(this.finishTime) + " " +
				Integer.toString(this.profit);
	}
	
}
