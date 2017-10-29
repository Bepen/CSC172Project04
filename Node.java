/*
Bepen Neupane
Lab: Tuesday & Thursday 4:50-6:05
Project 4
bneupane@u.rochester.edu
*/

import java.util.LinkedList;

public class Node { //intersection class
	String name = null;
	double latitude = 0;
	double longitude = 0;
	String makeParent = null;
	boolean known = false;
	double distance = 0;
	Node parent = null;
	int key = 0;
	LinkedList<Adjacent> adjacentList = null;
	
	public Node(int key, String name, double latitude, double longitude) { //node constructor
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.key = key;
		known = false;
	    distance = Double.MAX_VALUE;
	    parent = null;
	    adjacentList = new LinkedList<Adjacent>();
	}
	
	
	public boolean sameNode(Node node) { //checks if a node is equal to another node
		if (this.name.equals(node.name)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addToAdjacentList(Adjacent e) { //adds node to adjList
		adjacentList.add(e);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getMakeParent() {
		return makeParent;
	}

	public void setMakeParent(String makeParent) {
		this.makeParent = makeParent;
	}

	public boolean isKnown() {
		return known;
	}

	public void setKnown(boolean known) {
		this.known = known;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
}
