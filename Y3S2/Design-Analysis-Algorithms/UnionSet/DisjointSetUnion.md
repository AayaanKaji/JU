# Union-Find (Disjoint Set Union - DSU)

## Definition

The **Union-Find** (or **Disjoint Set Union**, DSU) data structure maintains a collection of **disjoint sets** and supports two operations efficiently:
- `find(u)`: Find the representative (root) of the set containing element `u`,
- `union(u, v)`: Merge the sets containing `u` and `v`.

Optimizations:
- **Union by Rank**: Always attach the smaller tree under the root of the larger tree,
- **Path Compression**: During `find`, make each node on the path point directly to the root.

These optimizations ensure **almost constant time** operations.

---

# Use Cases

- **Kruskal’s Minimum Spanning Tree Algorithm**,
- **Connected components** in a graph,
- **Cycle detection** in undirected graphs,
- **Dynamic connectivity** queries.

---

# Pseudocode for Union-Find with Rank and Path Compression

```
Initialization:
  make-set(u):
    parent[u] ← u
    rank[u] ← 0

Find with Path Compression:
  find(u):
    if parent[u] ≠ u:
      parent[u] ← find(parent[u])
    // Path compression
    return parent[u]


Union by Rank:
  union(u, v):
    uRoot ← find(u)
    vRoot ← find(v)
    
    if uRoot = vRoot:
      return  // Already in the same set

    // Attach smaller rank tree under larger rank tree
    if rank[uRoot] < rank[vRoot]:
        parent[uRoot] ← vRoot
    else if rank[uRoot] > rank[vRoot]:
        parent[vRoot] ← uRoot
    else:
        parent[vRoot] ← uRoot
        rank[uRoot] ← rank[uRoot] + 1
```

---

# Explanation

- **make-set(u)**: Initializes `u` as its own parent (a singleton set),
- **find(u)**:
  - Recursively finds the root of `u`,
  - While doing so, it flattens the path (makes all nodes on the path point directly to the root),
- **union(u, v)**:
  - Merges the two trees by comparing their ranks (approximate heights),
  - The root with the **higher rank** becomes the parent,
  - If ranks are equal, choose one arbitrarily and increase its rank by 1.

---

# Time Complexity

Each operation (`find` and `union`) runs in **amortized** time:

O(α(n))


where `α(n)` is the **inverse Ackermann function**, which grows extremely slowly:
- For all practical values (`n <= 10^80`), `α(n) <= 5`.

Thus, **almost constant time** per operation in practice.

---

# Space Complexity

- `parent` array: `O(n)`,
- `rank` array: `O(n)`.

Thus, total **space complexity**:

O(n)


where `n` is the number of elements.

---

# Summary Table

| Feature                   | Union-Find with Rank and Path Compression         |
|----------------------------|---------------------------------------------------|
| Problem solved             | Maintain disjoint sets                           |
| Operations supported       | find, union                                       |
| Optimizations              | Union by rank, path compression                  |
| Time complexity (amortized)| O(α(n)) per operation                             |
| Space complexity           | O(n)                                              |

---

# Properties

- **Correctness**: Union by rank keeps trees shallow; path compression flattens trees further,
- **Efficiency**: Critical for algorithms like Kruskal’s MST, where many union/find operations are needed,
- **Non-destructive**: Operations modify internal structure but preserve logical set membership.

---

# Mathematical Note

The amortized time complexity `O(α(n))` results from a deep analysis involving the **inverse Ackermann function**.  
Formally:

If `m` operations (finds and unions) are performed on `n` elements:

Total time = O(m α(n))


Thus, extremely efficient even for very large datasets.

---
