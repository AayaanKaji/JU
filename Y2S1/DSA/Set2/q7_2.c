#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node* next;
} Node;

typedef struct {
    Node* front;
    Node* rear;
} Queue;

void initQueue(Queue* queue) {
    queue->front = NULL;
    queue->rear = NULL;
}

void enqueue(Queue* queue, int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = NULL;

    if (queue->rear == NULL) {
        queue->front = newNode;
        queue->rear = newNode;
    } else {
        queue->rear->next = newNode;
        queue->rear = newNode;
    }
}

int dequeue(Queue* queue) {
    if (queue->front == NULL) {
        printf("Underflow: Queue is empty\n");
        return -1;
    }

    int data = queue->front->data;
    Node* temp = queue->front;
    queue->front = queue->front->next;

    if (queue->front == NULL) {
        queue->rear = NULL;
    }

    free(temp);
    return data;
}

int countElements(Queue* queue) {
    int count = 0;
    Node* current = queue->front;
    while (current != NULL) {
        count++;
        current = current->next;
    }
    return count;
}

void displayQueue(Queue* queue) {
    if (queue->front == NULL) {
        printf("Queue is empty.\n");
        return;
    }

    Node* current = queue->front;
    printf("Queue elements: ");
    while (current != NULL) {
        printf("%d ", current->data);
        current = current->next;
    }
    printf("\n");
}

int isEmpty(Queue* queue) {
    return queue->front == NULL;
}

int isFull(Queue* queue) {
    return 0;
}

int main() {
    Queue Queue;
    initQueue(&Queue);

    int choice, element;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Enqueue an element.\n");
        printf("2. Dequeue an element.\n");
        printf("3. Check number of elements.\n");
        printf("4. Check overflow and underflow conditions.\n");
        printf("5. Display queue.\n");
        printf("6. Exit\n");
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
                if (isEmpty(&Queue)) {
                    printf("Underflow: Queue is empty\n");
                } else {
                    printf("The queue is not empty\n");
                }
                break;
            case 5:
                displayQueue(&Queue);
                break;
            case 6:
                printf("Exiting the program.\n");
                return 0;
            default:
                printf("Invalid choice. Please choose again.\n");
        }
    }

    // Free memory for the linked list queue before exiting
    while (!isEmpty(&Queue)) {
        dequeue(&Queue);
    }

    return 0;
}
