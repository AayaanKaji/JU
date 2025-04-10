/*
    implement a class "student" with the following members-
        name, marks obtained in three subjects &
        functions to-
            * assign initial values
            * compute total average
            * display the student's name and the total marks 
*/

package q2;

class Student {
    private String name;
    // marks are out of 100
    private int sub1;
    private int sub2;
    private int sub3;

    public Student() {
        this.name = "Student";
        this.sub1 = 101;
        this.sub2 = 101;
        this.sub3 = 101;
    }

    public Student(String name, int sub1, int sub2, int sub3) {
        this.name = name;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
    }

    public int computeAverageMarks() {
        return (sub1 + sub2 + sub3) / 3;
    }

    public void display() {
        int totalMarks = sub1 + sub2 + sub3;
        System.out.println("Name: " + name + " | Total marks: " + totalMarks);
    }
}

public class Q2 {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.display();
        System.out.println("Average marks: " + s1.computeAverageMarks());

        Student s2 = new Student("Kirito", 36, 79, 53);
        s2.display();
        System.out.println("Average marks: " + s2.computeAverageMarks());
    }
}
