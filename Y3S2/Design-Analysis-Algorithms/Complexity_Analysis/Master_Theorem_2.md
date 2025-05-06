## Master Theorem

Consider a recurrence of the form:

$$
T(n) = a \cdot T\left(\frac{n}{b}\right) + n^c \cdot \log ^d n
$$

where:

* $a \ge 1$ is the number of subproblems,
* $b > 1$ is the input size reduction factor,

---

### Case 1:

If

$$
c < \log_b a,
$$

then

$$
T(n) \in \Theta(n^{\log_b a})
$$

---

### **Case 2**:

If

$$
c\ =\ \log_b a,
$$

then

for d > -1,

$$
T(n) \in \Theta(n^c \log ^{d+1} n)
$$

for d = -1,

$$
T(n) \in \Theta(n^c \log \log n)
$$

for d < -1,

$$
T(n) \in \Theta(n^c)
$$
---

### **Case 2**:

If

$$
c\ >\ \log_b a,
$$

then

for d >= 0,

$$
T(n) \in \Theta(n^c \log ^{d} n)
$$

for d < 0,

$$
T(n) \in \Theta(n^c)
$$
---