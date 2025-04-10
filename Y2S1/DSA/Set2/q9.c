#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct Node {
    int data;
    struct Node* next;
} Node;

typedef struct {
    Node* top;
} Stack;

void initStack(Stack* stack) {
    stack->top = NULL;
}

void push(Stack* stack, int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = stack->top;
    stack->top = newNode;
}

int pop(Stack* stack) {
    if (stack->top == NULL) {
        printf("Underflow: Stack is empty\n");
        return -1;
    }
    int data = stack->top->data;
    Node* temp = stack->top;
    stack->top = stack->top->next;
    free(temp);
    return data;
}

bool isStackEmpty(Stack* stack) {
    return stack->top == NULL;
}

typedef struct {
    Stack enqueueStack;
    Stack dequeueStack;
} Queue;

void initQueue(Queue* queue) {
    initStack(&(queue->enqueueStack));
    initStack(&(queue->dequeueStack));
}

void enqueue(Queue* queue, int data) {
    while (!isStackEmpty(&(queue->dequeueStack))) {
        push(&(queue->enqueueStack), pop(&(queue->dequeueStack)));
    }
    push(&(queue->enqueueStack), data);
}

int dequeue(Queue* queue) {
    while (!isStackEmpty(&(queue->enqueueStack))) {
        push(&(queue->dequeueStack), pop(&(queue->enqueueStack)));
    }
    if (isStackEmpty(&(queue->dequeueStack))) {
        printf("Underflow: Queue is empty\n");
        return -1;
    }
    return pop(&(queue->dequeueStack));
}

bool isEmpty(Queue* queue) {
    return isStackEmpty(&(queue->enqueueStack)) && isStackEmpty(&(queue->dequeueStack));
}

int queueSize(Queue* queue) {
    if(isEmpty(queue)) {
        return 0;
    }

    int count = 0;
    while (!isStackEmpty(&(queue->enqueueStack))) {
        push(&(queue->dequeueStack), pop(&(queue->enqueueStack)));
    }
    while (!isStackEmpty(&(queue->dequeueStack))) {
        push(&(queue->enqueueStack), pop(&(queue->dequeueStack)));
        count++;
    }
    return count;
}

void displayQueue(Queue* queue) {
    if(isEmpty(queue)) {
        printf("Queue is empty.\n");
        return;
    }

    while (!isStackEmpty(&(queue->enqueueStack))) {
        push(&(queue->dequeueStack), pop(&(queue->enqueueStack)));
    }
    while (!isStackEmpty(&(queue->dequeueStack))) {
        int data = pop(&(queue->dequeueStack));
        push(&(queue->enqueueStack), data);
        printf("%d ", data);
    }
    printf("\n");
}

int main() {
    Queue queue;
    initQueue(&queue);

    int choice, element;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Enqueue an element.\n");
        printf("2. Dequeue an element.\n");
        printf("3. Display queue.\n");
        printf("4. print queue size.\n");
        printf("5. Exit.\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter the element to enqueue: ");
                scanf("%d", &element);
                enqueue(&queue, element);
                break;
            case 2:
                element = dequeue(&queue);
                if (element != -1) {
                    printf("Dequeued element: %d\n", element);
                }
                break;
            case 3:
                displayQueue(&queue);
                break;
            case 4:
                printf("Size of queue is: %d\n", queueSize(&queue));
                break;
            case 5:
                printf("Exiting the program.\n");
                return 0;
            default:
                printf("Invalid choice. Please choose again.\n");
        }
    }

    return 0;
}
