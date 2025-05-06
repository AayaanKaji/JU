# Bucket Sort

```pseudo
bucketSort(A):
    create k empty buckets
    for each element in A:
        insert into appropriate bucket
    sort each bucket individually using some soring algo (insertion sort)
    concatenate all buckets
```

**Explanation**: Distribute into buckets, sort each, then concatenate. Works best for uniformly distributed data.

| Case    | Time Complexity | Space    |
| ------- | --------------- | -------- |
| Best    | O(n)            | O(n)     |
| Average | O(n + k)        | O(n + k) |
| Worst   | O(n²)           | O(n)     |

## Example:

Input: `[0.897, 0.565, 0.67, 0.1234, 0.665, 0.3434, 0.1125]`

* Buckets: `[[0.1234, 0.1125], [0.3434], [0.565], [0.67, 0.665], [0.897]]`
* After sorting: `[[0.1125, 0.1234], [0.3434], [0.565], [0.665, 0.67], [0.897]]`
* After merging: `[0.1125, 0.1234, 0.3434, 0.565, 0.665, 0.67, 0.897]`

Sorted: `[0.1125, 0.1234, 0.3434, 0.565, 0.665, 0.67, 0.897]`

---