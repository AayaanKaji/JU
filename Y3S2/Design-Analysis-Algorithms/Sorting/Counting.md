# Counting Sort *(non-comparison)*

---

## Version 1: Using Fixed-Size Array

```pseudo
countSort(A):
    min_val = min(A)
    max_val = max(A)
    range = max_val - min_val + 1

    count[0...range-1] = {0}
    for x in A:
        count[x - min_val] += 1

    index = 0
    for i = 0 to range - 1:
        while count[i] > 0:
            A[index] = i + min_val
            count[i] -= 1
            index += 1
```

**Explanation**: Use an array of size equal to the range. Shift by `min_val` to handle negative values.

| Case    | Time Complexity | Space |
| ------- | --------------- | ----- |
| Best    | O(n + k)        | O(k)  |
| Average | O(n + k)        | O(k)  |
| Worst   | O(n + k)        | O(k)  |

**Example**:

Input: `[4, 2, 2, 8, 3, 3, 1]`
min = 1, max = 8 → range = 8
Count Array (indexed from 0): `[0, 1, 2, 2, 1, 0, 0, 0, 1]`
Sorted Output: `[1, 2, 2, 3, 3, 4, 8]`

---

## Version 2: Using HashMap (for arbitrary ranges)

```pseudo
countSort(A):
    count = empty hashmap
    for x in A:
        if x not in count:
            count[x] = 0
        count[x] += 1

    index = 0
    // Reconstruct array
    for key in sorted(count.keys()):
        for i = 1 to count[key]:
            A[index] = key
            index += 1
```

**Explanation**: Suitable when elements are sparse or include negatives. Uses sorting on keys of hashmap.

| Case    | Time Complexity | Space |
| ------- | --------------- | ----- |
| Best    | O(n + u log u)  | O(u)  |
| Average | O(n + u log u)  | O(u)  |
| Worst   | O(n + u log u)  | O(u)  |

**Example**:

Input: `[4, 2, 2, 8, 3, 3, 1]`
Hashmap: `{1:1, 2:2, 3:2, 4:1, 8:1}`
Sorted Keys: `[1, 2, 3, 4, 8]`
Reconstructed Output: `[1, 2, 2, 3, 3, 4, 8]`

---