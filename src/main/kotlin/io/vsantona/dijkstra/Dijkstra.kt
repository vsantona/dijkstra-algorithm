package io.vsantona.dijkstra

class Dijkstra(private val graph: Graph) {

    private val mutableMap: MutableMap<Node, Distance> = mutableMapOf()

    init {
        graph.arcs.map { it.from }.forEach { mutableMap[it] = Distance(Int.MAX_VALUE) }
        graph.arcs.map { it.to }.forEach { mutableMap[it] = Distance(Int.MAX_VALUE) }
    }

    fun shortestPaths(startVertex: Node): Paths {

        graph.arcs.filter { it.from == startVertex }.forEach { mutableMap[it.from] = Distance(0) }
        graph.arcs.filter { it.to == startVertex }.forEach { mutableMap[it.to] = Distance(0) }

        val unvisited = mutableMap.toMutableMap()

        while (unvisited.isNotEmpty()) {

            val currentNode = unvisited.minBy { it.value.value }.key

            graph.arcs.filter { it.from == currentNode }.forEach {
                val distanceFromVertex = mutableMap[currentNode]?.value?.plus(it.weight);

                if (mutableMap[it.to]?.value!! > distanceFromVertex!!) mutableMap[it.to] =
                    Distance(distanceFromVertex)
            }
            unvisited.remove(currentNode)

        }

        return Paths(mutableMap)
    }
}