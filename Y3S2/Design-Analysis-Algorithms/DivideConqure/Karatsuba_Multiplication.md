# Karatsuba's Multiplication Algorithm

---

## Definition

Karatsuba's algorithm is a **divide-and-conquer** technique for multiplying large integers more efficiently than the standard grade-school method (which runs in $O(n^2)$).

It reduces multiplication of two $n$-digit numbers to **three multiplications** of $n/2$-digit numbers plus a few additions and shifts.

---

## Key Idea

Given two numbers $x$ and $y$, split them:

$$
x = a \cdot 10^m + b \\
y = c \cdot 10^m + d
$$

where:

* $m = \lfloor n/2 \rfloor$,
* $a, b, c, d$ are the high/low parts of the digits.

Then:

$$
xy = ac \cdot 10^{2m} + (ad + bc) \cdot 10^m + bd
$$

Instead of computing all four products ($ac, ad, bc, bd$), Karatsuba does:

* $p_1 = bd$
* $p_2 = ac$
* $p_3 = (a + b)(c + d) - p_1 - p_2$
  (This equals $ad + bc$)

Final result:

$$
xy = p_2 \cdot 10^{2m} + p_3 \cdot 10^m + p_1
$$

---

## Pseudocode

```pseudo
Karatsuba(x, y):
    if x or y is small:
        return x * y

    n = max(length(x), length(y))
    m = floor(n / 2)

    a, b = split x at m
    c, d = split y at m

    p1 = Karatsuba(b, d)
    p2 = Karatsuba(a, c)
    p3 = Karatsuba(a + b, c + d) - p1 - p2

    return (p2 × 10^(2m)) + (p3 × 10^m) + p1
```

---

### Time Complexity

Let $T(n)$ be the time to multiply two $n$-digit numbers:

$$
T(n) = 3T(n/2) + O(n)
$$

Using the Master Theorem:

$$
T(n) = O(n^{\log_2 3}) \approx O(n^{1.585})
$$

Faster than the classical $O(n^2)$ multiplication.

---

### Space Complexity

* Recursive depth: $O(\log n)$
* Temporary space per level: $O(n)$

Overall:

$$
\text{Space} = O(n)
$$

---

### Summary Table

| Feature          | Value                       |
| ---------------- | --------------------------- |
| Strategy         | Divide and Conquer          |
| Recursive Calls  | 3 recursive multiplications |
| Time Complexity  | $O(n^{1.585})$              |
| Space Complexity | $O(n)$                      |
| Advantage        | Faster than naive $O(n^2)$  |

---

### Notes

* First algorithm to break the $O(n^2)$ barrier (Karatsuba, 1960)
* Suitable for numbers up to a few hundred digits
* For very large inputs, algorithms like **Toom-Cook** and **Schönhage–Strassen** are faster

---