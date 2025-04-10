#include <stdio.h>
#include <stdlib.h>

// Define the structure of a node
typedef struct LinkedList {
    int data;
    struct LinkedList* next;
} Node;

// Function to insert a node at a specified position
void insertNode(Node** head, int pos, int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = NULL;
    
    if (pos == 1) {
        newNode->next = *head;
        *head = newNode;
        return;
    }

    Node* temp = *head;
    int i;
    for (i = 1; i < pos - 1 && temp != NULL; i++) {
        temp = temp->next;
    }

    if (temp == NULL) {
        printf("Invalid position\n");
        free(newNode);
        return;
    }

    newNode->next = temp->next;
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
        free(temp);
        return;
    }

    for (int i = 1; i < position - 1 && temp != NULL; i++) {
        temp = temp->next;
    }

    if (temp == NULL || temp->next == NULL) {
        printf("Invalid position\n");
        return;
    }

    Node* toDelete = temp->next;
    temp->next = toDelete->next;
    free(toDelete);
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

// Function to print the linked list
void printLL(Node* head) {
    if (head == NULL) {
        return;
    }
    if(head->next != NULL) printf("%d -> ", head->data);

    else printf("%d", head->data);

    printLL(head->next);
}

// Function to reverse print the linked list
void reversePrintLL(Node* head) {
    if (head == NULL) {
        return;
    }
    reversePrintLL(head->next);
    if(head->next != NULL)
        printf(" -> %d", head->data);
    else
        printf("%d", head->data);
}

// Function to reverse the linked list
void reverseLL(Node** head) {
    Node* prev = NULL;
    Node* current = *head;
    Node* next = NULL;
    
    while (current != NULL) {
        next = current->next;
        current->next = prev;
        prev = current;
        current = next;
    }
    
    *head = prev;

    printLL(*head);
}

int main() {
    Node* head = NULL;
    int value, choice, position;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Insert a node at a specified position\n");
        printf("2. Delete a node from a specified position.\n");
        printf("3. Count the number of nodes in the linked list.\n");
        printf("4. print the linked list.\n");
        printf("5. Reverse print the linked list.\n");
        printf("6. Reverse the linked list.\n");
        printf("7. Exit.\n");
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
                    reversePrintLL(head);
                    printf("\n");
                break;
            case 6:
                reverseLL(&head);
                break;
            case 7:
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
