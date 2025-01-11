/*
class “Date” with member functions for
    (i) getting the previous day
    (ii) getting the next day
    (iii) printing a day
    four constructors:
        (i) day, month and year are initialized to 01, 01, 1970
        (ii) day is  initialized  to  user  specified  value  but  month  and  year  are  initialized  to  01,  1970;
        (iii)  day, month are initialized to user specified value but  year is initialized to 1970
        (iv) day, month and year are initialized to user defined values

write a main() function to
    (i) create a date object
    (ii) print the next and the previous day
*/

// package q11;

import java.time.LocalDate;

class Date {
    private int day;
    private final int DEFAULT_DAY = 1;
    private int month;
    private final int DEFAULT_MONTH = 1;
    private int year;
    private final int DEFAULT_YEAR = 1970;
    private LocalDate date;

    private LocalDate checkIfDate(int year, int month, int day) {
        try {
            LocalDate currentDate = LocalDate.of(year, month, day);
            return currentDate;
        }
        catch(Exception e) {
            System.out.println("Not a date, default date (1970-01-01) used.");
        }
        return LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY);
    }

    public Date() {
        this.day = DEFAULT_DAY;
        this.month = DEFAULT_MONTH;
        this.year = DEFAULT_YEAR;
        date = checkIfDate(year, month, day);
    }

    public Date(int day) {
        this.day = day;
        this.month = DEFAULT_MONTH;
        this.year = DEFAULT_YEAR;
        date = checkIfDate(year, month, day);
    }

    public Date(int day, int month) {
        this.day = day;
        this.month = month;
        this.year = DEFAULT_YEAR;
        date = checkIfDate(year, month, day);
    }
    
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        date = checkIfDate(year, month, day);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void printDay() {
        System.out.println("current date: " + date);
    }

    public LocalDate previousDay() {
        LocalDate prevDay = date.minusDays(1);
        System.out.println("previous date: " + prevDay);
        return prevDay;
    }

    public LocalDate nextDay() {
        LocalDate nextDay = date.plusDays(1);
        System.out.println("next date: " + nextDay);
        return nextDay;
    }
}

public class Main {
    public static void main(String[] args) {
        Date d1 = new Date(41, 3, 2024);

        d1.printDay();
        d1.previousDay();
        d1.nextDay();
    }
}