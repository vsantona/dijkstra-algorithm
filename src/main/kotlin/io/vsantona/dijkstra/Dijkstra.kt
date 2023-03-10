package io.vsantona.dijkstra

class Dijkstra(private val graph: Graph) {

    private val mutableMap: MutableMap<Node, Distance> = mutableMapOf()

    init {
        graph.nodes.map { it to Distance(Int.MAX_VALUE) }.toMap(mutableMap)
    }

    fun shortestPaths(startVertex: Node): Paths {

        mutableMap[startVertex] = Distance(0)
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