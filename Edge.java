/*
Bepen Neupane
Lab: Tuesday & Thursday 4:50-6:05
Project 4
bneupane@u.rochester.edu
*/

public class Edge implements Comparable<Edge> { // edge class
	public String name;
	public Node v, w;
	public double weight;

	public Edge(String id, Node v, Node w) { // edge constructor when id is
												// known
		this.v = v;
		this.w = w;
		name = id;
		weight = distance(v.latitude, v.longitude, w.latitude, w.longitude);
	}

	public Edge(Node v, Node w) { // edge constructor when id isn't known
		this.v = v;
		this.w = w;
		name = null;
		weight = distance(v.latitude, v.longitude, w.latitude, w.longitude);
	}

	// https://rosettacode.org/wiki/Haversine_formula
	public static double distance(double lat1, double lon1, double lat2, double lon2) { //converting latitude and longitude to
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return 6372.8 * c;
	}

	@Override
	public int compareTo(Edge e) { //edge weights being compared
		if (this.weight == e.weight)
			return 0;
		else if (this.weight < e.weight)
			return -1;
		else if (this.weight > e.weight)
			return 1;
		return 0;
	}

	public Edge() {
		this.weight = 100000;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getV() {
		return v;
	}

	public void setV(Node v) {
		this.v = v;
	}

	public Node getW() {
		return w;
	}

	public void setW(Node w) {
		this.w = w;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String toString() {
		return name;
	}
}
