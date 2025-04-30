# Bellman-Ford Algorithm

## Definition

The **Bellman-Ford algorithm** computes the shortest paths from a single source vertex to all other vertices in a weighted graph.  
**Unlike Dijkstra's algorithm**, Bellman-Ford allows for **negative edge weights**.

**Key idea**:
- Relax all edges repeatedly to gradually decrease estimated path lengths,
- Detect negative weight cycles if distances can still be reduced after |V| - 1 iterations.

---

# Problem Setting

Given:
- A directed graph `G = (V, E)`,
- A weight function `w: E → R` (weights can be negative),
- A source vertex `s ∈ V`.

Goal:
- Compute shortest-path distances `d(s, v)` for every `v ∈ V`,
- Detect if a **negative-weight cycle** is reachable from the source.

---

# Pseudocode for Bellman-Ford

```
Bellman-Ford(Graph G, Vertex s):
    // Distance to all vertices is set to `∞`, except the source which is `0`
    for each vertex v in G.V:
        v.dist ← ∞
        v.prev ← null
        s.dist ← 0

  // Relax all edges |V| - 1 times
  for i from 1 to |V| - 1:
      for each edge (u, v) in G.E:
        // If current path to v can be improved do so
          if u.dist + w(u, v) < v.dist:
              v.dist ← u.dist + w(u, v)
              v.prev ← u

  // Check for negative-weight cycles
  for each edge (u, v) in G.E:
      if u.dist + w(u, v) < v.dist:
          report "Graph contains a negative-weight cycle"
          return False

  return True
```


---

# Explanation

- **Initialization**: Distance to all vertices is set to `∞`, except the source which is `0`.
- **Relaxation**:
  - For `|V| - 1` iterations, relax each edge:  
    If the current path to `v` can be improved via `u`, update `v.dist`.
- **Cycle Detection**:
  - After relaxation, check all edges.
  - If any edge can still be relaxed, a **negative-weight cycle** exists.

---

# Key Components

- **Distance array**: Stores shortest known distances,
- **Previous array**: Stores predecessors for path reconstruction,
- **Relaxation**: Core operation improving path estimates,
- **Negative cycle detection**: Unique feature compared to Dijkstra's algorithm.

---

# Time Complexity

- Relaxing all edges takes `O(|E|)` time,
- We perform `|V| - 1` passes for relaxation.

Thus, total **time complexity**: O(|V| * |E|)


---

# Space Complexity

- Distance array: `O(|V|)`,
- Predecessor array: `O(|V|)`,
- Graph storage (adjacency list or edge list): `O(|V| + |E|)`.

Thus, **space complexity**: O(|V| + |E|)


---

# Summary Table

| Feature            | Bellman-Ford Algorithm                                      |
|--------------------|-------------------------------------------------------------|
| Problem solved     | Single-source shortest paths (handles negative weights)     |
| Strategy           | Dynamic programming via repeated relaxation                 |
| Detects cycles     | Yes (detects negative-weight cycles)                         |
| Time complexity    | O(|V| * |E|)                                                 |
| Space complexity   | O(|V| + |E|)                                                 |

---

# Properties of Bellman-Ford

- Handles graphs with **negative edge weights**,
- Detects **negative-weight cycles** reachable from the source,
- Slower than Dijkstra's algorithm for graphs without negative weights,
- Used in:
  - **Routing protocols** (e.g., RIP - Routing Information Protocol),
  - **Currency arbitrage detection**,
  - **Graph analysis** where negative cycles matter.

---

# Mathematical Justification (Correctness)

- After `i` iterations, shortest paths with at most `i` edges are correctly computed.
- Since any simple path can have at most `|V| - 1` edges (no cycles), repeating relaxation `|V| - 1` times guarantees shortest paths.
- A further relaxation implies a **negative-weight cycle** (path length can be decreased indefinitely).

---

