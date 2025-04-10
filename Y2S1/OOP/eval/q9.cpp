#include <iostream>
using namespace std;

int stringlen(const char* str) {
    int len = 0;
    while (str[len] != '\0') {
        len++;
    }
    return len;
}

void swap1(char* str1, char* str2) {
    int len1 = stringlen(str1);
    int len2 = stringlen(str2);

    char *temp;

    for (int i = 0; i <= len1; i++) {
        temp[i] = str1[i];
    }

    for (int i = 0; i <= len2; i++) {
        str1[i] = str2[i];
    }

    for (int i = 0; i <= len1; i++) {
        str2[i] = temp[i];
    }
}

void swap2(char *(&str1), char *(&str2)) {
    char *temp = str1;
    str1 = str2;
    str2 = temp;
}

int main() {
    char *str1 = "Hello";
    char *str2 = "World!";

    cout << "Before swap:" << endl;
    cout << "String 1: " << str1 << endl;
    cout << "String 2: " << str2 << endl;

    swap1(str1, str2);

    cout << "After first swap:" << endl;
    cout << "String 1: " << str1 << endl;
    cout << "String 2: " << str2 << endl;

    swap2(str1, str2);

    cout << "After second swap:" << endl;
    cout << "String 3: " << str1 << endl;
    cout << "String 4: " << str2 << endl;

    return 0;
}