# Floyd-Warshall Algorithm

## Definition

The **Floyd-Warshall algorithm** finds the shortest paths between **all pairs of vertices** in a weighted graph.  
It can handle **negative edge weights** but **no negative-weight cycles**.

**Key idea**:
- Gradually improve estimates of the shortest paths by allowing intermediate vertices,
- Dynamic programming approach based on the principle:
  - Either the shortest path from `i` to `j` goes through `k` or it does not.

---

# Problem Setting

Given:
- A directed graph `G = (V, E)`,
- A weight function `w: E → R` (weights may be negative but no negative cycles).

Goal:
- Compute `dist(i, j)`, the shortest-path distance from every vertex `i` to every vertex `j`.

---

# Pseudocode for Floyd-Warshall

```
Floyd-Warshall(Graph G):
    let dist be a |V| × |V| matrix of minimum distances
    
    for each vertex i in G.V:
        for each vertex j in G.V:
            if i == j:
                dist[i][j] ← 0 
            else if (i, j) ∈ E: 
                dist[i][j] ← w(i, j)
            else:
                dist[i][j] ← ∞

    for each vertex k in G.V:
        for each vertex i in G.V:
            for each vertex j in G.V:
                if dist[i][j] > dist[i][k] + dist[k][j]:
                    dist[i][j] ← dist[i][k] + dist[k][j]
```


---

# Explanation

- **Initialization**:
  - Distance from `i` to itself is `0`,
  - Distance from `i` to `j` is weight `w(i, j)` if an edge exists,
  - Otherwise, set distance to `∞`.
- **Triple nested loop**:
  - For each possible intermediate vertex `k`,
  - Update distance from `i` to `j` if a shorter path through `k` is found.

---

# Key Components

- **Distance matrix `dist[i][j]`**: Maintains shortest distance estimates,
- **Dynamic programming**:
  - Build solutions for paths that use up to `k` intermediate vertices,
  - Reuse smaller subpath solutions.

---

# Time Complexity

- Three nested loops over `|V|` vertices.

Thus, total **time complexity**: O(|V|^3)


---

# Space Complexity

- Distance matrix stores `|V| × |V|` entries.

Thus, **space complexity**: O(|V|^2)


---

# Summary Table

| Feature           | Floyd-Warshall Algorithm |
| ----------------- | ------------------------ |
| Problem solved    | All-pairs shortest paths |
| Handles negatives | Yes (no negative cycles) |
| Time complexity   | O(V^3)                   |
| Space complexity  | O(V^2)                   |
| Strategy          | Dynamic programming      |

---

# Properties of Floyd-Warshall

- Handles **negative edge weights** (but not **negative-weight cycles**),
- Can reconstruct paths using a **predecessor matrix** (optional enhancement),
- Detects negative cycles by observing diagonal entries:
  - If `dist[i][i] < 0` for any `i`, a negative-weight cycle exists,
- Used in:
  - **Network routing**,
  - **Shortest path computations** where all-pairs information is needed,
  - **Transitive closure** computation in graphs.

---

# Mathematical Justification (Correctness)

Dynamic programming formulation:

Let `dist_k(i, j)` denote the length of the shortest path from `i` to `j` using only vertices `{1, ..., k}` as intermediate points.

Recurrence relation:

dist_k(i, j) = min(dist_{k-1}(i, j), dist_{k-1}(i, k) + dist_{k-1}(k, j))


- Base case `k = 0`: Only direct edges,
- Build up as `k` increases from `1` to `|V|`.

Thus, after considering all vertices as intermediates, `dist(i, j)` gives the true shortest path length from `i` to `j`.

---