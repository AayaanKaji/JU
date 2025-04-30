# Kruskal's Algorithm

## Definition

**Kruskal's algorithm** finds a **Minimum Spanning Tree (MST)** of a connected, undirected, weighted graph.

**Key idea**:
- Always pick the smallest weight edge that does not form a cycle,
- Use **Union-Find (Disjoint Set Union, DSU)** to efficiently detect cycles.

---

# Problem Setting

Given:
- An undirected graph `G = (V, E)` with edge weights `w: E → R^+`.

Goal:
- Find a subset `T ⊆ E` such that:
  - `T` forms a tree (i.e., no cycles, connected),
  - `|T| = |V| - 1`,
  - The total weight `∑_{e∈T} w(e)` is minimized.

---

# Pseudocode for Kruskal's Algorithm

```
Kruskal(Graph G):
  MST ← ∅
  for each vertex v in G.V:
    make-set(v)

  sort all edges E by increasing weight

  for each edge (u, v) in sorted E:
    if find(u) ≠ find(v):
        MST ← MST ∪ {(u, v)}
        union(u, v)

  return MST
```

---

# Explanation

- **Initialization**:
  - Each vertex is its own set (via Union-Find initialization).
- **Edge Sorting**:
  - Sort edges in non-decreasing order of weight.
- **Processing edges**:
  - For each edge `(u, v)`:
    - If `u` and `v` are in different sets (i.e., no cycle),
      - Add `(u, v)` to MST,
      - Merge the sets containing `u` and `v`.

---

# Key Components

- **Edge list**: All edges must be available and sortable,
- **Union-Find data structure**:
  - `find(u)`: Find representative of `u`'s set,
  - `union(u, v)`: Merge sets,
- **Sorting**:
  - Initial sorting of all edges based on weight.

---

# Time Complexity

Major steps:
- Sorting edges: `O(|E| log |E|)`,
- Union-Find operations (with path compression and rank):
  - Each `find` and `union`: amortized `O(α(|V|))`,
  - Total across all edges: `O(|E| α(|V|))`.

Thus, total **time complexity**:

O(|E| log |E|)


(because sorting dominates, as `α(|V|)` is extremely slow-growing).

---

# Space Complexity

- Parent and rank arrays for Union-Find: `O(|V|)`,
- Storing edges and MST: `O(|E|)`.

Thus, total **space complexity**:

O(|V| + |E|)


---

# Summary Table

| Feature          | Kruskal's Algorithm                     |
| ---------------- | --------------------------------------- |
| Problem solved   | Minimum Spanning Tree (MST)             |
| Strategy         | Greedy (choose smallest available edge) |
| Time complexity  | O(E*logE)                               |
| Space complexity | O(V + E)                                |
| Cycle detection  | Union-Find (DSU)                        |

---

# Properties

- **Greedy method**: Locally optimal choices lead to global optimum (MST),
- **Works for**:
  - **Sparse graphs**: Efficient when `|E|` is much less than `|V|^2`,
  - **Disconnected graphs**: Can find a **Minimum Spanning Forest** (MST for each connected component),
- **Depends on efficient DSU**:
  - Without Union-Find, cycle checking would be too slow.

---

# Mathematical Justification (Correctness)

- **Greedy Choice Property**: Choosing the minimum weight edge that connects two different components is safe,
- **Cut Property**: For any cut in the graph, the minimum weight edge crossing the cut belongs to some MST,
- **Proof**:
  - Adding the minimum edge maintains acyclicity and connectivity,
  - Repeated application builds a valid MST.

---