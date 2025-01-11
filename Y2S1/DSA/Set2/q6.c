#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdbool.h>

#define MAX_SIZE 100

// Structure for a stack
typedef struct {
    int elements[MAX_SIZE];
    int top;
} Stack;

// Initialize a new stack
void initStack(Stack* stack) {
    stack->top = -1;
}

// Push an element onto the stack
void push(Stack* stack, int data) {
    if (stack->top == MAX_SIZE - 1) {
        printf("Overflow: Stack is full\n");
        exit(1);
    }
    stack->elements[++stack->top] = data;
}

// Pop an element from the stack
int pop(Stack* stack) {
    if (stack->top == -1) {
        printf("Underflow: Stack is empty\n");
        exit(1);
    }
    return stack->elements[stack->top--];
}

// Check if the stack is empty
bool isStackEmpty(Stack* stack) {
    return stack->top == -1;
}

// Evaluate a postfix expression
int evaluatePostfix(char* expression) {
    Stack stack;
    initStack(&stack);

    for (int i = 0; expression[i] != '\0'; i++) {
        if (isdigit(expression[i])) {
            push(&stack, expression[i] - '0');
        } else if (expression[i] == '+' || expression[i] == '-' || expression[i] == '*' || expression[i] == '/') {
            int operand2 = pop(&stack);
            int operand1 = pop(&stack);

            switch (expression[i]) {
                case '+':
                    push(&stack, operand1 + operand2);
                    break;
                case '-':
                    push(&stack, operand1 - operand2);
                    break;
                case '*':
                    push(&stack, operand1 * operand2);
                    break;
                case '/':
                    if (operand2 == 0) {
                        printf("Error: Division by zero\n");
                        exit(1);
                    }
                    push(&stack, operand1 / operand2);
                    break;
            }
        }
    }

    if (isStackEmpty(&stack) || !isStackEmpty(&stack) && stack.top != 0) {
        printf("Error: Invalid expression\n");
        exit(1);
    }

    return stack.elements[0];
}

int main() {
    char expression[MAX_SIZE];
    
    printf("Enter a postfix expression: ");
    scanf("%s", expression);

    int result = evaluatePostfix(expression);
    printf("Result: %d\n", result);

    return 0;
}
