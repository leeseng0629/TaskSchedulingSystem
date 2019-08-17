package system.ui;

import system.domain.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TaskSchedulingSystemApp {
	
	private static ArrayList<Job> jobList;
	private static Sequencer sequencer;
	private static ArrayList<Worker> workerList;
	private static int totalProfitAfterScheduled;
	private static int totalWastedResources;

	public static void main(String[] args) {
		jobList = new ArrayList<>();
		sequencer = new Sequencer();
		workerList = new ArrayList<>();
		totalProfitAfterScheduled = 0;
		totalWastedResources = 0;
		
		String csvFile = "jobs.txt", 
				line = "", 
				csvSplitBy = ", ";
        BufferedReader br = null;
		
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
			
			sequencer.setJobList(jobList);
			sequencer.calculateMaxProfitAndSequencingJob();
			
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
		
		
	  	/**
	  	 * Worker will be initialize here
	  	 * The number of workers is depends on how many
	  	 * jobSequnced we have, then assign to them
	  	 */
		for (int i = 0; i < sequencer.getCounter(); i++) {
			LinkedList<Job> jobSequenced = sequencer.getSpecifiedJobSequenced(i);
			int maxProfit = sequencer.getSpecifiedMaxProfit(i);
			Worker worker = new Worker();
			worker.assginJob(jobSequenced, maxProfit);
			workerList.add(worker);
		}
		
		/**
		 * The result we want is total profit after we scheduled, 
		 * the total resources we wasted and maybe the worker's time schedule.
		 */
		for (Worker worker : workerList) {
			totalProfitAfterScheduled += worker.getProfitWorkerMade();
			totalWastedResources += worker.getIdleTime();
			System.out.println(worker.getScheduledTime());
		}
		
		System.out.println(totalProfitAfterScheduled);
		System.out.println(totalWastedResources);
		
	}

}
