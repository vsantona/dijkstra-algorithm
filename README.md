# dijkstra-algorithm

Set distance of start vertex from itself to 0
Set distance of all other vertices from start = ∞ (infinity)
All vertices are unvisited

while there are unvisited vertices
  Set current vertex with the smallest known distance from the start vertex
  For the current vertex, examine its unvisited neighbours
    For the current neighboor, calculate distance of each neighbour from start vertex
    if the calculated distance is less than the known distance, update the shortest distance
  Current vertex is visited
