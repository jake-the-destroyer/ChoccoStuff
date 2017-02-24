import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class TeamData {
	
	private int numWorkers; //number of workers
	private int chargeCap; //chargeCap
	private int teamSize; //required team size
	private int[] charges; //the charges for the workers
	private int[] leaders; //array of {0,1}, where 1 indicates worker is qualified to be a leader
	private int[] testers; //array of {0,1}, where 1 indicates worker is qualified to be a tester
	private int[][] badpairs; //a symmetric numWorkers x numWorkers matrix of {0,1}, where
	                              // a 1 indicates the corresponding workers are incompatible
	
    public TeamData(String filename) throws IOException {
    	/*
    	 * Assumes data is in file in the following format:
    	 *    number-of-workers  charge-cap team-size
    	 * followed by the charges on a new line
    	 * followed by the leader indicators on a new line
    	 * followed by the tester indicators on a new line
    	 * followed by the numWorker rows of the badpairs matrix
    	 */
	    Scanner scanner = new Scanner(new File(filename));
	    numWorkers = scanner.nextInt();
	    chargeCap = scanner.nextInt();
	    teamSize = scanner.nextInt();
	    
	    charges = new int[numWorkers];
	    for (int worker = 0; worker < numWorkers; worker++) {
            charges[worker] = scanner.nextInt();	    	
	    }
	    
	    leaders = new int[numWorkers];
	    for (int worker = 0; worker < numWorkers; worker++) {
            leaders[worker] = scanner.nextInt();	    	
	    }
	    
	    testers = new int[numWorkers];
	    for (int worker = 0; worker < numWorkers; worker++) {
            testers[worker] = scanner.nextInt();	    	
	    }
	    
	    badpairs = new int[numWorkers][numWorkers];	    
	    for (int worker1=0;worker1<numWorkers;worker1++){
	    	for (int worker2 = 0; worker2 < numWorkers; worker2++) {
	            badpairs[worker1][worker2] = scanner.nextInt();
	    	}
	    }
	    scanner.close();
    }
    
    public int getNumWorkers() {
    	return numWorkers;
    }
    
    public int getChargeCap() {
    	return chargeCap;
    }
    
    public int getTeamSize() {
    	return teamSize;
    }
    
    public int[] getCharges() {
    	return charges;
    }
    
    public int[] getLeaders() {
    	return leaders;
    }
    
    public int[] getTesters() {
    	return testers;
    }
    
    public int[][] getBadPairs() {
    	return badpairs;
    }
}
