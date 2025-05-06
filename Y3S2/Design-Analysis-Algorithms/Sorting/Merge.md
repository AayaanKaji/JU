# Merge Sort

```pseudo
// given Array, left_index, right_index
mergeSort(A, l, r):
    if l < r:
        m = (l + r) / 2
        mergeSort(A, l, m)
        mergeSort(A, m+1, r)
        merge(A, l, m, r)

merge(A, l, m, r):
    // number of elements in each side
    n1 = m - l + 1
    n2 = r - m

    create array L[size of n1] and R[size of n2]
    
    for i = 0 to n1 - 1:
        L[i] = A[l + i]
    for j = 0 to n2 - 1:
        R[j] = A[m + 1 + j]

    i = 0, j = 0, k = l
    while i < n1 and j < n2:
        if L[i] <= R[j]:
            A[k] = L[i]
            i = i + 1
        else:
            A[k] = R[j]
            j = j + 1
        k = k + 1

    // only one of these loops can run
    // Copy any remaining elements of L[]
    while i < n1:
        A[k++] = L[i++]

    // Copy any remaining elements of R[]
    while j < n2:
        A[k++] = R[j++]

```

**Explanation**: Divide the array into halves, sort each recursively, and merge.

| Case    | Time Complexity | Space |
| ------- | --------------- | ----- |
| Best    | O(n log n)      | O(n)  |
| Average | O(n log n)      | O(n)  |
| Worst   | O(n log n)      | O(n)  |

## Example:

Input: `[5, 2, 9, 1, 5, 6]`

1. Split: `[5, 2, 9]` and `[1, 5, 6]`
2. Split: `[5, 2]` and `[9]`
3. Split: `[5]` and `[2]`
4. Merge: `[5]` + `[2]` → `[2, 5]`
5. Merge: `[2, 5]` + `[9]` → `[2, 5, 9]`
6. Split: `[1, 5]` and `[6]`
7. Split: `[1]` and `[5]`
8. Merge: `[1]` + `[5]` → `[1, 5]`
9. Merge: `[1, 5]` + `[6]` → `[1, 5, 6]`
10. Merge: `[1, 2, 5, 5, 6, 9]`

Sorted: `[1, 2, 5, 5, 6, 9]`

---