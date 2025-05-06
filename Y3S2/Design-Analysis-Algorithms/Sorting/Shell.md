# Shell Sort

```pseudo
shellSort(A):
    for gap in decreasing_sequence:
        for i = gap to n-1:
            temp = A[i]
            j = i
            while j >= gap and A[j - gap] > temp:
                A[j] = A[j - gap]
                j -= gap
            A[j] = temp
```

**Explanation**: Generalization of insertion sort that compares elements far apart and reduces gap. So, swap smaller elements on the right with larger element on the left. After reducing gap apply insertion sort which requires less comparisons. 

| Case    | Time Complexity      | Space |
| ------- | -------------------- | ----- |
| Best    | O(n log n) (depends) | O(1)  |
| Average | O(n log² n) or worse | O(1)  |
| Worst   | O(n²)                | O(1)  |

## Example:

Input: `[12, 34, 54, 2, 3]`

* Gap 2: `[12, 34, 54, 2, 3]` → `[12, 3, 54, 2, 34]`
* Gap 1: `[3, 12, 2, 34, 54]`

Sorted: `[2, 3, 12, 34, 54]`

---