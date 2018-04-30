package org.nonari.aiseo.application.dijkstra

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class GraphTest {
    private val maxNodes = 256
    private lateinit var graph: Graph
    private val edge2to1 = Edge(2, 1, 1)
    private val edge2to3 = Edge(2, 3, 2)
    private val edge2to4 = Edge(2, 4, 2)
    private val edge2to5 = Edge(2, 5, 5)
    private val edge4to5 = Edge(4, 5, 2)

    @BeforeEach
    fun setUp() {
        graph = Graph(maxNodes)
        graph.addEdge(edge2to1)
        graph.addEdge(edge2to3)
        graph.addEdge(edge2to4)
        graph.addEdge(edge2to5)
        graph.addEdge(edge4to5)
    }

    @Test
    fun failsWhenAddingNodeNumbersOutOfBounds() {
        Assertions.assertThrows(
                IllegalArgumentException::class.java,
                {graph.addEdge(maxNodes + 1, 1, 3)}
        )
        Assertions.assertThrows(
                IllegalArgumentException::class.java,
                {graph.addEdge(maxNodes, 0, 3)}
        )
        Assertions.assertThrows(
                IllegalArgumentException::class.java,
                {graph.addEdge(1, maxNodes + 1, 3)}
        )
        Assertions.assertThrows(
                IllegalArgumentException::class.java,
                {graph.addEdge(0, maxNodes, 3)}
        )
        Assertions.assertThrows(
                IllegalArgumentException::class.java,
                {graph.addEdge(1, maxNodes, -1)}
        )
    }

    @Test
    fun containsNodesOneToFive() {
        Assertions.assertTrue(graph.hasNode(1))
        Assertions.assertFalse(graph.hasNode(6))
        Assertions.assertEquals(setOf(5, 2, 1, 4, 3), graph.nodes())
    }

    @Test
    fun edgeWeightBetweenAdjacentNodes() {
        Assertions.assertEquals(5, graph.edgeWeight(2, 5))
    }

    @Test
    fun nodeNeighboursFor2() {
        val expectedEdges = setOf<Edge>(edge2to1, edge2to3, edge2to4, edge2to5)
        Assertions.assertEquals(expectedEdges, graph.nodeNeighbours(2))
    }

    @Test
    fun nodeNeighboursFor1() {
        val expectedEdges = setOf<Edge>(edge2to1)
        Assertions.assertEquals(expectedEdges, graph.nodeNeighbours(1))
    }


}


