#include <iostream>
using namespace std;

void swap1(char **str1, char **str2) {
    char *temp = *str1;
    *str1 = *str2;
    *str2 = temp;
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

    swap1(&str1, &str2);

    cout << "After first swap:" << endl;
    cout << "String 1: " << str1 << endl;
    cout << "String 2: " << str2 << endl;

    swap2(str1, str2);

    cout << "After second swap:" << endl;
    cout << "String 3: " << str1 << endl;
    cout << "String 4: " << str2 << endl;

    return 0;
}
