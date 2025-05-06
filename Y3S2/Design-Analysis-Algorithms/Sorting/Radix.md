# Radix Sort *(non-comparison)*

```pseudo
radixSort(A):
    max_val = max(A)
    exp = 1
    while max_val // exp > 0:
        countingSortByDigit(A, exp)
        exp *= 10

// Counting sort is a **Stable Sort** i.e. the position of elements in the original array remains same
countingSortByDigit(A, exp):
    n = length(A)
    output[0...n-1]
    count[0...9] = {0}

    // Count occurrences of digits at (exp) place
    for i = 0 to n-1:
        digit = (A[i] // exp) % 10
        count[digit] += 1

    // Transform count[i] to actual position in output[]
    for i = 1 to 9:
        count[i] += count[i - 1]

    // Build output[] (stable)
    for i = n-1 downto 0:
        digit = (A[i] // exp) % 10
        output[count[digit] - 1] = A[i]
        count[digit] -= 1

    // Copy output back to A
    for i = 0 to n-1:
        A[i] = output[i]

```

**Explanation**: Sort by least significant digit using a stable sort (like counting sort), for all digits.

| Case    | Time Complexity    | Space  |
| ------- | ------------------ | ------ |
| Best    | O(nk) (k = digits) | O(n+k) |
| Average | O(nk)              | O(n+k) |
| Worst   | O(nk)              | O(n+k) |

## Example:

Input: `[170, 45, 75, 90, 802, 24, 2, 66]`

* 1st pass (units): `[170, 90, 802, 2, 24, 45, 75, 66]`
* 2nd pass (tens): `[802, 2, 24, 45, 66, 170, 75, 90]`
* 3rd pass (hundreds): `[2, 24, 45, 66, 75, 90, 170, 802]`

Sorted: `[2, 24, 45, 66, 75, 90, 170, 802]`

---