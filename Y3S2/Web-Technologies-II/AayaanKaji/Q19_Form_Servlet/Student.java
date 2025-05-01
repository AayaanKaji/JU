package Q19_Form_Servlet;

public class Student {
    private int roll_number;
    private String student_name;
    private String dept_name;

    public Student(int roll_number, String student_name, String dept_name) {
        this.roll_number = roll_number;
        this.student_name = student_name;
        this.dept_name = dept_name;
    }

    public int getRollNumber() {
        return roll_number;
    }

    public String getStudentName() {
        return student_name;
    }

    public String getDeptName() {
        return dept_name;
    }
}
