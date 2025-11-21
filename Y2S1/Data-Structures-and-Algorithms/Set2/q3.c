#include <stdio.h>
#include <stdlib.h>

typedef struct doublyLL {
    int data;
    struct doublyLL* prev;
    struct doublyLL* next;
} Node;

Node* head = NULL;
Node* tail = NULL;

// Function to create a new node
Node* createNode(int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

// Function to insert a node at a specified position
void insertNode(Node** head, int position, int data) {
    Node* newNode = createNode(data);
    
    if (*head == NULL) {
        *head = newNode;
        tail = newNode; 
        return;
    }

    if (position == 1) {
        newNode->next = *head;
        (*head)->prev = newNode;
        *head = newNode;
        return;
    }

    Node* temp = *head;
    for (int i = 1; i < position - 1 && temp != NULL; i++) {
        temp = temp->next;
    }

    if (temp == NULL) {
        printf("Invalid position\n");
        free(newNode);
        return;
    }

    newNode->prev = temp;
    newNode->next = temp->next;

    if (temp->next != NULL) {
        temp->next->prev = newNode;
    }else {
        tail = newNode;
    }

    temp->next = newNode;
}

// Function to delete a node from a specified position
void deleteNode(Node** head, int position) {
    if (*head == NULL) {
        printf("List is empty\n");
        return;
    }

    Node* temp = *head;

    if (position == 1) {
        *head = temp->next;
        if (*head != NULL) {
            (*head)->prev = NULL;
        }
        free(temp);
        return;
    }

    int i;
    for (i = 1; i < position && temp != NULL; i++) {
        temp = temp->next;
    }

    if (temp == NULL) {
        printf("Invalid position\n");
        return;
    }

    if (temp->prev != NULL) {
        temp->prev->next = temp->next;
    }

    if (temp->next != NULL) {
        temp->next->prev = temp->prev;
    }

    free(temp);
}

// Function to count the number of nodes in the linked list
int countNodes(Node* head) {
    int count = 0;
    while (head != NULL) {
        count++;
        head = head->next;
    }
    return count;
}

void printLL(Node* head) {
    Node* curr = head;
    if(curr == NULL) {
        printf("Doubly Linked List is empty.\n");
        return;
    }
    while (curr != NULL) {
        if(curr->next != NULL)
            printf("%d <-> ", curr->data);
        else
            printf("%d", curr->data);
        curr = curr->next;
    }
    free(curr);
    printf("\n");
}

// Function to reverse print the linked list
void reversePrintLL(Node* tail) {
    Node* curr = tail;
    if(curr == NULL) {
        printf("Doubly Linked List is empty.\n");
        return;
    }
    while (curr != NULL) {
        if(curr->prev != NULL)
            printf("%d <-> ", curr->data);
        else
            printf("%d", curr->data);
        curr = curr->prev;
    }
    free(curr);
    printf("\n");
}

// Function to reverse the linked list
/*
void reverseLL(Node** head, Node** tail) {
    if(*head == NULL || (*head)->next == NULL) {
        printLL(*head);
        return;
    }

    Node* temp = *tail;
    *tail = *head;
    *head = temp;
    
    Node* current = *head;
    while (current != NULL) {
        temp = current->prev;
        current->prev = current->next;
        current->next = temp;
        current = current->prev;
    }

    free(current);
    free(temp);

    printLL(*head);
}
*/

int main() {
    int value, choice, position;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Insert a node at a specified position\n");
        printf("2. Delete a node from a specified position.\n");
        printf("3. Count the number of nodes in the doubly linked list.\n");
        printf("4. print the doubly linked list.\n");
        printf("5. Reverse print the doubly linked list.\n");
        printf("6. Exit.\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("\nEnter the position of node (1 - %d): ", countNodes(head) + 1);
                scanf("%d", &position);
                printf("Enter the value of node to be added: ");
                scanf("%d", &value);
                insertNode(&head, position, value);
                break;
            case 2:
                printf("\nEnter the position of node (1 - %d): ", countNodes(head));
                scanf("%d", &position);
                deleteNode(&head, position);
                break;
            case 3:
                printf("Number of nodes: %d\n", countNodes(head));
                break;
            case 4:
                if(head == NULL)
                    printf("Linked List is empty.\n");
                else
                    printLL(head);
                    printf("\n");
                break;
            case 5:
                if(head == NULL)
                    printf("Linked List is empty.\n");
                else
                    reversePrintLL(tail);
                    printf("\n");
                break;
            case 6:
                printf("Exiting the program.\n");
                return 0;
            default:
                printf("Invalid choice. Please choose again.\n");
        }
    }

    Node* current = head;
    while (current != NULL) {
        Node* temp = current;
        current = current->next;
        free(temp);
    }

    return 0;
}
