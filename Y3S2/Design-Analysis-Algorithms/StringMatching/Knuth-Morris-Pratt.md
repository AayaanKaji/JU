# Knuth-Morris-Pratt (KMP) Algorithm

## Definition

The **KMP algorithm** efficiently finds occurrences of a **pattern** `P[k..m-1]` in a **text** `T[0..n-1]` by avoiding unnecessary comparisons.

**Key idea**:
- When a mismatch occurs, use previously matched characters to avoid rechecking them,
- Precompute a **Longest Prefix Suffix (LPS)** array for the pattern.

---

# Problem Setting

Given:
- A **text** `T` of length `n`,
- A **pattern** `P` of length `m`.

Goal:
- Find all occurrences of `P` as a substring in `T`.

---

# Pseudocode

## Preprocessing (LPS Array Construction)

```
computeLPSArray(P: string, m: int, lps: array):
    // 1st char has no pattern so, put zero length pattern
    lps[0] ← 0
    length ← 0
    // Start with the 2nd char
    i ← 1

    while i < m:
        // Check prefix pattern present
        if P[i] == P[length]:
            length ← length + 1
            lps[i] ← length
            i ← i + 1
        else:
            if length ≠ 0:
                length ← lps[length - 1]
            else:
                lps[i] ← 0
                i ← i + 1
```
eg: 
    A B A B C A B A B
    0 0 1 2 0 1 2 3 4

---

## Searching (Using LPS Array)

```
KMPsearch(T: string, P: string):
    n ← length of T
    m ← length of P
    lps ← array of size m

    computeLPSArray(P, m, lps)

    i ← 0  // index for T
    j ← 0  // index for P

    while i < n:
        if P[j] == T[i]:
            i ← i + 1
            j ← j + 1

        if j == m:
            print "Pattern found at index" (i - j)
            j ← lps[j - 1]
        else if i < n and P[j] ≠ T[i]:
            if j ≠ 0:
                j ← lps[j - 1]
            else:
                i ← i + 1
```

---

# Explanation

- **LPS array**:
  - `lps[i]` is the length of the longest proper prefix of `P[0..i]` which is also a suffix,
- **Matching**:
  - When characters match, move forward in both `T` and `P`,
  - When a mismatch occurs:
    - If `j ≠ 0`, use `lps[j-1]` to avoid redundant comparisons,
    - Otherwise, move forward in `T`.

---

# Key Components

- **LPS array**: Precomputed in `O(m)` time,
- **Two pointers**: `i` for text, `j` for pattern,
- **Efficient backtracking**: Achieved using LPS.

---

# Time Complexity

- Preprocessing (LPS array computation): `O(m)`,
- Searching through text: `O(n)`.

Thus, total **time complexity**:

O(n + m)


---

# Space Complexity

- LPS array: `O(m)`.

Thus, total **space complexity**:

O(m)


---

# Summary Table

| Feature            | KMP Algorithm                                |
|--------------------|---------------------------------------------|
| Problem solved     | String pattern matching                     |
| Strategy           | Preprocessing pattern to skip unnecessary comparisons |
| Time complexity    | O(n + m)                                     |
| Space complexity   | O(m)                                         |
| Preprocessing step | LPS array construction                      |

---

# Properties

- **Deterministic**: Always finds the first occurrence (or all) without rechecking characters,
- **Useful for**:
  - Text editors (find operations),
  - DNA sequence analysis,
  - Substring search problems,
- **Avoids redundant work**:
  - Does not backtrack in the text `T`,
  - Only shifts the pattern intelligently based on LPS values.

---

# Mathematical Insight

The algorithm ensures that at each mismatch:
- The indices `(i, j)` either move strictly forward,
- Or the pattern shifts according to previously computed prefix-suffix information,
thus ensuring **linear time** in the combined size of the text and pattern.

---****