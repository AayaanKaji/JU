#include<stdio.h>
#include<stdlib.h>
#include<math.h>

typedef struct sparsePoly {
    float coeff;
    int exp;
} Term;

float evalPoly(Term p[], int n, float x) {
    float result = 0;
    int i = 0;

    while(i < n) {
        float var = pow(x, p[i].exp);
        var *= p[i].coeff;
        result += var;
        i++;
    }

    return result;
}

int main() {
    int n, i;
    float x;

    printf("Enter size of polynomial:\n");
    scanf("%d", &n);

    Term *p = (Term*)malloc(n * sizeof(Term));

    printf("Polynomial\n");
    for (i = 0; i < n; i++) {
        printf("Enter coefficient and exponent of term %d: ", i + 1);
        scanf("%f %d", &p[i].coeff, &p[i].exp);
    }

    printf("Enter the value of variable x: ");
    scanf("%f", &x);

    float result = evalPoly(p, n, x);

    printf("Evaluated answer is %.2f", result);

    return 0;
}
