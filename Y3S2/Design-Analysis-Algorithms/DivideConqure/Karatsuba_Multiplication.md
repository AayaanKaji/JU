# Karatsuba's Multiplication Algorithm

## Definition

Karatsuba's algorithm is a **divide-and-conquer** method for multiplying large integers faster than the standard \( O(n^2) \) algorithm, which multiples 1st number with 2nd number's digit one at a time and finally adds all the products.

It reduces the multiplication of two \( n \)-digit numbers to at most **three multiplications** of \( n/2 \)-digit numbers, plus some additions and shifts.

**Key idea**:
- **Divide** the numbers into halves,
- Perform only **three recursive multiplications** instead of four,
- **Combine** results to reconstruct the final product.

---

# Mathematical Basis

Given two \( n \)-digit numbers \( x \) and \( y \), split each into two halves:

\[
x = a * 10^{m} + b
\]
\[
y = c * 10^{m} + d
\]

where:
- \( m = floor of n/2 \),
- \( a, b, c, d \) are approximately \( n/2 \)-digit numbers.

Then:

\[
xy = (a * 10^m + b)(c * 10^m + d)
\]
\[
= ac * 10^{2m} + (ad + bc) * 10^{m} + bd
\]

**Naively**, computing \( xy \) this way would require **four multiplications** of half-size numbers:
- \( ac, ad, bc, bd \)

**Karatsuba's optimization**:

Instead, compute:

1. \( p_1 = bd \),
2. \( p_2 = ac \),
3. \( p_3 = (a + b)(c + d) - p_2 - p_1 \) as this equals to \( ad + bc \).

Thus:

\[
xy = p_2 * 10^{2m} + p_3 * 10^{m} + p_1
\]

This requires **only three multiplications**: \( p_1, p_3, p_2 \), plus \( O(n) \) additions and subtractions.

---

# Pseudocode for Karatsuba

```
Karatsuba(x, y):
    // Base case
    if (x or y) is small enough:
        return x * y

    // Number of digits
    n ← max(length(x), length(y))
    m ← floor(n / 2)

    // Split the numbers
    a, b ← split x at position m
    c, d ← split y at position m

    // Three recursive multiplications
    p1 ← Karatsuba(b, d)
    p2 ← Karatsuba(a, c)
    p3 ← Karatsuba(a + b, c + d) - p2 - p1

    // Combine the results
    return (p2 × 10^(2m)) + (p3 × 10^m) + p1
```

---

# Time Complexity

Let \( T(n) \) denote the time to multiply two \( n \)-digit numbers.

Karatsuba’s recurrence relation is:

\[
T(n) = 3T(n/2) + O(n^1 * (logn)^0)
\]

Using the **Master Theorem** for divide-and-conquer recurrences:
\[
T(n) = aT(n/b) + O(n^c * (logn)^d)
\]

- \( a = 3 \),
- \( b = 2 \),
- \( f(n) = O(n) \).

Thus:

\[
T(n) = O(n^{log_2 3}) = O(n^{1.585})
\]

which is **asymptotically faster** than the standard \( O(n^2) \) multiplication.

---

# Space Complexity

- Recursive stack depth: \( O(\log n) \),
- Each level uses \( O(n) \) space for additions and splits.

Thus, **space complexity**:

\[
O(n)
\]

assuming reuse of temporary storage.

---

# Summary Table

| Feature          | Karatsuba Algorithm                           |
| ---------------- | --------------------------------------------- |
| Strategy         | Divide and Conquer                            |
| Recursive calls  | 3 subproblems of size \( n/2 \)               |
| Time complexity  | \( O(n^{log_2 3}) \)                          |
| Space complexity | \( O(n) \)                                    |
| Advantage        | Faster than naive \( O(n^2) \) multiplication |

---

# Properties of Karatsuba's Algorithm

- **First** known multiplication algorithm faster than \( O(n^2) \) (discovered in 1960 by Anatoly Karatsuba),
- Suitable for moderately large integers (practical cut-off around a few hundred digits),
- For very large integers, even faster algorithms exist (e.g., **Toom–Cook**, **Schönhage–Strassen**).

---