/*
class “Student”
    name, roll no and an array of five subject names
    constructor and get/set methods 

class “TabulationSheet”
    contains roll numbers and marks of each student for a particular subject
    method for adding the marks and roll no of a student

class “MarkSheet”
    contains marks of all subjects for a particular student
    method to add name of a student and marks in each subject

Write a main() function
    create three “Student” objects
    Five “Tabulationsheet” objects for Five subjects
    three “Marksheet” object for three students
    Print the mark sheets
*/

package q12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Student {
    private String name;
    private String rollNo;
    private List<String> subjectNames;

    public Student(String name, String rollNo, List<String> subjectNames) {
        this.name = name;
        this.rollNo = rollNo;
        this.subjectNames = subjectNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public List<String> getSubjectNames() {
        return subjectNames;
    }

    public void setSubjectNames(List<String> subjectNames) {
        this.subjectNames = subjectNames;
    }
}

class TabulationSheet {
    private String subject;
    private Map<String, Integer> rollwiseMark;

    public TabulationSheet(String subject) {
        this.subject = subject;
        this.rollwiseMark = new HashMap<>();
    }

    public TabulationSheet(String subject, Map<String, Integer> rollwiseMark) {
        this.subject = subject;
        this.rollwiseMark = rollwiseMark;
    }

    public String getSubject() {
        return subject;
    }

    public Map<String, Integer> getRollwiseMark() {
        return rollwiseMark;
    }

    public void setRollwiseMark(Map<String, Integer> rollwiseMark) {
        this.rollwiseMark = rollwiseMark;
    }

    public void addInfo(Student s, int mark) {
        if(!rollwiseMark.containsKey(s.getRollNo()))
            rollwiseMark.put(s.getRollNo(), mark);
    }

    public void addInfo(String rollNo, int mark) {
        if(!rollwiseMark.containsKey(rollNo))
            rollwiseMark.put(rollNo, mark);
    }

    public int getStudentMarks(Student s) {
        return rollwiseMark.get(s.getRollNo());
    }
}

class MarkSheet {
    private Student student;
    private Map<String, Integer> subjectWiseMarks;

    MarkSheet(Student student) {
        this.student = student;
        this.subjectWiseMarks = new HashMap<>();
    }

    MarkSheet(Student student, Map<String, Integer> subjectWiseMarks) {
        this.student = student;
        this.subjectWiseMarks = subjectWiseMarks;
    }

    public Student getStudent() {
        return this.student;
    }

    public Integer getMarks(String subject) {
        if(!student.getSubjectNames().contains(subject)) {
            System.out.println("Student not enrolled in this subject.");
            return -1;
        }
        return this.subjectWiseMarks.get(subject);
    }

    public void addMarks(String subject, Integer marks) {
        if(!student.getSubjectNames().contains(subject)) {
            System.out.println("Student not enrolled in this subject.");
            return;
        }
        this.subjectWiseMarks.put(subject, marks);
    }

    public void printDetails() {
        System.out.println("\nMarksheet:- ");
        System.out.println("Student: " + student.getName());
        System.out.println("Roll: " + student.getRollNo());
        for (Map.Entry<String, Integer> entry : subjectWiseMarks.entrySet()) {
            String subject = entry.getKey();
            int mark = entry.getValue();
            System.out.println("Subject: " + subject + ", Mark: " + mark);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        List<String> listOfSubject = List.of("Math", "Physics", "Chemistry", "English", "History");
        int numberOfSubjects = listOfSubject.size();

        Student[] listOfStudents = new Student[3];
        listOfStudents[0] = new Student("Alice", "101", listOfSubject);
        listOfStudents[1] = new Student("Bob", "102", listOfSubject);
        listOfStudents[2] = new Student("Charlie", "103", listOfSubject);
        int numberOfStudents = 3;

        TabulationSheet[] subjectSheets = new TabulationSheet[5];
        for(int i=0; i<numberOfSubjects; i++) {
            subjectSheets[i] = new TabulationSheet(listOfSubject.get(i));
        }

        // adding info into tabulations
        for (TabulationSheet subject : subjectSheets) {
            for (Student student : listOfStudents) {
                subject.addInfo(student, (int)(Math.random() * 99));
            }
        }

        MarkSheet[] markSheet = new MarkSheet[numberOfStudents];
        for(int i=0; i<numberOfStudents; i++) {
            markSheet[i] = new MarkSheet(listOfStudents[i]);
        }

        // adding mark sheet info
        for(MarkSheet student : markSheet) {
            for(TabulationSheet subject : subjectSheets) {
                student.addMarks(subject.getSubject(), subject.getStudentMarks(student.getStudent()));
            }
        }

        for(MarkSheet student : markSheet) {
            student.printDetails();
        }
    }
}

