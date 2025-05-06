## 1. **Big-O Notation (O)** — *Asymptotic Upper Bound*

$$
f(n) \in O(g(n)) \iff \exists\ c > 0,\ \exists\ n_0 \in \mathbb{N},\ \forall n \geq n_0,\ f(n) \leq c \cdot g(n)
$$

**Interpretation**: For large $n$, $f(n)$ grows at most as fast as a constant multiple of $g(n)$.

---

## 2. **Big-Omega Notation (Ω)** — *Asymptotic Lower Bound*

$$
f(n) \in \Omega(g(n)) \iff \exists\ c > 0,\ \exists\ n_0 \in \mathbb{N},\ \forall n \geq n_0,\ f(n) \geq c \cdot g(n)
$$

**Interpretation**: For large $n$, $f(n)$ grows at least as fast as a constant multiple of $g(n)$.

---

## 3. **Big-Theta Notation (Θ)** — *Asymptotically Tight Bound*

$$
f(n) \in \Theta(g(n)) \iff \exists\ c_1, c_2 > 0,\ \exists\ n_0 \in \mathbb{N},\ \forall n \geq n_0,\ c_1 \cdot g(n) \leq f(n) \leq c_2 \cdot g(n)
$$

**Interpretation**: For large $n$, $f(n)$ grows at the same rate as $g(n)$, up to constant factors.

---

## 4. **Little-o Notation (o)** — *Strict Upper Bound*

$$
f(n) \in o(g(n)) \iff \forall c > 0,\ \exists\ n_0 \in \mathbb{N},\ \forall n \geq n_0,\ f(n) < c \cdot g(n)
$$

**Equivalently**:

$$
\lim_{n \to \infty} \frac{f(n)}{g(n)} = 0
$$

**Interpretation**: $f(n)$ grows strictly slower than $g(n)$ as $n \to \infty$.

---

## 5. **Little-omega Notation (ω)** — *Strict Lower Bound*

$$
f(n) \in \omega(g(n)) \iff \forall c > 0,\ \exists\ n_0 \in \mathbb{N},\ \forall n \geq n_0,\ f(n) > c \cdot g(n)
$$

**Equivalently**:

$$
\lim_{n \to \infty} \frac{f(n)}{g(n)} = \infty
$$

**Interpretation**: $f(n)$ grows strictly faster than $g(n)$ as $n \to \infty$.

---

## Summary Table

| Notation       | Meaning          | Formal Comparison                              |
| -------------- | ---------------- | ---------------------------------------------- |
| $O(g(n))$      | Upper bound      | $f(n) \leq c \cdot g(n)$                       |
| $\Omega(g(n))$ | Lower bound      | $f(n) \geq c \cdot g(n)$                       |
| $\Theta(g(n))$ | Tight bound      | $c_1 \cdot g(n) \leq f(n) \leq c_2 \cdot g(n)$ |
| $o(g(n))$      | Strictly smaller | $\lim \frac{f(n)}{g(n)} = 0$                   |
| $\omega(g(n))$ | Strictly larger  | $\lim \frac{f(n)}{g(n)} = \infty$              |

---