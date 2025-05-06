## 🛣️ Bellman-Ford Algorithm

---

### 📘 Definition

The **Bellman-Ford algorithm** computes shortest paths from a **single source** vertex to all other vertices in a **weighted directed graph**, **allowing negative edge weights**.

Unlike Dijkstra's algorithm, it can **detect negative-weight cycles**.

---

### 💡 Key Idea

* Repeatedly **relax all edges** to improve path estimates.
* After $|V| - 1$ iterations, all shortest paths (with at most $|V| - 1$ edges) are found.
* A further improvement indicates a **negative-weight cycle**.

---

### 🎯 Problem Statement

Given:

* Directed graph $G = (V, E)$
* Weight function $w: E \rightarrow \mathbb{R}$
* Source vertex $s \in V$

Compute:

* Shortest-path distances $d(s, v)$ for all $v \in V$
* Detect if any **negative-weight cycle** is reachable from $s$

---

### 🧾 Pseudocode

```pseudo
Bellman-Ford(Graph G, Vertex s):
    for each vertex v in G.V:
        v.dist ← ∞
        v.prev ← null
    s.dist ← 0

    for i from 1 to |V| - 1:
        for each edge (u, v) in G.E:
            if u.dist + w(u, v) < v.dist:
                v.dist ← u.dist + w(u, v)
                v.prev ← u

    for each edge (u, v) in G.E:
        if u.dist + w(u, v) < v.dist:
            report "Negative-weight cycle detected"
            return False

    return True
```

---

### 🔍 Explanation

* **Initialization**:

  * Set all vertex distances to ∞ except the source.
* **Relaxation**:

  * Repeat $|V| - 1$ times:
    For each edge $(u, v)$, update $v.dist$ if a better path is found via $u$.
* **Cycle Detection**:

  * If any edge can be relaxed after $|V| - 1$ passes, a **negative-weight cycle** exists.

---

### 🧠 Key Concepts

* **Distance array**: Tracks shortest path estimates.
* **Predecessor array**: Enables shortest path reconstruction.
* **Relaxation**: Core operation to improve estimates.
* **Cycle check**: Final pass detects negative cycles.

---

### ⏱️ Time Complexity

* Each relaxation pass: $O(|E|)$
* Number of passes: $|V| - 1$

Total:

$$
O(|V| \cdot |E|)
$$

---

### 📦 Space Complexity

* Distance and predecessor arrays: $O(|V|)$
* Graph (edge list or adjacency list): $O(|V| + |E|)$

Total:

$$
O(|V| + |E|)
$$

---

### 📊 Summary Table

| Feature          | Bellman-Ford Algorithm                            |   |       |   |     |
| ---------------- | ------------------------------------------------- | - | ----- | - | --- |
| Problem Solved   | Single-source shortest path (with negative edges) |   |       |   |     |
| Graph Type       | Directed, weighted (allows negative weights)      |   |       |   |     |
| Cycle Detection  | Yes (negative-weight cycles)                      |   |       |   |     |
| Strategy         | Dynamic programming (edge relaxation)             |   |       |   |     |
| Time Complexity  | O(V.E) |
| Space Complexity | O(V + E) |

---

### 🧩 Properties

* Works with **negative weights**.
* Detects **negative-weight cycles**.
* Slower than Dijkstra for graphs with non-negative weights.
* Used in:

  * **Distance vector routing protocols** (e.g., RIP),
  * **Arbitrage detection** in finance,
  * **Graphs where cycles affect correctness**.

---

### 📐 Mathematical Justification

* After $i$ iterations, all shortest paths with at most $i$ edges are computed.
* Since any acyclic path has at most $|V| - 1$ edges, running the algorithm for $|V| - 1$ rounds suffices.
* A further improvement means a path can be made indefinitely shorter ⇒ **negative-weight cycle** exists.

---