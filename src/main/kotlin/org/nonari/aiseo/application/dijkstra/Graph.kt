package org.nonari.aiseo.application.dijkstra

import java.util.HashMap

class Graph(val maxNodes: Int) {
    private val adjacencyMap = HashMap<Int, MutableMap<Int, Int>>()

    fun addEdge(edge: Edge) {
        addEdge(edge.src, edge.dst, edge.len)
    }

    fun addEdge(srcNode: Int, dstNode: Int, weight: Int) {
        if (weight < 0) {
            throw IllegalArgumentException("Weight must be greater than 0")
        }
        if (srcNode < 1 || srcNode > maxNodes || dstNode < 1 || dstNode > maxNodes) {
            throw IllegalArgumentException("Node Id numbers must be between 1 and 256")
        }

        addOneWayEdge(srcNode, dstNode, weight)
        addOneWayEdge(dstNode, srcNode, weight)
    }

    private fun addOneWayEdge(src: Int, dst: Int, weight: Int) {
        if (adjacencyMap.containsKey(src)) {
            adjacencyMap[src]?.put(dst, weight)
        } else {
            val map = HashMap<Int, Int>()
            map.put(dst, weight)
            adjacencyMap.put(src, map)
        }
    }

    fun edgeWeight(srcNode: Int, dstNode: Int): Int? {
        return adjacencyMap[srcNode]?.get(dstNode)
    }

    fun nodeNeighbours(node: Int): Set<Edge>? {
        return adjacencyMap[node]?.entries?.mapTo(mutableSetOf<Edge>(), {
            Edge(node, it.key, it.value)
        })
    }

    fun nodes(): Set<Int> {
        return adjacencyMap.keys.toSet()
    }

    fun hasNode(node: Int): Boolean {
        return adjacencyMap[node] != null
    }

}

