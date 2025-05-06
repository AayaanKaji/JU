```pseudo
function knapsackBranchAndBound(items, capacity):
    // Sort items by value/weight ratio in descending order
    items = sortByValuePerWeight(items)

    bestSolution = Solution(0, 0)  // Initialize best solution (value, weight)
    Q = priorityQueue()  // Priority queue (to explore items)
    
    // Add initial state (no items, zero capacity)
    Q.push(Node(0, 0, 0, 0))  // Node(index, total value, total weight, bound)

    while not Q.isEmpty():
        node = Q.pop()

        if node.bound <= bestSolution.value:  // Pruning: if bound is worse than best solution, skip
            continue

        if node.index == len(items):  // If all items have been considered
            continue

        // Explore left branch (take item)
        if node.totalWeight + items[node.index].weight <= capacity:
            leftNode = Node(node.index + 1, node.totalValue + items[node.index].value,
                            node.totalWeight + items[node.index].weight, calculateBound(node))
            if leftNode.totalValue > bestSolution.value:
                bestSolution = leftNode.totalValue
            Q.push(leftNode)

        // Explore right branch (do not take item)
        rightNode = Node(node.index + 1, node.totalValue, node.totalWeight, calculateBound(node))
        Q.push(rightNode)

    return bestSolution.value

function calculateBound(node):
    // Compute the upper bound for the current node
    remainingCapacity = capacity - node.totalWeight
    bound = node.totalValue

    // Add value of the fractional part of the remaining items
    for i = node.index to len(items)-1:
        if items[i].weight <= remainingCapacity:
            bound += items[i].value
            remainingCapacity -= items[i].weight
        else:
            bound += (remainingCapacity / items[i].weight) * items[i].value
            break

    return bound  // Return the upper bound

```