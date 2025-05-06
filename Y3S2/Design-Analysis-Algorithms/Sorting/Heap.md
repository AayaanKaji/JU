# Heap Sort

```pseudo
heapSort(A):
    n = length(A)
    buildMaxHeap(A, n)
    for i = n - 1 down to 1:
        swap A[0] and A[i]         // move max to end
        heapify(A, 0, i)           // restore heap on reduced array

// Max element at root
buildMaxHeap(A, n):
    for i = floor(n / 2) - 1 down to 0:
        heapify(A, i, n)

heapify(A, i, n):
    largest = i
    left = 2 * i + 1
    right = 2 * i + 2

    if left < n and A[left] > A[largest]:
        largest = left
    if right < n and A[right] > A[largest]:
        largest = right

    if largest != i:
        swap A[i] and A[largest]
        heapify(A, largest, n)  // recursively heapify affected subtree
```

**Explanation**: Build a max-heap, then extract the maximum repeatedly.

| Case    | Time Complexity | Space |
| ------- | --------------- | ----- |
| Best    | O(n log n)      | O(1)  |
| Average | O(n log n)      | O(1)  |
| Worst   | O(n log n)      | O(1)  |

## Example:

Input: `[4, 10, 3, 5, 1]`

* Build heap: `[10, 5, 3, 4, 1]`
* Remove root: `[1, 5, 3, 4, 10]`
* Heapify: `[5, 4, 3, 1, 10]`
* Repeat → `[4, 1, 3, 5, 10]` → `[3, 1, 4, 5, 10]` → `[1, 3, 4, 5, 10]`

Sorted: `[1, 3, 4, 5, 10]`

---