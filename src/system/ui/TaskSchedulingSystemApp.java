package system.ui;

import system.domain.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TaskSchedulingSystemApp {
	
	private static final Random intRandom = new Random();

	public static void main(String[] args) {
		String csvFile = "jobs.txt";
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ", ";
        
		ArrayList<Job> jobList = new ArrayList<>();
		
		WeightedJobSchedulingMaximumProfit mp = new WeightedJobSchedulingMaximumProfit();
		
		try {
			br = new BufferedReader(new FileReader(csvFile));
			
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] job = line.split(csvSplitBy);
				int job_id =  Integer.parseInt(job[0]);
				int job_start = Integer.parseInt(job[1]);
				int job_end = Integer.parseInt(job[2]);
				int job_profit = Integer.parseInt(job[3]);
				jobList.add(new Job(job_id, job_start, job_end, job_profit));
			}
			
			mp.setJobList(jobList);
			mp.calculateMaxProfitAndSequencingJob();
			
		}
		catch (TimeConstraintException err) {
			err.printStackTrace();
		}
		catch (ProfitNegativeException err) {
			err.printStackTrace();
		}
		catch (FileNotFoundException err) {
			err.printStackTrace();
		} 
		catch (IOException err) {
			err.printStackTrace();
		} 
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Worker worker = new Worker();
		LinkedList<Job> jobSequenced = mp.getSpecifiedJobSequenced(0);
		int maxProfit = mp.getSpecifiedMaxProfit(0);
		worker.assginJob(jobSequenced, maxProfit);
		
		System.out.println("This Worker has idle for " + worker.getIdleTime() + " unit of time. ");
		System.out.println("This Worker has made " + worker.getProfitWorkerMade() + " unit of profit. ");
		
	}

}
