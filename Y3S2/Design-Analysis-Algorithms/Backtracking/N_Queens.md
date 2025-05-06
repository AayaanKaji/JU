```pseudo
function solveNQueens(board, row):
    if row == N:  // All queens are placed successfully
        print(board)  // Solution found
        return true

    for col = 0 to N-1:  // Try placing a queen in every column of the current row
        if isSafe(board, row, col):  // Check if placing a queen at (row, col) is safe
            board[row][col] = 1  // Place the queen
            if solveNQueens(board, row + 1):  // Recurse to place the next queen
                return true
            board[row][col] = 0  // Backtrack: Remove the queen and try next column

    return false  // No valid placement found for this row

function isSafe(board, row, col):
    // Check column for another queen
    for i = 0 to row-1:
        if board[i][col] == 1:
            return false

    // Check upper left diagonal
    for i = 1 to row:
        if col - i >= 0 and board[row - i][col - i] == 1:
            return false

    // Check upper right diagonal
    for i = 1 to row:
        if col + i < N and board[row - i][col + i] == 1:
            return false

    return true  // No conflicts found

```