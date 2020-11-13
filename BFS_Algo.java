
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BFS_Algo
{
	static int j = 0;
	public static void main(String[] args) {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("                 Missionaries and Cannibals Problem");
		System.out.println("---------------------------------------------------------------------------");
		String optionStr = null;

		State initialState = new State (3, 3, Position.LEFT, 0, 0);
		switch(1) {
		case 1:
			long startTime = System.currentTimeMillis();
			executeBFS(initialState);
			long stopTime = System.currentTimeMillis();
		    long elapsedTime = stopTime - startTime;
		    System.out.println("---------------------------------------------------------------------------");
		    System.out.println("Total time elapsed: "+elapsedTime+"ms");
		    System.out.println("---------------------------------------------------------------------------");
			break;
		}
	}
	public State exec(State initialState) {
		if (initialState.isGoal()) {
			return initialState;
		}
		Queue<State> frontier = new LinkedList<State>();	// FIFO queue
		Set<State> explored = new HashSet<State>();
		frontier.add(initialState);
		while (true) {
			if (frontier.isEmpty()) {
				return null;	// failure
			}
			State state = frontier.poll();
			explored.add(state);
			List<State> successors = state.generateSuccessors();
			for (State child : successors) {
				if (!explored.contains(child) || !frontier.contains(child)) {
					if (child.isGoal()) {
						return child;
					}
					frontier.add(child);
				}
			}
		}
	}

	private static void executeBFS(State initialState) {
		BFS_Algo search = new BFS_Algo();
		State solution = search.exec(initialState);
		printSolution(solution);
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