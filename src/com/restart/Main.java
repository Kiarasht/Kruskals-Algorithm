package com.restart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        TreeSet<Edge> edges = new TreeSet<>();                              // Tree of edges

        try {
            FileReader file = new FileReader("input.txt");                  // Read the text file input.txt taken from
            BufferedReader buff = new BufferedReader(file);                 // http://svcs.cs.pdx.edu/cs350-summer2015/city-pairs.txt
            String line = buff.readLine();
            while (line != null) {                                          // Read till end of file
                StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
                String from = stringTokenizer.nextToken();                  // Break the line into three tokens
                String to = stringTokenizer.nextToken();                    // each separated by a space
                int cost = Integer.valueOf(stringTokenizer.nextToken());    // cost has to be an int value
                edges.add(new Edge(from, to, cost));                        // Create the edge
                line = buff.readLine();                                     // Loop more if needed
            }
            buff.close();
        } catch (IOException e) {                                           // I/O exception in unexpected situations
            System.out.println("Had trouble reading the file:");
            System.out.println(e.getMessage());
        }

        Kruskal kruskal = new Kruskal();                                    // Start creating the kruskal tree

        edges.forEach(kruskal::union);                                      // For each edge, try combining them

        int Totalcost = 0;                                                  // Total cost of tree
        int loop = 1;
        for (Edge edge : kruskal.getEdges()) {                              // Go through the kruskal tree
            System.out.println(loop++ + ") " + edge.toString());            // Print the cities and cost
            Totalcost += edge.getCost();
        }                                                                   // Total distance of kruskal
        System.out.println("\nTotal cost of minimal spanning tree: " + Totalcost);
    }
}

class Edge implements Comparable<Edge> {
    String from, to;                                                        // Strings for source and destination cities
    int cost;                                                               // Cost of traveling between the cities

    public Edge(String from, String to, int cost) {
        this.from = from;                                                   // Copy the values in the newly created edge
        this.to = to;
        this.cost = cost;
    }

    public String getFrom() {                               // Get functions
        return from;                                  // Get the source city
    }

    public String getTo() {
        return to;                                      // Get the destination city
    }

    public int getCost() {
        return cost;                                     // Get the cost of traveling
    }

    public String toString() {                              // Printing out final values
        return "From " + from + " to " + to + " with cost of " + cost + ".";
    }

    public int compareTo(Edge edge) {                                       // Sorts the edge list by implementing comparable
        if (this.cost < edge.cost)
            return -1;
        else
            return 1;
    }
}

class Kruskal {
    Vector<HashSet<String>> Groups = new Vector<>();
    TreeSet<Edge> Edges = new TreeSet<>();

    public TreeSet<Edge> getEdges() {                       // Used when we have the spanning tree
        return Edges;                                       // and we need all the edges to print
    }

    HashSet<String> find(String vertex) {                   // Find and see if a city is already in tree
        for (HashSet<String> Group : Groups) {              // This is fine since there won't be loops
            if (Group.contains(vertex)) {                   // in the spanning tree
                return Group;
            }
        }
        return null;                                        // It wasn't found in the tree
    }

    public void union(Edge edge) {                          // Insert an edge to the Kruskal tree
        String from = edge.getFrom();                       // Get source
        String to = edge.getTo();                           // Get destination

        HashSet<String> From = find(from);                  // Find city from
        HashSet<String> To = find(to);                      // Find city to

        if (From == null) {                                 // From wasn't there using find()
            Edges.add(edge);                                // Add that edge
            if (To == null) {                               // To wasn't there using find()
                HashSet<String> New = new HashSet<>();      // New HashSet
                New.add(from);                              // That contains the newly two added cities
                New.add(to);
                Groups.add(New);                            // Add the new group to vector
            } else {
                To.add(from);                               // From was found using find()
            }                                               // Only add the from city, to already in
        } else {
            if (To == null) {                               // To wasn't there using find()
                From.add(to);                               // Add only from
                Edges.add(edge);
            } else if (From != To) {                        // As long as they don't equal to each other
                From.addAll(To);                            // Copy everything from To and put in From HashSet
                Groups.remove(To);                          // No longer need the To HashSet
                Edges.add(edge);
            }
        }
    }
}