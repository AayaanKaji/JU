#ifndef STACK_H
#define STACK_H

struct Stack {
    int top;
    char* array;
};

struct Stack* createStack();
void push(struct Stack*, char);
char pop(struct Stack*);
int isEmpty(struct Stack*);
void freeStack(struct Stack*);

#endif
