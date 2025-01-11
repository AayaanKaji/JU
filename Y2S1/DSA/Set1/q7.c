#include <stdio.h>
#include <stdlib.h>

int main() {
    int n, m, i, j; // size of the matrix
    printf("Enter the sizes(N, M) of the two Matrixes (N x M):\n");
    scanf("%d %d", &n, &m);

    // check inputted size
    if(n < 1 || m < 1) {// size has to be more than 1 x 1
        printf("\nInvalid Input.\n");
        exit(1);
    }

    // Allocating space for the matrix & initializing each index with zero
    int **Matrix1 = (int **)malloc(n * sizeof(int *));
    int **Matrix2 = (int **)malloc(n * sizeof(int *));
    for(i=0; i<n; i++) {
        Matrix1[i] = (int *)calloc(m, sizeof(int));
        Matrix2[i] = (int *)calloc(m, sizeof(int));
    }

    // Input only the elements
    printf("\nEnter the elements of the first matrix: \n");
    for(i=0; i<n; i++) {
        for(j=0; j<m; j++) {
            printf("Matrix1[%d][%d]: ", i + 1, j + 1);
            scanf("%d", &Matrix1[i][j]);
        }
    }

    printf("\nEnter the elements of the second matrix: \n");
    for(i=0; i<n; i++) {
        for(j=0; j<m; j++) {
            printf("Matrix2[%d][%d]: ", i + 1, j + 1);
            scanf("%d", &Matrix2[i][j]);
        }
    }

    int **result = (int **)malloc(n * sizeof(int *));
    for(int i=0; i<n; i++) {
        result[i] = (int *)calloc(m, sizeof(int));
    }

    for(i=0; i<n; i++) {
        for(j=0; j<m; j++) {
            result[i][j] = Matrix1[i][j] + Matrix2[i][j];
        }
    }

    printf("\nResult: \n");
    for(int i=0; i<n; i++) {
        for(int j=0; j<m; j++) {
            // adjusting space
            if(abs(result[i][j]) < 10) {
                printf(" ");
            }//
            printf("%d ", result[i][j]);
        }
        printf("\n");
    }

    /* ~Deallocating Memory~ */
    for(int i=0; i<n; i++) {
        free(Matrix1[i]);
        free(Matrix2[i]);
        free(result[i]);
    }
}