package system.domain;

import java.util.ArrayList;
import java.util.List;

public class Worker {
	private List<Boolean> AvailableTime;
	private final static int MAX_SIZE = 10;
	
	public Worker() {
		AvailableTime = new ArrayList<>();
		System.out.println(AvailableTime);
	}
	
	public void assginJob(List<Job> jobSequenced) {
		for (int i = 0; i < MAX_SIZE; i++) {
			AvailableTime.add(null);
			System.out.println(AvailableTime);
		}
		System.out.println(AvailableTime);
		System.out.println(AvailableTime.size());
		/*
		if (AvailableTime.size() == MAX_SIZE) {
			// Throw Exception or do anything
		}
		else {
			for (int i = 0; i < MAX_SIZE; i++) {
				AvailableTime.add(null);
			}
			
			for (Job job : jobSequenced) {
				
			}
		}*/
	}
	
}
