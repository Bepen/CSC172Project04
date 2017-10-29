/*
Bepen Neupane
Lab: Tuesday & Thursday 4:50-6:05
Project 4
bneupane@u.rochester.edu
*/

public class Test {
	public static void main(String[] args) {
		int argsLength = args.length;
		String textFile = "";
		boolean showGraph = false;
		boolean mst = false;
		boolean dji = false;
		String startIntersection = null;
		String endIntersection = null;
		
		if (args[0].equals("ur.txt")) { //first argument is text file name
			textFile = args[0];
		} else if (args[0].equals("monroe.txt")) {
			textFile = args[0];
		} else if (args[0].equals("nys.txt")) {
			textFile = args[0];
		} else {
			System.out.println("Wrong name of text file.");
		}

		if (argsLength == 2) {
			if (args[1].equals("-meridianmap")) { //second argument is -meridianmap
				mst = true;
			}
		}

		if (argsLength == 3) {
			showGraph = true; //second argument is -show
			if (args[2].equals("-meridianmap")) { //third argument is -meridianmap
				mst = true;
			}
		}

		if (argsLength == 4) {
			if (args[1].equals("-directions")) { //second argument is -directions
				dji = true;
			}
			startIntersection = args[2]; //third argument is starting intersection
			endIntersection = args[3]; //fourth argument is end intersection
		}

		if (argsLength == 5) {
			showGraph = true; //second argument is -show
			if (args[2].equals("-directions")) { //third argument is -directions
				dji = true;
			}
			startIntersection = args[3]; //fourth argument is start intersection
			endIntersection = args[4]; //fifth argument is end intersection
		}

		System.out.println("textFile: " + textFile);
		System.out.println("mst: " + mst);
		System.out.println("dji: " + dji);
		System.out.println("startIntersection: " + startIntersection);
		System.out.println("endIntersection: " + endIntersection);
		System.out.println("showGraph: " + showGraph);
		

		Graph graph = new Graph();
		graph.createFromFile(textFile);
		if (mst == true) { //if user wants -meridianmap
			graph.printKruskals();
		} else if (dji == true) { //if user wants -directions
			graph.roadsForDji(Graph.getNodeFromId(startIntersection), Graph.getNodeFromId(endIntersection));
		}
		

	}
}
