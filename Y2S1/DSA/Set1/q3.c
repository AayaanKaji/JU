#include<stdio.h>
#include<stdlib.h>

typedef struct sparsePoly {
    float coeff;
    int exp;
} Term;

Term* addPoly(Term p1[], int n1, Term p2[], int n2, int* result_size) {
    Term* result = (Term*)malloc((n1 + n2) * sizeof(Term));

    int i, j, k =0;
    while (i < n1 && j < n2) {
        if (p1[i].exp > p2[j].exp) {
            result[k++] = p1[i++];
        } else if (p1[i].exp < p2[j].exp) {
            result[k++] = p2[j++];
        } else {
            float sum_coeff = p1[i].coeff + p2[j].coeff;
            if (sum_coeff != 0) {
                result[k].coeff = sum_coeff;
                result[k].exp = p1[i].exp;
                k++;
            }
            i++;
            j++;
        }
    }
    
    while (i < n1) {
        result[k++] = p1[i++];
    }
    
    // Copy remaining terms from poly2
    while (j < n2) {
        result[k++] = p2[j++];
    }
    
    *result_size = k;
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

    Term* result = addPoly(p1, n1, p2, n2, &result_size);

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
