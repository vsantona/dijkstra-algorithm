package io.vsantona.dijkstra;

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test;

class ApplicationTest {

    @Test
    fun `given a graph with only a node, when apply dijkstra, then return 0`() {
        var graph = Graph(listOf(Arrow(Node("A"), Node("A"), 2)))

        var actual: Map<Node, Distance> = Dijkstra(graph).shortestDistances(Node("A"))

        assertThat(actual[Node("A")]).isEqualTo(Distance(0))
    }

    @Test
    fun `given full linked graph, when apply dijkstra, then return shortest distances`() {

        var graph = Graph(
            listOf(
                Arrow(Node("A"), Node("B"), 6),
                Arrow(Node("A"), Node("D"), 1),
                Arrow(Node("D"), Node("B"), 2),
                Arrow(Node("D"), Node("E"), 1),
                Arrow(Node("E"), Node("B"), 2),
                Arrow(Node("B"), Node("C"), 5),
                Arrow(Node("E"), Node("C"), 5),
            )
        )

        var actual: Map<Node, Distance> = Dijkstra(graph).shortestDistances(Node("A"))

        assertThat(actual.size).isEqualTo(5)
        assertThat(actual[Node("A")]).isEqualTo(Distance(0))
        assertThat(actual[Node("B")]).isEqualTo(Distance(3))
        assertThat(actual[Node("C")]).isEqualTo(Distance(7))
        assertThat(actual[Node("D")]).isEqualTo(Distance(1))
        assertThat(actual[Node("E")]).isEqualTo(Distance(2))
    }

    @Test
    fun `given another configuration full linked graph, when apply dijkstra, then return shortest distances`() {

        var graph = Graph(
            listOf(
                Arrow(Node("A"), Node("B"), 6),
                Arrow(Node("A"), Node("D"), 1),
                Arrow(Node("D"), Node("B"), 2),
                Arrow(Node("D"), Node("E"), 1),
                Arrow(Node("E"), Node("B"), 1),
                Arrow(Node("B"), Node("E"), 2),
                Arrow(Node("B"), Node("C"), 3),
                Arrow(Node("E"), Node("C"), 5),
            )
        )

        var actual: Map<Node, Distance> = Dijkstra(graph).shortestDistances(Node("A"))

        assertThat(actual.size).isEqualTo(5)
        assertThat(actual[Node("A")]).isEqualTo(Distance(0))
        assertThat(actual[Node("B")]).isEqualTo(Distance(3))
        assertThat(actual[Node("C")]).isEqualTo(Distance(6))
        assertThat(actual[Node("D")]).isEqualTo(Distance(1))
        assertThat(actual[Node("E")]).isEqualTo(Distance(2))
    }

    @Test
    fun `given a node not linked to graph, when apply dijkstra, then return infinite distance`() {

        var graph = Graph(
            listOf(
                Arrow(Node("A"), Node("B"), 6),
                Arrow(Node("A"), Node("D"), 1),
                Arrow(Node("D"), Node("B"), 2),
                Arrow(Node("D"), Node("E"), 1),
                Arrow(Node("E"), Node("B"), 2),
                Arrow(Node("B"), Node("C"), 5),
                Arrow(Node("E"), Node("C"), 5),
                Arrow(Node("F"), Node("F"), 0),
            )
        )

        var actual: Map<Node, Distance> = Dijkstra(graph).shortestDistances(Node("A"))

        assertThat(actual[Node("F")]).isEqualTo(Distance(2147483647))
    }

    class Dijkstra(private val graph: Graph) {

        fun shortestDistances(startVertex: Node): Map<Node, Distance> {

            val nodesWithShortestDistance = mutableMapOf<Node, Distance>()
            graph.arrows.map { it.from }.forEach { if (it == startVertex) nodesWithShortestDistance[it] = Distance(0) else nodesWithShortestDistance[it] = Distance(Int.MAX_VALUE) }
            graph.arrows.map { it.to }.forEach { if (it == startVertex) nodesWithShortestDistance[it] = Distance(0) else nodesWithShortestDistance[it] = Distance(Int.MAX_VALUE) }

            val unvisited = nodesWithShortestDistance.toMutableMap()

            while (unvisited.isNotEmpty()) {

                val currentNode = unvisited.minBy { it.value.value }.key

                graph.arrows.filter { it.from == currentNode }.forEach {
                    val distanceFromVertex = nodesWithShortestDistance[currentNode]?.value?.plus(it.weight);

                    if (nodesWithShortestDistance[it.to]?.value!! > distanceFromVertex!!) nodesWithShortestDistance[it.to] = Distance(distanceFromVertex)
                }
                unvisited.remove(currentNode)

            }
                return nodesWithShortestDistance
        }

    }

}