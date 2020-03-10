package com.cameron;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

    private Graph<String> graph;

    @Before
    public void setUp() throws Exception {
        graph = new Graph<>();
    }

    @Test
    public void testAddNode() throws Graph.CycleException {
        graph.addNodeIfNotPresent("node");
        assertEquals(graph.getNodes().size(), 1);
        graph.addNodeIfNotPresent("node");
        assertEquals(graph.getNodes().size(), 1);
    }

    @Test
    public void testAddNodeChildren() {
        graph.addNodeIfNotPresent("node");
        assertEquals(graph.getNodes().get("node").size(), 0);
    }

    @Test
    public void testAddEdgeAddsNodes() throws Graph.CycleException {
        graph.addEdge("1", "2");
        assertEquals(graph.getNodes().size(), 2);
    }

    @Test
    public void testAddEdge() throws Graph.CycleException {
        graph.addEdge("1", "2");
        assertEquals(graph.getNodes().get("1").size(), 1);
        assertTrue(graph.getNodes().get("1").contains("2"));
    }



    @Test
    public void testDeleteNode() throws Graph.CycleException {

        graph.addNodeIfNotPresent("hello");
        graph.delete("hello");

        assertEquals(graph.getNodes().size(), 0);
    }






    @Test(expected = Graph.CycleException.class)
    public void testPreventTrivialCycle() throws Graph.CycleException {
        String node = "node";
        graph.addNodeIfNotPresent(node);
        graph.addEdge(node, node);
    }


    @Test(expected = Graph.CycleException.class)
    public void testPreventNonTrivialCycle() throws Graph.CycleException {

        graph.addEdge("1", "2");
        graph.addEdge("2", "1");

    }

    @Test(expected = Graph.CycleException.class)
    public void testLongCycle() throws Graph.CycleException {
        graph.addEdge("1", "2");
        graph.addEdge("2", "3");
        graph.addEdge("3", "1");
    }
}