package org.nonari.aiseo.application.dijkstra

import java.util.Stack

class Dijkstra(private val graph: Graph) {
    fun distance(srcNode: Int, dstNode: Int): Int {
        val dist = Array(graph.maxNodes, {-1})
        val prev = Array(graph.maxNodes, {-1})
        val Q = graph.nodes().toMutableSet()
        dist[srcNode] = 0
        while(Q.size > 0) {
            val u = Dijkstra.min(dist, Q)
            if (u == dstNode) {
                break
            }
            Q.remove(u)
            graph.nodeNeighbours(u)?.forEach({ edge ->
                val alt = dist[u] + edge.len
                if (dist[edge.dst] == -1 || alt < dist[edge.dst]) {
                    dist[edge.dst] = alt
                    prev[edge.dst] = u
                }
            }) ?: throw Exception("No path found")
        }
        val S = Stack<Int>()
        var u = dstNode
        while (prev[u] > -1) {
            S.push(u)
            u = prev[u]
        }
        S.push(u)
        var s = S.pop()
        var pathDist = 0
        for(j in 0 .. (S.size - 1)) {
            val d = S.pop()
            pathDist += graph.edgeWeight(s, d) ?: 0
            s = d
        }
        return pathDist
    }

    private companion object {
        private fun min(dist: Array<Int>, Q: Set<Int>): Int {
            var pos = -1
            var last = Int.MAX_VALUE
            dist.forEachIndexed {idx, node ->
                if(node < last && node > -1 && Q.contains(idx)) {
                        pos = idx
                        last = node
                    }
            }
            return pos
        }
    }
}