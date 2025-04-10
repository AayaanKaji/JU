/*
    class "Employee"
        name, id, address, salary etc.
        Write necessary constructor and read/write methods
    class "Dept"
        name, location etc
        The "Dept" contains a number of "Employee"
        Write methods "add" and "remove" to add and remove an employee to/from this department.
    Write a main() function and create "Information Technology" department.
    Add five employees and print yearly expenditure for this department.
*/

package q6;

import java.util.HashSet;
import java.util.Set;

class Employee {
    private String name;
    private int id;
    private String address;
    private int salary;

    public Employee(String name, int id, String address, int salary) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }   
}

class Dept {
    private String name;
    private String location;
    private Set<Employee> listOfEmployee;
    
    public Dept(String name, String location) {
        this.name = name;
        this.location = location;
        listOfEmployee = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void add(Employee e) {
        if(listOfEmployee.contains(e)) {
            System.out.println("Already part of department.");
            return;
        }
        listOfEmployee.add(e);
        System.out.println("Employee added to " + this.name + " department.");    
    }

    public void remove(Employee e) {
        if(!listOfEmployee.contains(e)) {
            System.out.println("Employee not a part of department.");
            return;
        }
        listOfEmployee.remove(e);
        System.out.println("Employee removed from " + this.name + " department.");    
    }

    public long yearlyExpenditure() {
        long totalMonthlyExpens = 0;
        for (Employee employee : listOfEmployee) {
            totalMonthlyExpens += employee.getSalary();
        }
        return totalMonthlyExpens * 12;
    }
}

public class Main {
    public static void main(String[] args) {
        Dept d1 = new Dept("Information Technology", "JUSL");

        d1.add(new Employee("John Doe", 1, "123 Main St", 4000));
        d1.add(new Employee("Jane Smith", 2, "456 Oak St", 5000));
        d1.add(new Employee("Bob Johnson", 3, "789 Pine St", 4500));
        d1.add(new Employee("Alice Brown", 4, "101 Maple St", 6000));
        d1.add(new Employee("Charlie Davis", 5, "202 Cedar St", 5500));

        System.out.println("Yearly Expenditure for " + d1.getName() + ": $" + d1.yearlyExpenditure());
    }
}
