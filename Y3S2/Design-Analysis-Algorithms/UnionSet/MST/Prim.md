# Prim's Algorithm

## Definition

**Prim’s algorithm** finds a **Minimum Spanning Tree (MST)** for a weighted, connected, undirected graph.

**Key idea**:
- Start with a single vertex,
- Grow the MST by **adding the minimum-weight edge** that connects a vertex inside the MST to a vertex outside.

---

# Problem Setting

Given:
- A connected, undirected graph `G = (V, E)` with positive edge weights `w: E → R+`.

Goal:
- Find a subset `T ⊆ E` such that:
  - `T` forms a tree covering all vertices,
  - Total weight `∑_{e∈T} w(e)` is minimized.

---

# Pseudocode for Prim's Algorithm (Using Min-Priority Queue)

```
Prim(Graph G, Vertex s):
    for each vertex v in G.V:
        key[v] ← ∞
        parent[v] ← NIL

    key[s] ← 0
    Q ← all vertices in G.V (min-priority queue ordered by key[])

    while Q ≠ ∅:
        u ← extract-min(Q)
        for each neighbor v of u:
            if v ∈ Q and w(u, v) < key[v]:
                parent[v] ← u
                key[v] ← w(u, v)****

    return parent[]  // Encodes the MST edges
```

---

# Explanation

- **Initialization**:
  - Set all vertex keys to ∞ except the starting vertex `s` (set to 0),
  - `key[v]` stores the minimum weight to connect `v` to the MST,
  - `parent[v]` tracks the MST structure.
- **Priority Queue**:
  - Vertices are prioritized based on the smallest `key[v]`,
- **Edge Relaxation**:
  - Whenever a better edge `(u, v)` is found, update `key[v]` and set `parent[v] = u`.

---

# Key Components

- **Min-Priority Queue**: Selects vertex with smallest connecting edge weight,
- **Key array**: Stores best known edge weight to connect each vertex,
- **Parent array**: Encodes the MST edges.

---

# Time Complexity

Depends on the data structure for the priority queue:
- **Using Binary Heap**:
  - Insert/Extract-Min: `O(log |V|)`,
  - Decrease-Key: `O(log |V|)`.

Total:

O((|V| + |E|) log |V|)


- **Using Fibonacci Heap** (theoretical):
  - Decrease-Key: `O(1)` amortized,
  - Extract-Min: `O(log |V|)`.

Total:

O(|E| + |V| log |V|)


---

# Space Complexity

- Key array: `O(|V|)`,
- Parent array: `O(|V|)`,
- Priority queue: up to `O(|V|)` elements.

Thus, total **space complexity**:

O(|V|)


---

# Summary Table

| Feature            | Prim's Algorithm                                |
|--------------------|-------------------------------------------------|
| Problem solved     | Minimum Spanning Tree (MST)                    |
| Strategy           | Greedy (grow MST from a starting point)         |
| Time complexity    | O((|V| + |E|) log |V|) (binary heap)             |
| Space complexity   | O(|V|)                                           |
| Cycle detection    | Implicitly avoided (tree is built carefully)    |

---

# Properties

- **Greedy algorithm**: Always adds the locally minimum weight edge,
- **Connected graph required**: Otherwise, only a partial MST would be produced,
- **Correctness**:
  - Follows from the **Cut Property**: the lightest edge crossing any cut belongs to some MST.

---

# Mathematical Justification (Correctness)

- **Cut Property**: For any cut `(S, V \ S)`, the minimum-weight edge crossing the cut is part of the MST.
- **Prim's method**:
  - Maintains a growing set `S` of vertices,
  - Always adds the minimum-weight edge connecting `S` to `V \ S`,
  - Ensures each step is safe and builds the correct MST incrementally.

---