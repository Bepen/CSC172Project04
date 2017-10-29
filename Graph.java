
/*
Bepen Neupane
Lab: Tuesday & Thursday 4:50-6:05
Project 4
bneupane@u.rochester.edu
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {
	public static Map<String, Node> nodeIDMap;
	public static Map<String, Edge> edgeIDMap;
	public static Map<Integer, Node> nodeCountMap;
	public static Map<Integer, Edge> edgeCountMap;
	public static ArrayList<Edge> listKruskalEdge;
	public static ArrayList<String> visitedDjiNodes;
	public static int nodeCount = 0;
	public static int edgeCount = 0;
	public static String textFile;
	public static boolean mst;
	public static boolean dji;
	public static String startIntersection;
	public static String endIntersection;
	public static boolean showGraph;
	public static ArrayList<String> nodePrint;
	public static double maximumLatitude;
	public static double minimumLatitude;
	public static double maximumLongitude;
	public static double minimumLongitude;

	public Graph() {
		nodeIDMap = new HashMap<String, Node>();
		edgeIDMap = new HashMap<String, Edge>();
		nodeCountMap = new HashMap<Integer, Node>();
		edgeCountMap = new HashMap<Integer, Edge>();
		listKruskalEdge = new ArrayList<Edge>();
		nodePrint = new ArrayList<String>();
		visitedDjiNodes = new ArrayList<String>();
		maximumLatitude = Double.MAX_VALUE;
		minimumLatitude = Double.MIN_VALUE;
		maximumLongitude = Double.MAX_VALUE;
		minimumLongitude = Double.MIN_VALUE;
	}

	public Graph createFromFile(String textFile) { //generates graph
		Graph graph = new Graph();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(textFile));
			while (reader.ready()) {
				String input = reader.readLine();
				String[] tempArray = input.split("\\s+");
				if (tempArray[0].equals("i")) {
					double latitude = Double.parseDouble(tempArray[2]);
					double longitude = Double.parseDouble(tempArray[3]);
					Node tempNode = new Node(nodeCount, tempArray[1], latitude, longitude);
					nodeIDMap.put(tempArray[1], tempNode);
					nodeCountMap.put(nodeCount, tempNode);
					nodeCount++;
					if (latitude > maximumLatitude) {
						maximumLatitude = latitude;
					}
					if (latitude < minimumLatitude) {
						minimumLatitude = latitude;
					}
					if (longitude > maximumLongitude) {
						maximumLongitude = longitude;
					}
					if (longitude < minimumLongitude) {
						minimumLongitude = longitude;
					}
				} else if (tempArray[0].equals("r")) {
					Node node1 = nodeIDMap.get(tempArray[2]);
					Node node2 = nodeIDMap.get(tempArray[3]);
					Edge tempEdge = new Edge(tempArray[1], node1, node2);
					edgeIDMap.put(tempArray[1], tempEdge);
					edgeCountMap.put(edgeCount, tempEdge);
					listKruskalEdge.add(tempEdge);
					nodeIDMap.get(tempEdge.v.name).addToAdjacentList(new Adjacent(tempEdge.w, tempEdge.weight));
					nodeIDMap.get(tempEdge.w.name).addToAdjacentList(new Adjacent(tempEdge.v, tempEdge.weight));
					edgeCount++;
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return graph;
	}

	public static ArrayList<Edge> kruskalsList() { //generates kruskal's mst
		Collections.sort(listKruskalEdge);
		int edges = 0;
		int count = 0;
		ArrayList<Node> kruskalsVisitedNodes = new ArrayList<Node>();
		ArrayList<Edge> finalListKruskal = new ArrayList<Edge>();
		finalListKruskal.add(listKruskalEdge.get(count));
		kruskalsVisitedNodes.add(listKruskalEdge.get(count).v);
		kruskalsVisitedNodes.add(listKruskalEdge.get(count).w);
		while (edges < nodeCount - 1) {
			if (kruskalsVisitedNodes.contains(listKruskalEdge.get(count).v)
					&& !kruskalsVisitedNodes.contains(listKruskalEdge.get(count).w)
					|| !kruskalsVisitedNodes.contains(listKruskalEdge.get(count).v)
							&& kruskalsVisitedNodes.contains(listKruskalEdge.get(count).w)
					|| !kruskalsVisitedNodes.contains(listKruskalEdge.get(count).v)
							&& !kruskalsVisitedNodes.contains(listKruskalEdge.get(count).w)) {
				finalListKruskal.add(listKruskalEdge.get(count));
				kruskalsVisitedNodes.add(listKruskalEdge.get(count).v);
				kruskalsVisitedNodes.add(listKruskalEdge.get(count).w);
			}
			edges++;
			count++;
		}
		return finalListKruskal;
	}

	public void printKruskals() { //prints kruskal's mst
		kruskalsList();
		ArrayList<Edge> list = new ArrayList<Edge>();
		for (int i = 0; i < kruskalsList().size(); i++) {
			if (!kruskalsList().isEmpty()) {
				list.add(kruskalsList().get(i));
			}
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).v.name + " via " + list.get(i).name + " to " + list.get(i).w.name);
		}
	}

	public static Node getNodeFromId(String name) { //gets node from id name
		return nodeIDMap.get(name);
	}

	public static void dji(Node v, Node w) { //djikstra's algorithm
		Node temp;
		v.distance = 0;
		v.known = true;
		visitedDjiNodes.add(v.name);
		double weight;
		Node smallestNode = v;
		while (w.known != true) {
			smallestNode.known = true;
			LinkedList<Adjacent> adjacentNodesToV = smallestNode.adjacentList;
			for (int i = 0; i < adjacentNodesToV.size(); i++) {
				temp = nodeIDMap.get(smallestNode.name).adjacentList.get(i).parent;
				weight = smallestNode.adjacentList.get(i).weight;
				visitedDjiNodes.add(temp.name);
				if (!nodeIDMap.get(adjacentNodesToV.get(i).parent.name).known) {
					if (smallestNode.distance + weight < nodeIDMap.get(temp.name).distance) {
						nodeIDMap.get(temp.name).distance = smallestNode.distance + weight;
						nodeIDMap.get(temp.name).parent = smallestNode;
					}
				}
			}
			smallestNode = nodeIDMap.get(smallNode(visitedDjiNodes));
		}

	}

	public static String smallNode(ArrayList<String> visitedDjiNodes) { //finds node that leads to smallest weight, for djikstra's algorithm
		String smallestNode = "";
		double totalWeight = Double.POSITIVE_INFINITY;
		ArrayList<String> removeNodes = new ArrayList<String>();
		for (int i = 0; i < visitedDjiNodes.size(); i++) {
			if (!nodeIDMap.get(visitedDjiNodes.get(i)).known
					&& nodeIDMap.get(visitedDjiNodes.get(i)).distance < totalWeight) {
				smallestNode = nodeIDMap.get(visitedDjiNodes.get(i)).name;
				totalWeight = nodeIDMap.get(visitedDjiNodes.get(i)).distance;
			} else if (nodeIDMap.get(visitedDjiNodes.get(i)).known) {
				removeNodes.add(nodeIDMap.get(visitedDjiNodes.get(i)).name);
			}
			if (removeNodes.size() > 0) {
				for (int k = 0; k < removeNodes.size(); k++) {
					visitedDjiNodes.remove(removeNodes.get(k));
				}
			}
		}
		return smallestNode;
	}

	public ArrayList<Edge> roadsForDji(Node v, Node w) { // prints the path for dji's algorithm
		ArrayList<Edge> list = new ArrayList<Edge>();
		dji(v, w);
		Node z = w;
		nodePrint.add(w.name);
		while (z != v) {
			Edge e = new Edge(z, z.parent);
			list.add(e);
			nodePrint.add(z.parent.name);
			z = z.parent;
		}
		int locs = nodePrint.size() - 1;
		Collections.reverse(nodePrint);
		for (int i = 0; i < nodePrint.size(); i++) {
			System.out.print(nodePrint.get(i));
			if (locs > 0) {
				System.out.print(" to ");
			}
			locs--;
		}
		System.out.println("\nThe total distance between " + v.name + " and " + w.name + " is: " + w.distance + " km");
		return list;
	}
}
