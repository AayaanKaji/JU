# Dijkstra's Algorithm

## Definition

Dijkstra’s algorithm finds the **shortest paths** from a given **source vertex** to all other vertices in a weighted graph with **non-negative edge weights**.

**Key idea**:
- Always expand the vertex with the **smallest known distance** from the source
- Update the shortest path estimates for its neighbors
- Repeat until all vertices have been processed

---

# Problem Setting

Given:
- A directed or undirected graph G = (V, E),
- A weight function w: E -> G assigning **non-negative** weights to edges,
- A source vertex s in V.

Goal:
- Compute the shortest-path distances d(s, v) for every v in V,
where d(s, v) is the minimum sum of weights along any path from s to v.

---

# Pseudocode for Dijkstra (Priority Queue Form)

Assuming a **min-priority queue** (e.g., binary heap):

```
Dijkstra(Graph G, Vertex s):
    // Current estimate is ∞ dist to reach other vertices
    for each vertex v in G.V:
        v.dist ← ∞
        v.prev ← null

    // Start is at 0 distance
    s.dist ← 0

    create a min-priority queue Q
    Q.insert((s.dist, s))

    while Q is not empty:
        (d, u) ← Q.extract-min()

        for each neighbor v of u:
            // Update route incase it's faster
            if v.dist > u.dist + w(u, v):
                v.dist ← u.dist + w(u, v)
                v.prev ← u
                // Insert this vertex to explore through this vertex
                Q.insert((v.dist, v))
```

---

# Explanation

- **Initialization**: Set tentative distance to all vertices as ∞ except the source s, which is 0.
- **Main Loop**:
  - Extract the vertex u with the minimum tentative distance,
  - Relax all edges (u, v) — if a shorter path to v is found through u, update v's distance.
- **Termination**: When the priority queue is empty, shortest paths to all reachable vertices are finalized.

---

# Key Components

- **Priority queue (min-heap)**: To always pick the vertex with the smallest tentative distance,
- **Distance array**: Stores the current shortest distance estimates,
- **Previous array**: Optionally stores predecessors for path reconstruction.

---

# Time Complexity

Assuming:
- |V| = number of vertices,
- |E| = number of edges.

Depending on the priority queue used:

| Priority Queue | Time per Operation                                  | Overall Time Complexity |
| -------------- | --------------------------------------------------- | ----------------------- |
| Array          | O(1) insert, O(V) extract-min                       | O(V^2)                  |
| Binary Heap    | O(logV) insert and extract-min                      | O((V + E) * logV)       |
| Fibonacci Heap | O(1) insert, O(logV) extract-min, O(1) decrease-key | O(E + V * logV)         |

Thus:
- For **sparse graphs** (where |E| = O(|V|)), binary heap or Fibonacci heap is preferable,
- For **dense graphs** (where |E| = O(|V|^2)), simpler array-based implementation suffices.

---

# Space Complexity

- Distance array: O(|V|),
- Priority queue: O(|V|),
- Graph storage (adjacency list): O(|V| + |E|).

Thus, **space complexity**:

\[
O(|V| + |E|)
\]

---

# Summary Table

| Feature          | Dijkstra's Algorithm                                |
| ---------------- | --------------------------------------------------- |
| Problem solved   | Single-source shortest paths (non-negative weights) |
| Strategy         | Greedy                                              |
| Data structure   | Min-priority queue                                  |
| Time complexity  | O((V + E) logV) with binary heap                   |
| Space complexity | O(V + E)                                            |

---

# Properties of Dijkstra's Algorithm

- **Correctness** relies critically on **non-negative edge weights**; otherwise, it may fail,
- Guarantees that when a vertex is extracted from the priority queue, its shortest distance is finalized,
- **Greedy choice** is locally optimal and leads to global optimality (due to non-negative weights),
- Used in:
  - **Network routing** (e.g., OSPF protocol),
  - **Pathfinding** in AI (games, robots),
  - **Logistics and mapping** (e.g., GPS shortest path calculations).

---

# Mathematical Justification (Correctness)

Let u be a vertex extracted from the queue.  
At extraction time, u.dist = d(s,u), where d(s,u) is the true shortest distance.

Proof (Sketch):
- Assume otherwise: there exists a shorter path reaching u later.
- Due to non-negative weights, any partial path can only increase the distance,
- Hence, no shorter path to u can exist after u is finalized (contradiction).

---