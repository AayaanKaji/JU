/*
    class "Person"
        data members 'age', 'weight', 'height', 'dateOfBirth', 'address'
        reader/write methods etc
        two subclasses "Employee" and "Student"
    
    Employee
        data member 'annualSalary', 'dateOfJoining', 'experience' etc
        two sub-classes "Technician" and "Professor"

    Student
        data members 'roll', 'listOfSubjects', their marks
        methods 'calculateGrade'

    Professor
        data members 'courses', 'listOfAdvisee'
        add/remove methods
        
    Write a main() function to demonstrate the creation of objects of different classes and their method calls.
*/

package q9;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

class Person {
    private String name; 
    private int age;
    private double weight_in_kg;
    private double height_in_meter;
    private String dateOfBirth;// yyyy-mm-dd
    private String address;

    public static int calculateTimePeriod(String time) {
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.parse(time);
        int years = Period.between(date, now).getYears();
        return years;
    }
    
    public Person(String name, double weight_in_kg, double height_in_meter, String dateOfBirth,
            String address) {
        this.name = name;
        this.age = calculateTimePeriod(dateOfBirth);
        this.weight_in_kg = weight_in_kg;
        this.height_in_meter = height_in_meter;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight_in_kg;
    }

    public void setWeight(double weight) {
        this.weight_in_kg = weight;
    }

    public double getHeight() {
        return height_in_meter;
    }

    public void setHeight(double height) {
        this.height_in_meter = height;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        age = calculateTimePeriod(dateOfBirth);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void displayDetails() {
        System.out.println("Person details:- ");
        System.out.println("Name: " + getName());
        System.out.println("DoB: " + getDateOfBirth());
        System.out.println("Age: " + getAge());
        System.out.println("Height: " + getHeight());
        System.out.println("Weight: " + getWeight());
        System.out.println("Address: " + getAddress());
        System.out.println();
    }
}

class Employee extends Person {
    private int annualSalary;
    private String dateOfJoining;// yyyy-mm-dd
    private int experience;

    public Employee(String name, double weight_in_kg, double height_in_meter, String dateOfBirth, String address,
            int annualSalary, String dateOfJoining) {
        super(name, weight_in_kg, height_in_meter, dateOfBirth, address);
        this.annualSalary = annualSalary;
        this.dateOfJoining = dateOfJoining;
        this.experience = calculateTimePeriod(dateOfJoining);
    }

    public int annualSetSalary() {
        return annualSalary;
    }

    public void annualSetSalary(int annualSalary) {
        this.annualSalary = annualSalary;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
        experience = calculateTimePeriod(dateOfJoining);
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public void displayDetails() {
        System.out.println("Employee details:- ");
        System.out.println("Name: " + getName());
        System.out.println("DoB: " + getDateOfBirth());
        System.out.println("Age: " + getAge());
        System.out.println("Height: " + getHeight());
        System.out.println("Weight: " + getWeight());
        System.out.println("Address: " + getAddress());
        System.out.println("Date of joining: " + getDateOfJoining());
        System.out.println("Years of experience: " + getExperience());
        System.out.println("Annual Salary: " + annualSetSalary());
        System.out.println();
    }
}

class Pair {
    private String subject;
    private int marks;

    public Pair(String subject, int marks) {
        this.subject = subject;
        this.marks = marks;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}

class Student extends Person {
    private String roll;
    private List<Pair> listOfSubjectsAndMarks = new ArrayList<>();

    public Student(String name, double weight_in_kg, double height_in_meter, String dateOfBirth, String address,
            String roll, List<Pair> listOfSubjectsAndMarks) {
        super(name, weight_in_kg, height_in_meter, dateOfBirth, address);
        this.roll = roll;
        this.listOfSubjectsAndMarks = listOfSubjectsAndMarks;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public List<Pair> getListOfSubjectsAndMarks() {
        return listOfSubjectsAndMarks;
    }

    public void setListOfSubjectsAndMarks(List<Pair> listOfSubjectsAndMarks) {
        this.listOfSubjectsAndMarks = listOfSubjectsAndMarks;
    }

    public double calculateGrade() {
        double grade = 0;
        int totalMarks = 0;
        int count = 0;

        for (Pair pair : listOfSubjectsAndMarks) {
            totalMarks += pair.getMarks();
            count++;
        }
        grade = (totalMarks / Math.max(count, 1)) / 10;
        return grade;
    }

    @Override
    public void displayDetails() {
        System.out.println("Student details:- ");
        System.out.println("Name: " + getName());
        System.out.println("DoB: " + getDateOfBirth());
        System.out.println("Age: " + getAge());
        System.out.println("Height: " + getHeight());
        System.out.println("Weight: " + getWeight());
        System.out.println("Address: " + getAddress());
        System.out.println("Roll: " + getRoll());
        System.out.println("Grade: " + calculateGrade());
        System.out.println();
    }
}

class Technician extends Employee {
    public Technician(String name, double weight_in_kg, double height_in_meter, String dateOfBirth, String address,
            int annualSalary, String dateOfJoining) {
        super(name, weight_in_kg, height_in_meter, dateOfBirth, address, annualSalary, dateOfJoining);
    }

    @Override
    public void displayDetails() {
        System.out.println("Technician details:- ");
        System.out.println("Name: " + getName());
        System.out.println("DoB: " + getDateOfBirth());
        System.out.println("Age: " + getAge());
        System.out.println("Height: " + getHeight());
        System.out.println("Weight: " + getWeight());
        System.out.println("Address: " + getAddress());
        System.out.println("Date of joining: " + getDateOfJoining());
        System.out.println("Years of experience: " + getExperience());
        System.out.println("Annual Salary: " + annualSetSalary());
        System.out.println();
    }
}

class Professor extends Employee {
    private List<String> courses = new ArrayList<>();
    private List<String> listOfAdvices = new ArrayList<>();
    
    public Professor(String name, double weight_in_kg, double height_in_meter, String dateOfBirth, String address,
            int annualSalary, String dateOfJoining, List<String> courses, List<String> listOfAdvices) {
        super(name, weight_in_kg, height_in_meter, dateOfBirth, address, annualSalary, dateOfJoining);
        this.courses = courses;
        this.listOfAdvices = listOfAdvices;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public List<String> getListOfAdvices() {
        return listOfAdvices;
    }

    public void setListOfAdvices(List<String> listOfAdvices) {
        this.listOfAdvices = listOfAdvices;
    }

    public void addCourse(String course) {
        if(!courses.contains(course))
            courses.add(course);
    }

    public void removeCourse(String course) {
        if(courses.contains(course))
            courses.remove(course);
    }
}

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Subject-99", 60, 1.70, "2004-12-12", "Block 9, Main Town, Metaverse");
        p1.displayDetails();

        Employee e1 = new Employee("Subject-109", 36, 96, "2016-01-30", "", 5000, "2020-01-01");
        e1.displayDetails();
        
        List<Pair> s1_subject = new ArrayList<>();
        s1_subject.add(new Pair("OOS", 90));
        s1_subject.add(new Pair("CN", 90));
        s1_subject.add(new Pair("SE", 90));
        
        Person s1 = new Student("Subject-115", 68, 1.68, "2005-06-25", "Block 3, Palet Town, Metaverse", "002311000011", s1_subject);
        System.out.println("Student: " + s1.getName() + " Grade: " + ((Student)s1).calculateGrade());
        s1.displayDetails();


    }
}
