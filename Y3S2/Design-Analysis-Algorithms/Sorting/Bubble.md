# Bubble Sort

```pseudo
for i = 0 to n-1:
    // Largest elements are at the end
    for j = 0 to n-i-2:
        if A[j] > A[j+1]:
            swap A[j] and A[j+1]
```

**Explanation**: Repeatedly swap adjacent elements if they are in the wrong order. Largest elements "bubble" to the end.

| Case    | Time Complexity | Space |
| ------- | --------------- | ----- |
| Best    | O(n)            | O(1)  |
| Average | O(n²)           | O(1)  |
| Worst   | O(n²)           | O(1)  |

## Example:

Input: `[5, 1, 4, 2, 8]`

* Pass 1: `[1, 4, 2, 5, 8]`
* Pass 2: `[1, 2, 4, 5, 8]`
* Pass 3: `[1, 2, 4, 5, 8]`
* Pass 4: `[1, 2, 4, 5, 8]`

Sorted: `[1, 2, 4, 5, 8]`

---