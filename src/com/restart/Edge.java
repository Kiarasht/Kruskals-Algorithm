package com.restart;

class Edge implements Comparable<Edge> {
	String from, to;                                                 // Strings for source and destination cities
	int cost;                                                        // Cost of traveling between the cities

	public Edge(String from, String to, int cost) {
		this.from = from;                                            // Copy the values in the newly created edge
		this.to = to;
		this.cost = cost;
	}

	public String getFrom() {                                        // Get functions
		return from;                                                 // Get the source city
	}

	public String getTo() {
		return to;                                                   // Get the destination city
	}

	public int getCost() {
		return cost;                                                 // Get the cost of traveling
	}

	public String toString() {                                       // Printing out final values
		return "From " + from + " to " + to + " with cost of " + cost + ".";
	}

	public int compareTo(Edge edge) {                                // Sorts the edge list by implementing comparable
		if (this.cost < edge.cost)
			return -1;
		else
			return 1;
	}
}
