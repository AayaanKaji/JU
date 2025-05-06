# Quick Sort

```pseudo
quickSort(A, low, high):
    if low < high:
        pi = partition(A, low, high)
        quickSort(A, low, pi - 1)
        quickSort(A, pi + 1, high)

// After partition: Array[smaller than pivot - pivot - greater than pivot]
partition(A, low, high):
    pivot = A[high]         // choose last element as pivot
    i = low - 1             // place for the smaller element

    for j = low to high - 1:
        if A[j] <= pivot:
            i = i + 1
            swap A[i] with A[j]

    swap A[i + 1] with A[high]  // place pivot in correct position
    return i + 1                // pivot index
```

**Explanation**: Partition around a pivot; recursively sort subarrays.

| Case    | Time Complexity | Space    |
| ------- | --------------- | -------- |
| Best    | O(n log n)      | O(log n) |
| Average | O(n log n)      | O(log n) |
| Worst   | O(n²)           | O(log n) |

## Example:

Input: `[10, 90, 30, 80, 20, 50, 70]`

* Pivot = 70
* Partition → `[10, 30, 20, 50, 70, 90, 80]`
* Recurse left `[10, 30, 20, 50]` and right `[90, 80]`
* Final sorted: `[10, 20, 30, 50, 70, 80, 90]`
  
---