#include <stdio.h>
#include <stdlib.h>

#include "header.h"
#include "stack.h"

int str_varify(char[], int);// alphabatic string
int check_counts_equal(char[], int, char, char);

int main() {
    char str[SIZE-1];
    char char1 = 'a', char2 = 'b';
    int len, count_char1, count_char2;

    printf("Enter a alphabatic string: ");
    scanf("%s", str);

    len = str_length(str);

    int check = str_varify(str, len);
    if(check) {
        printf("Invalid string %s. Enter a alphabatic string.\n", str);
        exit(0);
    }

    count_char1 = count_chars(str, char1);
    count_char2 = count_chars(str, char2);

    printf("Number of %c's: %d\n", char1, count_char1);
    printf("Number of %c's: %d\n", char2, count_char2);

    int equal_check = check_counts_equal(str, len, char1, char2);
    if (equal_check) {
        printf("Number of %c's and %c's are equal.\n", char1, char2);
    } else {
        printf("Number of %c's and %c's aren't equal.\n", char1, char2);
    }

    if (count_char1%2==0 && count_char2%2==0) {
        printf("Count of %c's and %c's are even.\n", char1, char2);
    } else {
        printf("Count of %c's and %c's aren't even.\n", char1, char2);
    }

    return 0;
}

int str_varify(char str[], int len) {
    int i;
    for (i = 0; i < len; i++)
        if ((str[i] - 'a' < 0 ) || (str[i] - 'a' > 25))
            return 1;
    return 0;
}

int check_counts_equal(char str[], int len, char char1, char char2) {
    if (len == 0) {
        return 0;
    }

    struct Stack *s = createStack();
    int idx = 0;

    // If char1 is encountered, push it onto the stack.
    //If char2 is encountered, pop from the stack.
    char push_char = char1, pop_char = char2;

    while (idx < len) {
        char ch = str[idx];
        if (ch == push_char) {
            push(s, ch);
        } else if (ch == pop_char) {
            if (!isEmpty(s)) {
                pop(s);
            } else {
                // If stack is empty, interchange char 1 & 2
                char temp = push_char;
                push_char = pop_char;
                pop_char = temp;
                push(s, ch);
            }
        }
        idx++;
    }

    int result = isEmpty(s);
    freeStack(s);
    free(s);
    return result;
}