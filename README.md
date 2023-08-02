# Graph Algorithms Repository (Java)
Activity in Java of the Algorithms and Data Structures III discipline - CSI 105 feat. <a href="https://github.com/caiogc">Caio Guilherme</a>. This repository contains various graph algorithms implemented in Java. These algorithms are commonly used in computer science and have applications in network analysis, path finding, and optimization problems. Below, you will find a brief description of each algorithm implemented in this repository.

### Some of the functions in this repository:
- Breadth-first search
- Depth-first search
- SubGraph
- Complement graph
- Dijkstra
- Bellman Ford
- Floyd Warshall
- Kruskal
- Prim

## Algorithms
### Breadth-first search
The Breadth-first search (BFS) algorithm is used to traverse or search a graph in a breadthward motion. It starts at a given source vertex and explores all its neighbors before moving on to the next level of vertices. BFS uses a queue data structure to keep track of the vertices to be visited. This algorithm is useful for finding the shortest path between two vertices in an unweighted graph and for exploring the connectivity of a graph.

### Depth-first search
The Depth-first search (DFS) algorithm is used to traverse or search a graph in a depthward motion. It starts at a given source vertex and explores as far as possible along each branch before backtracking. DFS uses a stack data structure (or recursion) to keep track of the vertices to be visited. This algorithm is useful for finding connected components, detecting cycles, and solving puzzles with graph-like structures.

### SubGraph
The SubGraph algorithm is used to extract a subgraph from a given graph. Given a set of vertices, it constructs a new graph that contains only the specified vertices and the edges connecting them. This algorithm is useful for analyzing specific parts of a larger graph or reducing the complexity of a graph problem by focusing on a subset of vertices.

### Complement graph
The Complement graph algorithm constructs a new graph where the edges of the original graph are reversed. If an edge exists between vertices A and B in the original graph, it will be removed, and a new edge will be added between A and B in the complement graph. This algorithm is useful for studying structural properties of a graph or finding alternative paths in a network.

### Dijkstra
Dijkstra's algorithm is a widely used algorithm for finding the shortest paths between nodes in a graph with non-negative edge weights. It maintains a priority queue to keep track of the distances from the source node to all other nodes and iteratively selects the node with the smallest distance. Dijkstra's algorithm guarantees finding the shortest path from the source node to all other nodes in the graph.

### Bellman Ford
The Bellman-Ford algorithm is an algorithm used to find the shortest paths from a single source vertex to all other vertices in a graph. Unlike Dijkstra's algorithm, the Bellman-Ford algorithm can handle graphs with negative edge weights, but it is slower. It iteratively relaxes the edges in the graph, updating the distances until no more updates are possible, or a negative cycle is detected.

### Floyd Warshall
The Floyd-Warshall algorithm is an all-pairs shortest path algorithm used to find the shortest paths between all pairs of vertices in a weighted graph. It works by considering all possible intermediate vertices and gradually updating the distances between pairs of vertices. The Floyd-Warshall algorithm is efficient for small to medium-sized graphs but becomes impractical for large graphs due to its time and space complexity.

### Kruskal
The Kruskal algorithm is a greedy algorithm used to find a minimum spanning tree (MST) in a connected, weighted graph. It starts with an empty graph and repeatedly adds the shortest edge that does not form a cycle, until all vertices are included in the MST. The Kruskal algorithm is efficient for finding an MST in a dense graph or when the edges are already sorted by weight.

### Prim
The Prim algorithm is another greedy algorithm used to find a minimum spanning tree (MST) in a connected, weighted graph. It starts with a single vertex and gradually grows the MST by adding the shortest edge that connects a vertex in the MST to a vertex outside the MST. The Prim algorithm is efficient for finding an MST in a sparse graph or when the graph is represented using an adjacency list.

## Usage
Each algorithm is implemented in Java and comes with detailed instructions on how to use it. You can find the Java source files in their respective directories along with example code snippets. You can compile and run the algorithms using any Java development environment or from the command line.

## Acknowledgements
If you have any questions, suggestions, or improvements, please feel free to open an issue or contact the repository maintainers.

Happy graph algorithm exploration in Java!
