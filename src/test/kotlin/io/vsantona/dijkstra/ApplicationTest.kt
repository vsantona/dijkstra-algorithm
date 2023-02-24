package io.vsantona.dijkstra;

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test;

class ApplicationTest {

    @Test
    fun testMatchedNodes() {
        var graph = Graph(listOf(Arrow(Node("A"), Node("A"), 2)))

        var actual: Distance = Dijkstra(graph).shortestPath(Node("A"), Node("A"))

        assertThat(actual).isEqualTo(Distance(2))
    }

    @Test
    fun `given two nodes, when apply dijkstra, then return shortest path`() {

        var graph = Graph(
            listOf(
                Arrow(Node("A"), Node("A"), 0),
                Arrow(Node("A"), Node("B"), 6)
            )
        )

        var actual: Distance = Dijkstra(graph).shortestPath(Node("A"), Node("B"))

        assertThat(actual).isEqualTo(Distance(6))
    }

    class Dijkstra(private val graph: Graph) {
        fun shortestPath(startVertex: Node, toVertex: Node): Distance {
            return graph.arrows
                .find { it.from == startVertex && it.to == toVertex }
                ?.let { Distance(it.weight) }
                ?: Distance(0)
        }

    }

}