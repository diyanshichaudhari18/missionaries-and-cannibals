
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DFS_Algo
{

	static int j;
	public static void main(String[] args) {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("                 Missionaries and Cannibals Problem");
		System.out.println("---------------------------------------------------------------------------");		//System.out.println("Choose the search method: ");

		State initialState = new State (3, 3, Position.LEFT, 0, 0);
		
		
			long startTime = System.currentTimeMillis();
			executeDLS(initialState);
			long stopTime = System.currentTimeMillis();
		    long elapsedTime = stopTime - startTime;
		    System.out.println("---------------------------------------------------------------------------");
		    System.out.println("Total time elapsed: "+elapsedTime+"ms");
		    System.out.println("---------------------------------------------------------------------------");
			
	}
	private static void executeDLS(State initialState) {
    DFS_Algo search = new DFS_Algo();
		State solution = search.exec(initialState);
		printSolution(solution);
	}


	public State exec(State initialState) {
		int limit = 20;
		return recursiveDLS(initialState, limit);
	}

	private State recursiveDLS(State state, int limit) {
		if (state.isGoal()) {
			return state;
		} else if (limit == 0) {
			return null;
		} else {
			List<State> successors = state.generateSuccessors();
			for (State child : successors) {
				State result = recursiveDLS(child, limit - 1);
				if (null != result) {
					return result;
				}
			}
			return null;
		}
	}
	
	
	private static void printSolution(State solution) {
		if (null == solution) {
			System.out.print("\nNo solution found.");
		} else {
			System.out.println("Solution (cannibalLeft,missionaryLeft,boat,cannibalRight,missionaryRight): ");
			System.out.println("---------------------------------------------------------------------------");
			List<State> path = new ArrayList<State>();
			State state = solution;
			while(null!=state) {
				path.add(state);
				state = state.getParentState();
			}

			int depth = path.size() - 1;
			for (int i = depth; i >= 0; i--) {
				state = path.get(i);
				if (state.isGoal()) {
					System.out.println(j++ +"\t:"+ state.toString());
				} else {
					System.out.println(j++ +"\t:"+ state.toString());
				}
			}
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("Depth: " + depth);
		}
	}
}
