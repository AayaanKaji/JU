// Function to add two Big Numbers, irrespective of their sign (+ve or, -ve)

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <stdbool.h>

#define max_number_to_be_added 1000
// Max digit numbers that can be added using this program

#define max_digits_in_node 8
// Maximum number of digits, one would like to store in each node of the LinkedList
// Do mind that it shouldn't exceed [('int' data-type's capacity) - 1] in the system architecture

// LinkedList to store the big numbers
typedef struct LinkedList {
    int data;
    struct LinkedList* next;
} Node;

Node* create_node(int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

void insert_node_at_end(Node** head, int data) {
    Node* newNode = create_node(data);

    if(!(*head)) {
        *head = newNode;
    }else {
        Node* curr = *head;
        while(curr->next) {
            curr = curr->next;
        }
        curr->next = newNode;
    }
}

// free's memory
void free_list(Node* head) {
    Node* curr = head;
    while (curr) {
        Node* temp = curr;
        curr = curr->next;
        free(temp);
    }
}

// checks whether the leading nodes hold any value or not
bool node_val_zero(Node* head) {
    if(head == NULL) {
        return true;
    } 

    if(head->data != 0) {
        return false;
    }

    return node_val_zero(head->next);
}

void print_list_reverse(Node* head) {
    if(head == NULL || node_val_zero(head)) {
        return;
    }

    // calls its next node then prints current node's value
    print_list_reverse(head->next);

    // Calculate the number of leading zeros required
    int digits = max_digits_in_node;
    int tempData = abs(head->data);
    while (tempData / 10 > 0) {
        digits--;
        tempData /= 10;
    }

    // Print the significant zeros only
    if(head->next && !node_val_zero(head->next)) {
        for (int i = 1; i < digits; i++) {
            printf("0");
        }
    }
    printf("%d", (head->data));
}

void print_list(Node* head) {
    // in case the result is empty, this will print '0'
    if(node_val_zero(head)) {
        printf("0");
    }
    // call a function to print the LL in reverse order
    else {
        print_list_reverse(head);
    }
}

int check_sign(char* n) {
    // assuming these to be +ve
    int sign = 1;

    if(n[0] == '-') {
        sign = -1;
    }

    return sign;
}

void adjust_sign(Node* result, int sign) {
    Node* curr = result;
    Node* make_positive = result;
    int carry = 0;

    while(curr) {
        curr->data += carry;
        int curr_sign = (curr->data < 0) ? -1 : 1;

        if(sign != curr_sign && curr->data != 0) {
            curr->data += sign * (int)pow(10, max_digits_in_node);
            carry = -1 * sign; 
        }
        else {
            carry = 0;
        }
        curr = curr->next;
    }

    while(!node_val_zero(make_positive->next)) {
        make_positive->data = abs(make_positive->data);
        make_positive = make_positive->next;
    }
}

Node* add_lists(Node* l1, Node* l2) {
    Node* result_head = NULL;
    Node* result_tail = NULL;
    int carry = 0, sign = 1;

    while (l1 || l2 || carry) {
        int sum = carry;
        if (l1) {
            sum += l1->data;
            l1 = l1->next;
        }
        if (l2) {
            sum += l2->data;
            l2 = l2->next;
        }
        
        carry = sum / (int)pow(10, max_digits_in_node);
        int data = (sum) % (int)pow(10, max_digits_in_node);

        if(data < 0) sign = -1;
        if(data > 0) sign = 1;

        Node* newNode = create_node(data);

        if (result_head == NULL) {
            result_head = result_tail = newNode;
        } else {
            result_tail->next = newNode;
            result_tail = newNode;
        }
    }

    adjust_sign(result_head, sign);

    return result_head;
}

Node* add_numbers(char* n1, char* n2) {
    Node* l1 = NULL;
    Node* l2 = NULL;
    Node* result = NULL;

    int sign1 = check_sign(n1);
    int sign2 = check_sign(n2);

    char* ptr1 = n1 + strlen(n1) - 1;
    char* ptr2 = n2 + strlen(n2) - 1;

    if(sign1 == -1) n1++;
    if(sign2 == -1) n2++;

    int i, data;

    // adding the 1st number in linked-list l1
    while(ptr1 >= n1) {
        data = 0;
        int count = 0;
        while(count < max_digits_in_node && ptr1 >= n1) {
            data += (*ptr1 - '0') * (int)pow(10, count);
            count++;
            ptr1--;
        }
        data *= sign1;
        insert_node_at_end(&l1, data);
    }
    // adding the 2nd number in linked-list l2
    while(ptr2 >= n2) {
        data = 0;
        int count = 0;
        while(count < max_digits_in_node && ptr2 >= n2) {
            data += (*ptr2 - '0') * (int)pow(10, count);
            count++;
            ptr2--;
        }
        data *= sign2;
        insert_node_at_end(&l2, data);
    }

    // adding the lists
    result = add_lists(l1, l2);
    
    // free memory
    free_list(l1);
    free_list(l2);

    return result;
}

int main() {
    char n1[max_number_to_be_added+1], n2[max_number_to_be_added+1];

    printf("Enter 1st big-number: \n");
    scanf("%s", n1);

    printf("Enter 2nd big-number: \n");
    scanf("%s", n2);

    Node* result = add_numbers(n1, n2);
    
    printf("Sum of the 2 numbers is: \n");
    print_list(result);

    // free the memory
    free_list(result);

    return 0;
}