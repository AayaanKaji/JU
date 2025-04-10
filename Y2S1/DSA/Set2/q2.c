#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node* next;
};

struct Node* createNode(int data) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

struct Node* createCircularLinkedList(int n) {
    if (n <= 0) {
        return NULL;
    }
    
    struct Node* head = createNode(1);
    struct Node* current = head;
    
    for (int i = 2; i <= n; i++) {
        current->next = createNode(i);
        current = current->next;
    }
    
    current->next = head; // Make it circular
    return head;
}

void josephus(struct Node** head, int k) {
    if (*head == NULL || k <= 0) {
        printf("Invalid input!\n");
        return;
    }
    
    struct Node* current = *head;
    struct Node* prev = NULL;
    
    while (current->next != current) {
        // Move 'k-1' steps
        int i;
        for (i = 1; i < k; i++) {
            prev = current;
            current = current->next;
        }
        
        // Eliminate the current node
        prev->next = current->next;
        free(current);
        current = prev->next;
    }
    
    *head = current; // Last remaining node
}

int main() {
    int n, k;
    printf("Enter the number of people: ");
    scanf("%d", &n);
    printf("Enter the elimination step: ");
    scanf("%d", &k);
    
    struct Node* head = createCircularLinkedList(n);
    
    printf("Last survivor:\n");
    josephus(&head, k);

    // Print the last remaining person
    printf("%d\n", head->data);

    free(head);
    
    return 0;
}
