#include <stdio.h>
#include <stdbool.h>

#define MAX_SIZE 100

typedef struct {
    int elements[MAX_SIZE];
    int front;
    int rear;
} Queue;

void initQueue(Queue* queue) {
    queue->front = -1;
    queue->rear = -1;
}

void enqueue(Queue* queue, int data) {
    if (queue->rear == MAX_SIZE - 1) {
        printf("Overflow: Queue is full\n");
        return;
    }
    
    if (queue->front == -1) {
        queue->front = 0;
    }

    queue->rear++;
    queue->elements[queue->rear] = data;
}

int dequeue(Queue* queue) {
    if (queue->front == -1) {
        printf("Underflow: Queue is empty\n");
        return -1;
    }

    int data = queue->elements[queue->front];
    queue->front++;

    if (queue->front > queue->rear) {
        queue->front = queue->rear = -1;
    }

    return data;
}

bool isEmpty(Queue* queue) {
    return queue->front == -1;
}

bool isFull(Queue* queue) {
    return queue->rear == MAX_SIZE - 1;
}
int countElements(Queue* queue) {
    if (queue->front == -1) {
        return 0;
    }
    return queue->rear - queue->front + 1;
}

void displayQueue(Queue* queue) {
    if (queue->front == -1) {
        printf("Queue is empty.\n");
        return;
    }

    printf("Queue elements: ");
    for (int i = queue->front; i <= queue->rear; i++) {
        printf("%d ", queue->elements[i]);
    }
    printf("\n");
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
        printf("5. Display Queue.\n");
        printf("6. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                if (!isFull(&Queue)) {
                    printf("Enter the element to enqueue: ");
                    scanf("%d", &element);
                    enqueue(&Queue, element);
                } else {
                    printf("Overflow: Queue is full\n");
                }
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
                } else if (isFull(&Queue)) {
                    printf("Overflow: Queue is full\n");
                } else {
                    printf("The queue is not empty or full\n");
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

    return 0;
}