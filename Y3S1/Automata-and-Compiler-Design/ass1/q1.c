#include <stdio.h>
#include <stdlib.h>

#include "header.h"
#include "stack.h"

int bin_varify(char[], int);
int check_counts_equal(char[], int);

int main() {
    char bin_str[SIZE-1];
    int len, count_one, count_zero;

    printf("Enter a binary string: ");
    scanf("%s", bin_str);

    len = str_length(bin_str);

    int check = bin_varify(bin_str, len);
    if(check) {
        printf("Invalid binary number %s.\n", bin_str);
        exit(0);
    }

    count_one = count_chars(bin_str, '1');
    count_zero = count_chars(bin_str, '0');

    printf("Number of 1's: %d\n", count_one);
    printf("Number of 0's: %d\n", count_zero);

    int equal_check = check_counts_equal(bin_str, len);
    if (equal_check) {
        printf("Number of 1's and 0's are equal.\n");
    } else {
        printf("Number of 1's and 0's aren't equal.\n");
    }

    if (count_one%2==0 && count_zero%2==0) {
        printf("Count of 1's and 0's are even.\n");
    } else {
        printf("Count of 1's and 0's aren't even.\n");
    }

    return 0;
}

int bin_varify(char str[], int len) {
    int i;
    for (i = 0; i < len; i++) {
        if ((str[i] - '0' != 1 ) && (str[i] - '0' != 0)) {
            return 1;
        }
    }
    return 0;
}

int check_counts_equal(char str[], int len) {
    if (len == 0) {
        return 0;
    }

    struct Stack *s = createStack();
    int idx = 0;

    // If 0 is encountered, push it onto the stack.
    //If 1 is encountered, pop from the stack.
    char push_char = '0';

    while (idx < len) {
        char ch = str[idx];
        if (ch == push_char) {
            push(s, ch);
        } else {
            if (!isEmpty(s)) {
                pop(s);
            } else {
                // If stack is empty, interchange the rules for 0 & 1
                push_char = (push_char == '0') ? '1' : '0';
            }
        }
        idx++;
    }

    int result = isEmpty(s);
    freeStack(s);
    free(s);
    return result;
}