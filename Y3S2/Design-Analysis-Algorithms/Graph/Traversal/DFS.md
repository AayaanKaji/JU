# Depth-First Search (DFS)

## Definition

Depth-First Search (DFS) is a fundamental graph traversal algorithm.
It explores a graph **deeply** by going as far along each branch as possible before **backtracking**.

**Key idea**:  
- Follow a path from the starting node as far as possible
- Backtrack when no further progress can be made
- Repeat until all vertices are visited

---

# Pseudocode for DFS (Recursive Form)

```
DFS(Graph G):
    // Mark all vertexes as unvisited
    for each vertex v in G.V:
        v.visited ← false

    for each vertex v in G.V:
        if v.visited = false:
            DFS-Visit(G, v)

DFS-Visit(Graph G, Vertex v):
    v.visited <- true

    // Visits it's neighbors
    for each neighbor u in G.Adj[v]:
        if u.visited = false:
            DFS-Visit(G, u)
```

---

# Explanation

- Start from an **unvisited vertex** \( v \),
- Recursively explore all unvisited neighbors,
- After finishing all neighbors, backtrack,
- Repeats for disconnected components (if any).

---

# Key Components

- **Graph G**: with vertex set \( G.V \) and adjacency list \( G.Adj \),
- **Visited marker**: Boolean value to prevent revisiting nodes,
- **Recursion**: Implements an implicit **stack**.

---

# Iterative Version (using Explicit Stack)

Instead of recursion, we can manually manage a **stack**:

```
DFS-Iterative(Graph G, Vertex s):
    for each vertex v in G.V:
        v.visited ← false

    create an empty stack S
    S.push(s)

    while S is not empty:
        v ← S.pop()

        if v.visited = false:
            mark v as visited
            process(v)

            for each neighbor u of v in G.Adj[v] (in any order):
                if u.visited = false:
                    S.push(u)
```

---

# Time Complexity

- Each vertex is visited exactly once: \( O(|V|) \),
- Each edge is examined once (in undirected graphs) or once per direction (in directed graphs): \( O(|E|) \).

Thus, total **time complexity**: 
\[
O(|V| + |E|)
\]

---

# Space Complexity

- Recursive call stack or explicit stack can grow up to \( O(|V|) \) in the worst case (e.g., the graph is a long path),
- Visited array requires \( O(|V|) \) space.

Thus, **space complexity**:

\[
O(|V|)
\]

---

# Summary Table

| Feature          | DFS                                         |
| ---------------- | ------------------------------------------- |
| Data structure   | Stack (explicit or via recursion)           |
| Time complexity  | O(\|V\| + \|E\|)                            |
| Space complexity | O(\|V\|)                                    |
| Traversal order  | Deepest unexplored node first (depth-first) |

---

# Properties of DFS

- Can be used to detect **cycles**,
- Can classify edges (tree, back, forward, cross edges) — useful in **graph theory**,
- Used in algorithms like:
  - **Topological Sorting** (for Directed Acyclic Graphs),
  - **Strongly Connected Components** (Kosaraju's algorithm, Tarjan's algorithm),
  - **Finding Bridges and Articulation Points**.

---