#include <stdio.h>

#include "header.h"

int str_length(char str[]) {
    int len = 0;
    while (str[len] != '\0') {
        len++;
    }
    return len;
}

int count_chars(char str[], char ch) {
    int i, count = 0;
    for (i = 0; str[i] != '\0'; i++) {
        if (str[i] == ch) {
            count++;
        }
    }
    return count;
}