#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct Node {
    int data;
    struct Node* next;
} Node;

typedef struct {
    Node* rear;
} CircularQueue;

void initQueue(CircularQueue* queue) {
    queue->rear = NULL;
}

void enqueue(CircularQueue* queue, int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    
    if (queue->rear == NULL) {
        newNode->next = newNode; // Point to itself
        queue->rear = newNode;
    } else {
        newNode->next = queue->rear->next;
        queue->rear->next = newNode;
        queue->rear = newNode;
    }
}

int dequeue(CircularQueue* queue) {
    if (queue->rear == NULL) {
        printf("Underflow: Queue is empty\n");
        return -1;
    }

    Node* frontNode = queue->rear->next;
    int data = frontNode->data;

    if (frontNode == queue->rear) {
        queue->rear = NULL; // Reset rear when only one element is present
    } else {
        queue->rear->next = frontNode->next;
    }

    free(frontNode);
    return data;
}

bool isEmpty(CircularQueue* queue) {
    return queue->rear == NULL;
}

int countElements(CircularQueue* queue) {
    if (queue->rear == NULL) {
        return 0;
    }

    Node* current = queue->rear->next;
    int count = 0;
    do {
        count++;
        current = current->next;
    } while (current != queue->rear->next);

    return count;
}

void displayQueue(CircularQueue* queue) {
    if (queue->rear == NULL) {
        printf("Circular Queue is empty.\n");
        return;
    }

    Node* current = queue->rear->next;
    printf("Circular Queue elements: ");
    do {
        printf("%d ", current->data);
        current = current->next;
    } while (current != queue->rear->next);
    printf("...");
    printf("\n");
}

int main() {
    CircularQueue Queue;
    initQueue(&Queue);

    int choice, element;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Enqueue an element.\n");
        printf("2. Dequeue an element.\n");
        printf("3. Check number of elements.\n");
        printf("4. Display Queue.\n");
        printf("5. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter the element to enqueue: ");
                scanf("%d", &element);
                enqueue(&Queue, element);
                break;
            case 2:
                element = dequeue(&Queue);
                if (element != -1) {
                    printf("Dequeued element: %d\n", element);
                }
                break;
            case 3:
                printf("Number of elements in the queue: %d\n", countElements(&Queue));
                break;
            case 4:
                displayQueue(&Queue);
                break;
            case 5:
                printf("Exiting the program.\n");
                return 0;
            default:
                printf("Invalid choice. Please choose again.\n");
        }
    }

    while (!isEmpty(&Queue)) {
        dequeue(&Queue);
    }

    return 0;
}