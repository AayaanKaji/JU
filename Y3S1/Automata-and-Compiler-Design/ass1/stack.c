#include <stdio.h>
#include <stdlib.h>

#include "header.h"
#include "stack.h"

struct Stack* createStack() {
    struct Stack *s = (struct Stack*)malloc(sizeof(struct Stack));
    s->top = -1;
    s->array = (char*)malloc(SIZE * sizeof(char));
    return s;
}

void push(struct Stack *s, char data) {
    if (s->top == SIZE - 1) {
        printf("\nOverflow!\n");
    }
    s->top++;
    s->array[s->top] = data;
}

char pop(struct Stack *s) {
    if (s->top == -1) {
        printf("\nUnderflow!\n");
        return '*';
    }
    return s->array[s->top--];
}

int isEmpty(struct Stack *s) {
    return s->top == -1;
}

void freeStack(struct Stack* s) {
    free(s->array);
}