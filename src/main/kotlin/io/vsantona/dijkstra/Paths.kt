package io.vsantona.dijkstra

class Paths(private val shortestDistances: Map<Node, Distance>) {
    fun getShortestDistanceFrom(node: Node): Distance {
        return shortestDistances[node]!!
    }
}