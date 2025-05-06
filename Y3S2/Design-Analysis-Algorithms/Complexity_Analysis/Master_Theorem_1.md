## Master Theorem

Consider a recurrence of the form:

$$
T(n) = a \cdot T\left(\frac{n}{b}\right) + f(n)
$$

where:

* $a \ge 1$ is the number of subproblems,
* $b > 1$ is the input size reduction factor,
* $f(n)$ is the cost of work done **outside** the recursive calls (e.g., divide and combine steps).

Let:

$$
E = \log_b a
$$

Then:

---

### **Case 1** (Polynomially smaller work):

If

$$
f(n) \in O(n^{E - \varepsilon}) \text{ for some } \varepsilon > 0,
$$

then

$$
T(n) \in \Theta(n^E)
$$

---

### **Case 2** (Same order work):

If

$$
f(n) \in \Theta(n^E),
$$

then

$$
T(n) \in \Theta(n^E \log n)
$$

---

### **Case 3** (Polynomially larger work):

If

$$
f(n) \in \Omega(n^{E + \varepsilon}) \text{ for some } \varepsilon > 0,
$$

**and** if the regularity condition holds:

$$
\exists c < 1,\ \forall n \ge n_0,\ a \cdot f(n/b) \le c \cdot f(n),
$$

then

$$
T(n) \in \Theta(f(n))
$$

---

## Notes

* The theorem **only applies** if the recurrence exactly matches the form $T(n) = a T(n/b) + f(n)$, where **subproblem sizes are equal and divide evenly**.

---

## Extra

Consider a recurrence of the form:

$$
T(n) = a \cdot T\left(\frac{n}{b}\right) + n^c \cdot \log ^d n
$$

if

$$
c < \log_a b
$$

then

$$

$$