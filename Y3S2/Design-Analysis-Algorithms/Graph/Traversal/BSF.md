# Breadth-First Search (BFS)

## Definition

- Breadth-First Search (BFS) is a fundamental graph traversal algorithm.
- It explores a graph **level by level**, visiting all neighbors of a node before moving deeper.

**Key idea**:
- Visit all neighbors of the current node,
- Then visit neighbors of neighbors,
- Continue until all vertices reachable from the start are visited.

---

# Pseudocode for BFS (Using Queue)

```
BFS(Graph G, Vertex s):
    for each vertex v in G.V:
        v.visited ← false

    create an empty queue Q
    v.visited ← true
    Q.enqueue(s)

    while Q is not empty:
        v ← Q.dequeue()
        process(v)

        for each neighbor u in G.Adj[v]:
            if u.visited = false:
                u.visited ← true
                Q.enqueue(u)
```

---

# Explanation

- Start from a **given source vertex** \( s \),
- Visit all unvisited neighbors and enqueue them,
- Dequeue a vertex and repeat the process for its neighbors,
- Traversal happens in layers: first all vertices at distance 1 from \( s \), then distance 2, and so on.

---

# Key Components

- **Graph G**: with vertex set \( G.V \) and adjacency list \( G.Adj \),
- **Visited marker**: Boolean array to prevent revisiting vertices,
- **Queue**: Implements **FIFO** (First-In-First-Out) order.

---

# Time Complexity

- Each vertex is enqueued and dequeued exactly once: \( O(|V|) \),
- Each edge is examined once (in undirected graphs) or once per direction (in directed graphs): \( O(|E|) \).

Thus, total **time complexity**:

\[
O(|V| + |E|)
\]

---

# Space Complexity

- Visited array requires \( O(|V|) \),
- Queue can store up to \( O(|V|) \) vertices in the worst case.

Thus, **space complexity**:

\[
O(|V|)
\]

---

# Summary Table

| Feature          | BFS                                              |
| ---------------- | ------------------------------------------------ |
| Data structure   | Queue (FIFO)                                     |
| Time complexity  | O(\|V\| + \|E\|)                                 |
| Space complexity | O(\|V\|)                                         |
| Traversal order  | Closest (level-wise) nodes first (breadth-first) |

---

# Properties of BFS

- Computes the **shortest path** (minimum number of edges) from a source \( s \) to every other reachable vertex in an **unweighted graph**,
- Traverses vertices in **increasing order of distance** from the start node,
- Can be used to:
  - Find **connected components**,
  - Check **bipartiteness** of a graph,
  - Find the **shortest path** in unweighted graphs (e.g., grid-based games),
  - Detect **cycles** in an undirected graph.

---