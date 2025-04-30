# Rabin-Karp Algorithm

## Definition

The **Rabin-Karp algorithm** is a string matching algorithm that uses **hashing** to find occurrences of a **pattern** `P[0..m-1]` in a **text** `T[0..n-1]`.

**Key idea**:
- Compute hash values of the pattern and text substrings,
- Compare hashes to quickly identify potential matches,
- Confirm matches by direct character comparison if hashes match (to avoid false positives).

---

# Problem Setting

Given:
- A **text** `T` of length `n`,
- A **pattern** `P` of length `m`.

Goal:
- Find all starting indices `i` such that `T[i..i+m-1] == P[0..m-1]`.

---

# Pseudocode

```
RabinKarp(T: string, P: string, d: int, q: int):
    n ← length of T
    m ← length of P
    h ← (d^(m-1)) mod q
    p ← 0 // hash value for pattern
    t ← 0 // hash value for text window

    // Preprocessing: calculate hash of pattern and first text window
    for i from 0 to m-1:
        p ← (d*p + ord(P[i])) mod q
        t ← (d*t + ord(T[i])) mod q

    for i from 0 to n-m:
        if p == t:
            // If hash values match, check characters one by one
            if T[i..i+m-1] == P[0..m-1]:
                print "Pattern found at index", i

        if i < n-m:
            t ← (d*(t - ord(T[i])*h) + ord(T[i+m])) mod q
            if t < 0:
                t ← t + q
```

---

# Explanation

- `d`: Number of possible characters (e.g., 256 for ASCII),
- `q`: A large prime number to reduce hash collisions and prevent integer overflow,
- `h`: Precomputed value of `d^(m-1) mod q` to help update the rolling hash efficiently,
- **Rolling hash**:
  - When moving the window, subtract the old character and add the new one,
  - Adjust modulo `q` to keep hash values small and manageable.

---

# Key Components

- **Hashing**:
  - Represents strings as numerical values,
- **Rolling hash**:
  - Efficiently updates the hash value in constant time `O(1)` when shifting the window,
- **Collision handling**:
  - If hashes match, verify by direct character comparison to confirm.

---

# Time Complexity

- Preprocessing (pattern and initial window hash): `O(m)`,
- For each shift:
  - Hash comparison: `O(1)`,
  - Full comparison (only if hashes match): worst-case `O(m)`.

Thus:
- **Best case** (few hash collisions):

O(n + m)


- **Worst case** (many hash collisions):

O(nm)


In practice, with a good hash function and large `q`, collisions are rare.

---

# Space Complexity

- Storing a few integers and the pattern: `O(1)` additional space.

Thus, total **space complexity**:

O(1)

(excluding the text and pattern storage).

---

# Summary Table

| Feature            | Rabin-Karp Algorithm                            |
|--------------------|-------------------------------------------------|
| Problem solved     | String pattern matching                         |
| Strategy           | Hashing + Rolling Hash                          |
| Best time complexity | O(n + m)                                       |
| Worst time complexity | O(nm)                                         |
| Space complexity   | O(1)                                             |
| Preprocessing step | Compute initial hashes                          |

---

# Properties

- **Efficient for multiple pattern matching**:
  - If many patterns are searched simultaneously, Rabin-Karp is very effective,
- **Hash collisions**:
  - Hash match does not guarantee string match — verify manually,
- **Useful for**:
  - Plagiarism detection,
  - Database lookups,
  - DNA sequence matching.

---

# Mathematical Insight

- Rolling hash update:

  \[
  t_{\text{new}} = (d \times (t_{\text{old}} - \text{ord}(T[i]) \times h) + \text{ord}(T[i+m])) \mod q
  \]

- This ensures constant time update for each window, enabling efficient traversal of the text.

---