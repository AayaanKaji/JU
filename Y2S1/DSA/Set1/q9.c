// /* ~Function to print 1D, 2D version of a Tri-Diagonal Matrix~ */

// #include <stdio.h>
// #include <stdlib.h>

// int main() {
//     int row, col, i, j, len; // size of the matrix
//     printf("Enter the size(N) of a One Dimensional Matrix:\n");
//     scanf("%d", &len);

//     // check inputted size
//     if(len < 1) {// size has to be more than 1 x 1
//         printf("\nInvalid Input.\n");
//         exit(1);
//     }

//     int *oneD = (int *)malloc(len * sizeof(int));
//     int **triMatrix = (int **)malloc(size * sizeof(int *));
//     for(int i=0; i<size; i++) {
//         triMatrix[i] = (int *)calloc(size, sizeof(int));
//     }

//     printf("\nEnter the elements of the matrix: \n");
//     for(int i=0; i<size; i++) {
//         printf("Matrix[%d]: ", i+1);
//         scanf("%d", &oneD[i]);
//     }

//     // Print 1D Matrix
//     printf("\n1D version: \n");
//     for(int i=0; i<size*size; i++) {
//         printf("%d ", oneD[i]);
//     }
//     printf("\n");

//     printf("Enter row(N) & column(M) length of 2D matrix, such that (N x M = %d: Length of the 1D matrix): \n", len);
//     scanf("%d %d", &row, &col);

//     /* ~Allocating memory to store the 2D version of the matrix from the 1D~ */
//     int **twoD = (int **)malloc(row * sizeof(int *));
//     for(int i=0; i<row; i++) {
//         twoD[i] = (int *)calloc(col, sizeof(int));
//     }

//     // Storing the elements
//     for(int i=0; i<len; i++) {
//         int row = i / len;
//         int col = i % len;
//         twoD[row][col] = oneD[i];
//     }

//     // Print 2D Matrix
//     printf("\n2D Version: \n");
//     for(int i=0; i<size; i++) {
//         for(int j=0; j<size; j++) {
//             // adjusting space
//             if(abs(twoD[i][j]) < 10) {
//                 printf(" ");
//             }//
//             printf("%d ", twoD[i][j]);
//         }
//         printf("\n");
//     }

//     /* ~Deallocating Memory~ */
//     // (1) triMatrix & twoD
//     for(int i=0; i<size; i++) {
//         free(triMatrix[i]);
//         free(twoD[i]);
//     }
//     // (2) oneD
//     free(oneD);
// }