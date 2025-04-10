#include<stdio.h>
#include<stdlib.h>

typedef struct sparsePoly {
    float coeff;
    int exp;
} Term;

void sortResult(Term result[], int size) {
    for (int i = 0; i < size - 1; i++) {
        for (int j = 0; j < size - i - 1; j++) {
            if (result[j].exp < result[j + 1].exp) {
                Term temp = result[j];
                result[j] = result[j + 1];
                result[j + 1] = temp;
            }
        }
    }
}

Term* mulPoly(Term p1[], int n1, Term p2[], int n2, int* result_size) {
    Term* result = (Term*)malloc((n1 * n2) * sizeof(Term));

    int i, j, k = 0;
    for (i = 0; i < n1; i++) {
        for (j = 0; j < n2; j++) {
            result[k].coeff = p1[i].coeff * p2[j].coeff;
            result[k].exp = p1[i].exp + p2[j].exp;
            k++;
        }
    }

    for (i = 0; i < k - 1; i++) {
        for (j = i + 1; j < k; j++) {
            if (result[i].exp == result[j].exp) {
                result[i].coeff += result[j].coeff;
                result[j].exp = -1;
            }
        }
    }

    int newSize = 0;
    for (int i = 0; i < k; i++) {
        if (result[i].exp != -1) {
            result[newSize++] = result[i];
        }
    }

    *result_size = newSize;
    sortResult(result, newSize);
    
    return result;
}

int main() {
    int n1, n2, result_size, i;
    printf("Enter sizes of 1st & 2nd polynomials:\n");
    scanf("%d %d", &n1, &n2);

    Term *p1 = (Term*)malloc(n1 * sizeof(Term));
    Term *p2 = (Term*)malloc(n1 * sizeof(Term));

    printf("Polynomial 1\n");
    for (i = 0; i < n1; i++) {
        printf("Enter coefficient and exponent of term %d: ", i + 1);
        scanf("%f %d", &p1[i].coeff, &p1[i].exp);
    }

    printf("Polynomial 2\n");
    for (i = 0; i < n2; i++) {
        printf("Enter coefficient and exponent of term %d: ", i + 1);
        scanf("%f %d", &p2[i].coeff, &p2[i].exp);
    }

    Term* result = mulPoly(p1, n1, p2, n2, &result_size);

    printf("Result: \n");
    for(int i=0; i<result_size; i++) {
        printf("(%.2f)", result[i].coeff);
        if(result[i].exp != 0)
            printf("*X^%d", result[i].exp);
        if(i < result_size-1)
            printf(" + ");
    }

    return 0;
}
