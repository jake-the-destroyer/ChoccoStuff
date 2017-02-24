import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class MeetingProblemReader {
	
	private int numPeople; //number of people -- n
	private int numMeetings; //number of meetings -- m
	private int maxDays; //maximum days available -- t
	private int maxProjectors; //maximum number of projectors on any day -- k
	private int[] durations; //the duration of each of the m meetings (each > 0)
	private int[][] meetingPeople; //a m x n matrix of {0,1}, where
	                        // meetingPeople[p][q] == 1 <==> person q is in meeting p
	private int[] earliestStart; //a 1 x n array of {0, 1, ...,  maxDays-1}, where
	                        //earliestStart[q] == d <==> person q cannot start before day d
	
    public MeetingProblemReader(String filename) throws IOException {
    	/*
    	 * Assumes data is in file in the following format:
    	 *    number-of-people  number-of-meetings max-days max-projectors
    	 * followed by the durations on a new line
    	 * followed by the numMeetings rows of the meetingPeople matrix
    	 * followed by the numPeople rows of the unavailable matrix
    	 */
    	
	    Scanner scanner = new Scanner(new File(filename));
	    numPeople = scanner.nextInt();
	    numMeetings = scanner.nextInt();
	    maxDays = scanner.nextInt();
	    maxProjectors = scanner.nextInt();
	    
	    durations = new int[numMeetings];
	    for (int meeting = 0; meeting < numMeetings; meeting++) {
            durations[meeting] = scanner.nextInt();	    	
	    }
	    	    
	    meetingPeople = new int[numMeetings][numPeople];	    
	    for (int meeting=0;meeting<numMeetings;meeting++){
	    	for (int person = 0; person < numPeople; person++) {
	            meetingPeople[meeting][person] = scanner.nextInt();
	    	}
	    }
	    
		earliestStart = new int[numPeople];	    
		for (int person=0;person<numPeople;person++){
	        earliestStart[person] = scanner.nextInt();
		}
	    scanner.close();
    }
    
    public int getNumPeople() {
    	return numPeople;
    }
    
    public int getNumMeetings() {
    	return numMeetings;
    }
    
    public int getmaxDays() {
    	return maxDays;
    }
    
    public int getMaxProjectors() {
    	return maxProjectors;
    }
    
    public int[] getDurations() {
    	return durations;
    }
    
    public int[][] getMeetingPeople() {
    	return meetingPeople;
    }

    public int[] getEarliestStart() {
    	return earliestStart;
    }
}