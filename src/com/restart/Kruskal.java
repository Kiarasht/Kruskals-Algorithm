package com.restart;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.Vector;

class Kruskal {
    Vector<HashSet<String>> Groups = new Vector<>();
    TreeSet<Edge> Edges = new TreeSet<>();

    public TreeSet<Edge> getEdges() {                                       // Used when we have the spanning tree
        return Edges;                                                       // and we need all the edges to print
    }

    HashSet<String> find(String vertex) {                                   // Find and see if a city is already in tree
        for (HashSet<String> Group : Groups) {                              // This is fine since there won't be loops
            if (Group.contains(vertex)) {                                   // in the spanning tree
                return Group;
            }
        }
        return null;                                                        // It wasn't found in the tree
    }

    public void union(Edge edge) {                                          // Insert an edge to the Kruskal tree
        String from = edge.getFrom();                                       // Get source
        String to = edge.getTo();                                           // Get destination

        HashSet<String> From = find(from);                                  // Find city from
        HashSet<String> To = find(to);                                      // Find city to

        if (From == null) {                                                 // From wasn't there using find()
            Edges.add(edge);                                                // Add that edge
            if (To == null) {                                               // To wasn't there using find()
                HashSet<String> New = new HashSet<>();                      // New HashSet
                New.add(from);                                              // That contains the newly two added cities
                New.add(to);
                Groups.add(New);                                            // Add the new group to vector
            } else {
                To.add(from);                                               // From was found using find()
            }                                                               // Only add the from city, to already in
        } else {
            if (To == null) {                                               // To wasn't there using find()
                From.add(to);                                               // Add only from
                Edges.add(edge);
            } else if (From != To) {                                        // As long as they don't equal to each other
                From.addAll(To);                                            // Copy everything from To and put in From HashSet
                Groups.remove(To);                                          // No longer need the To HashSet
                Edges.add(edge);
            }
        }
    }
}
