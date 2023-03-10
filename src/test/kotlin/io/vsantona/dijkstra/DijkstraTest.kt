package io.vsantona.dijkstra;

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test;

class DijkstraTest {

    @Test
    fun `given a graph with only a node, when apply dijkstra, then return 0`() {

        val actual = Dijkstra(Graph(listOf(Arc(A, A, 2)), listOf(A)))
                .shortestPaths(A)

        assertThat(actual.getShortestDistanceFrom(A)).isEqualTo(Distance(0))
    }

    @Test
    fun `given full linked graph, when apply dijkstra, then return shortest distances`() {

        val actual = Dijkstra(
            Graph(
                listOf(
                    Arc(A, B, 6),
                    Arc(A, D, 1),
                    Arc(D, B, 2),
                    Arc(D, E, 1),
                    Arc(E, B, 2),
                    Arc(B, C, 5),
                    Arc(E, C, 5),
                ),
                listOf(A, B, C, D, E)
            )
        ).shortestPaths(A)

        assertThat(actual.getShortestDistanceFrom(A)).isEqualTo(Distance(0))
        assertThat(actual.getShortestDistanceFrom(B)).isEqualTo(Distance(3))
        assertThat(actual.getShortestDistanceFrom(C)).isEqualTo(Distance(7))
        assertThat(actual.getShortestDistanceFrom(D)).isEqualTo(Distance(1))
        assertThat(actual.getShortestDistanceFrom(E)).isEqualTo(Distance(2))
    }

    @Test
    fun `given another configuration full linked graph, when apply dijkstra, then return shortest distances`() {

        val graph = Graph(
            listOf(
                Arc(A, B, 6),
                Arc(A, D, 1),
                Arc(D, B, 2),
                Arc(D, E, 1),
                Arc(E, B, 1),
                Arc(B, E, 2),
                Arc(B, C, 3),
                Arc(E, C, 5),
            ), listOf(A, D, E, B, C))

        val actual = Dijkstra(graph).shortestPaths(A)

        assertThat(actual.getShortestDistanceFrom(A)).isEqualTo(Distance(0))
        assertThat(actual.getShortestDistanceFrom(B)).isEqualTo(Distance(3))
        assertThat(actual.getShortestDistanceFrom(C)).isEqualTo(Distance(6))
        assertThat(actual.getShortestDistanceFrom(D)).isEqualTo(Distance(1))
        assertThat(actual.getShortestDistanceFrom(E)).isEqualTo(Distance(2))
    }

    @Test
    fun `given another configuration full linked graph, when apply dijkstra with a different order, then return shortest distances`() {

        val graph = Graph(
            listOf(
                Arc(A, B, 6),
                Arc(A, D, 1),
                Arc(D, B, 2),
                Arc(D, E, 1),
                Arc(E, B, 1),
                Arc(B, E, 2),
                Arc(B, C, 3),
                Arc(E, C, 5),
            ), listOf(A, B, C, D, E))

        val actual = Dijkstra(graph).shortestPaths(A)

        assertThat(actual.getShortestDistanceFrom(A)).isEqualTo(Distance(0))
        assertThat(actual.getShortestDistanceFrom(B)).isEqualTo(Distance(3))
        assertThat(actual.getShortestDistanceFrom(C)).isEqualTo(Distance(6))
        assertThat(actual.getShortestDistanceFrom(D)).isEqualTo(Distance(1))
        assertThat(actual.getShortestDistanceFrom(E)).isEqualTo(Distance(2))
    }

    @Test
    fun `given a node not linked to graph, when apply dijkstra, then return infinite distance`() {

        val graph = Graph(
            listOf(
                Arc(A, B, 6),
                Arc(A, D, 1),
                Arc(D, B, 2),
                Arc(D, E, 1),
                Arc(E, B, 2),
                Arc(B, C, 5),
                Arc(E, C, 5),
                Arc(F, F, 0),
            ), listOf(A, B, C, D, E, F))

        val actual = Dijkstra(graph).shortestPaths(A)

        assertThat(actual.getShortestDistanceFrom(F)).isEqualTo(Distance(2147483647))
    }

    companion object {
        private val A = Node("A")
        private val B = Node("B")
        private val D = Node("D")
        private val E = Node("E")
        private val C = Node("C")
        private val F = Node("F")
    }

}

