#include <iostream>
#include <cstring>

using namespace std;

class Person {
private:
    char* name;
    int age;
    double height_cm;

public:
    Person(const char* name = "John Doe", int age = 36, double height = 168): age(age), height_cm(height) {
      this->name = strdup(name);
    }

    ~Person() {
      delete[] name;
    }

    const char* getName() const {
      return name;
    }

    int getAge() const {
      return age;
    }

    double getHeight() const {
      return height_cm;
    }

    void setName(const char* newName) {
      name = strdup(newName);
    }

    void setAge(int newAge) {
      age = newAge;
    }

    void setHeight(double newHeight) {
      height_cm = newHeight;
    }

    void printDetails() const {
        cout << "Name: " << name << ", Age: " << age << ", Height: " << height_cm << " cm" << endl;
    }
};

class Student : public Person {
private:
    int roll;
    int yearOfAdmission;

public:
    Student(const char* name = "John", int age = 18, double height = 168,
            int roll = 0, int yearOfAdmission = 2022)
        : Person(name, age, height), roll(roll), yearOfAdmission(yearOfAdmission) {}

    int getRoll() const {
        return roll;
    }

    int getYearOfAdmission() const {
        return yearOfAdmission;
    }

    void setRoll(int newRoll) {
        roll = newRoll;
    }

    void setYearOfAdmission(int newYear) {
        yearOfAdmission = newYear;
    }

    void printDetails() const {
        Person::printDetails();
        cout << "Roll: " << roll << ", Year of Admission: " << yearOfAdmission << endl;
    }
};

int main() {
    Person person("Ajak", 25, 170);
    cout << "Person details: " << endl; 
    person.printDetails();

    Student student("Boris", 20, 175, 12, 2023);
    cout << "Student details: " << endl;
    student.printDetails();

    return 0;
}
