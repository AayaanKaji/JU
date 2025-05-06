# Selection Sort

```pseudo
for i = 0 to n-1:
    min_idx = i
    for j = i+1 to n-1:
        if A[j] < A[min_idx]:
            min_idx = j
    swap A[i] and A[min_idx]
```

**Explanation**: Repeatedly select the smallest remaining element and move it to its correct position.

| Case    | Time Complexity | Space |
| ------- | --------------- | ----- |
| Best    | O(n²)           | O(1)  |
| Average | O(n²)           | O(1)  |
| Worst   | O(n²)           | O(1)  |

## Example:

Input: `[29, 10, 14, 37, 14]`

* Pass 1: `[10, 29, 14, 37, 14]`
* Pass 2: `[10, 14, 29, 37, 14]`
* Pass 3: `[10, 14, 14, 37, 29]`
* Pass 4: `[10, 14, 14, 29, 37]`

Sorted: `[10, 14, 14, 29, 37]`

---