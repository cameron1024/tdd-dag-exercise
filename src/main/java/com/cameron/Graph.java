package com.cameron;

import java.util.*;

public class Graph<T> {

//    addNode, addEdge

//    constructor, addChild


    private final Map<T, Set<T>> nodes = new HashMap<>();


    public void addNodeIfNotPresent(T node) {
        nodes.putIfAbsent(node, new HashSet<>());
    }

    public void addEdge(T from, T to) throws CycleException {
        addNodeIfNotPresent(from);
        addNodeIfNotPresent(to);

        Set<T> visited = new HashSet<>();

        checkCycle(to, from, visited);


        Set<T> currentEdges = nodes.get(from);
        currentEdges.add(to);
        // TODO: 10/03/2020 mutable?

    }

    private void checkCycle(T currentNode, T search, Set<T> visited) throws CycleException {
        if (currentNode.equals(search)) throw new CycleException();
        for (T node : nodes.get(currentNode)) {
            if (node.equals(search)) throw new CycleException();
            if (!visited.contains(node)) {
                checkCycle(node, search, visited);
            }
            visited.add(node);
        }
    }





    public void delete(T node) {
        nodes.remove(node);
    }

    public Map<T, Set<T>> getNodes() {
        return nodes;
    }




    static class CycleException extends Exception {}
}
