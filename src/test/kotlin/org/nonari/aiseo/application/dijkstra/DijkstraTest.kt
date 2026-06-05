package org.nonari.aiseo.application.dijkstra

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class DijkstraTest {
    private val graph = Graph(256)
    private val edge2to1 = Edge(2, 1, 1)
    private val edge2to3 = Edge(2, 3, 2)
    private val edge2to4 = Edge(2, 4, 2)
    private val edge2to5 = Edge(2, 5, 5)
    private val edge4to5 = Edge(4, 5, 2)
    private val dijkstra = Dijkstra(graph)

    init {
        graph.addEdge(edge2to1)
        graph.addEdge(edge2to3)
        graph.addEdge(edge2to4)
        graph.addEdge(edge2to5)
        graph.addEdge(edge4to5)
    }

    @Test
    fun distanceBetween() {
        Assertions.assertEquals(5, dijkstra.distance(1, 5))
    }
}