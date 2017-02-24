import java.io.IOException;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

public class Team113380396 {
  public static void main(String[] args)  throws IOException {

    // Create a Model
    Model model = new Model("Team Building");

    //Create a data object
    TeamData data = new TeamData("data//team0.txt");
    
    /* From the created data object, extrapolate all the relevant information required. 
     * Create integers for; total employees in the company, budget for the given project & size of the required team. 
     * Create int arrays for; hourly charge, leadership status & tester status for each employee, plus, a 2D array for pairs that don't work together.
    */
    int employees = data.getNumWorkers();
    int charge_cap = data.getChargeCap();
    int team_required = data.getTeamSize();

    int[] charges = data.getCharges(); 
    int[] leaders = data.getLeaders(); 
    int[] testers =   data.getTesters();  
    int[][] bad_pairs = data.getBadPairs();
 
    //Create an IntVar array in the domain {0,1} for each employee denoting whether that employee is included in the project.
    IntVar[] employees_on_project = new IntVar[employees];
    for (int working = 0; working<employees; working++) {
		employees_on_project[working] = model.intVar("Employee "+working + " working", 0,  1); //how many of each type
	}
    
    //Set the following constraints....
    
    //The scalar product of the working employees and the hourly cost of the employees must be within the budget.
    model.scalar(employees_on_project, charges, "<=", charge_cap).post();
    //The scalar product of the working employees and Leadership status must be over one (i.e there must be at least one leader)
    model.scalar(employees_on_project, leaders, ">", 0).post();
    //The scalar product of the working employees and Tester status must be over one (i.e there must be at least one Tester)
    model.scalar(employees_on_project, testers, ">", 0).post();
    //The total employees working on the project must meet the specified team size.
    model.sum(employees_on_project, "=", team_required).post();
    
    //Iterate through the 2D array of employees that don't work well together...
    for(int i = 0; i < employees; i++){
    	for(int j = 0; j < employees; j++){
    		if(bad_pairs[i][j] == 1){
    			//Perform an arithmetic NAND on each pair of employees in the working list that are seen to not work together.
    			model.arithm(employees_on_project[i], "+",employees_on_project[j], "<", 2).post();
    		}
    	}
    }
    
    // Solve the problem
	Solver solver = model.getSolver();
	int numsoloution = 1;
	while (solver.solve()) { 
		// Print the solution
		System.out.println("Solution "+numsoloution + ";");
		for (int employee = 0; employee < employees_on_project.length; employee++) {
			 System.out.println(employees_on_project[employee]);
		}
		System.out.println();
	 	numsoloution++;
	}  
  }
}
