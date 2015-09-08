package com.restart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TreeSet<Edge> edges = new TreeSet<>();                              // Tree of edges

        try {
            FileReader file = new FileReader("input.txt");                  // Read the text file input.txt
            BufferedReader buff = new BufferedReader(file);
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