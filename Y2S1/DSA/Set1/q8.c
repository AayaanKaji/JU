#include <stdio.h>
#include <stdlib.h>

int main() {
    int n, m, i, j; // size of the matrix
    printf("Enter the sizes(N, M) of the Matrix (N x M):\n");
    scanf("%d %d", &n, &m);

    // check inputted size
    if(n < 1 || m < 1) {// size has to be more than 1 x 1
        printf("\nInvalid Input.\n");
        exit(1);
    }

    // Allocating space for the matrix & initializing each index with zero
    int **Matrix = (int **)malloc(n * sizeof(int *));
    int *non_zero_element = (int *)malloc(n * sizeof(int));

    for(i=0; i<n; i++) {
        Matrix[i] = (int *)calloc(m, sizeof(int));
    }

    // Input only the elements
    printf("\nEnter the elements of the matrix: \n");
    for(i=0; i<n; i++) {
        int count = 0;
        for(j=0; j<m; j++) {
            printf("Matrix[%d][%d]: ", i + 1, j + 1);
            scanf("%d", &Matrix[i][j]);
            if(Matrix[i][j] != 0)
                count++;
        }
        non_zero_element[i] = count;
    }

    printf("Number of non-zero elements in the matrix in each row: \n");
    for(int i=0; i<n; i++) {
        printf("Row %d: %d\n", i+1, non_zero_element[i]);
    }

    /* ~Deallocating Memory~ */
    for(int i=0; i<n; i++) {
        free(Matrix[i]);
    }
}