# Insertion Sort

```pseudo
for i = 1 to n-1:
    key = A[i]
    j = i - 1
    while j >= 0 and A[j] > key:
        A[j + 1] = A[j]
        j = j - 1
    A[j + 1] = key
```

**Explanation**: Build sorted array by inserting elements into their correct position.

| Case    | Time Complexity | Space |
| ------- | --------------- | ----- |
| Best    | O(n)            | O(1)  |
| Average | O(n²)           | O(1)  |
| Worst   | O(n²)           | O(1)  |

## Example:

Input: `[12, 11, 13, 5, 6]`

* Insert 11: `[11, 12, 13, 5, 6]`
* Insert 13: `[11, 12, 13, 5, 6]`
* Insert 5: `[5, 11, 12, 13, 6]`
* Insert 6: `[5, 6, 11, 12, 13]`

Sorted: `[5, 6, 11, 12, 13]`

---