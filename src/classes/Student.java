/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import static classes.Method.formatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 2/1/2020
 * @author George.Giazitzis
 * @version 1.1
 */
public class Student {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int tuitionFees;
    private List<Assignment> listOfStudentAssignments = new ArrayList();
    private static List<Student> listOfAllStudents = new ArrayList();
    private static int count = 1000;
    public final int ID;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, int tuitionFees) {
        count++;
        this.ID = count;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
    }

    //Constructor with user input.
    private Student() {
        count++;
        this.ID = count;
        setFirstName();
        setLastName();
        setDateOfBirth();
        this.tuitionFees = 0;
    }

    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s$%-15s#%-15s", firstName, lastName, dateOfBirth.format(formatter), tuitionFees, ID);
    }

    //Getters & Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(int tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     *
     * @param dateOfBirth Insert a valid date in the format of
     * LocalDate.of(YYYY, MM, DD)
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Assignment> getListOfStudentAssignments() {
        return listOfStudentAssignments;
    }

    public static List<Student> getListOfAllStudents() {
        return listOfAllStudents;
    }

    //Overloaded setters for student creation, with user input.
    private void setFirstName() {
        this.firstName = Method.inputString("first name of the student");
    }

    private void setLastName() {
        this.lastName = Method.inputString("last name of the student");
    }

    private void setDateOfBirth() {
        System.out.println("Insert the birth date of the student, the student must be an adult to sign up for our courses.");
        this.dateOfBirth = Method.inputLocalDate(LocalDate.of(1940, 01, 02), LocalDate.of(2002, 01, 02));
    }

    public void setTuitionFees() {
        System.out.println("Insert the student's tuition fees for the enrolled course");
        this.tuitionFees += Method.inputInteger(1000, 5000);
        System.out.println("The student's updated tuition fees are: " + this.tuitionFees + "$");
    }

    //A method to appoint and grade the assignments of all students in a given course
    public void addAssignment(Assignment assignment) {
        if (listOfStudentAssignments.contains(assignment)) {
            System.out.println("Assignment Already Appointed!");
        } else {
            System.out.println(firstName + " " + lastName + ":");
            listOfStudentAssignments.add(new Assignment(assignment));
            System.out.println("Assignment appointed to student successfully!\n");
        }
    }

    //A method to create students with user input.
    public static void createStudents() {
        System.out.println("How many Students would you like to create?");
        int input = Method.inputInteger(1, 10);
        for (int i = 0; i < input; i++) {
            System.out.println("Student: " + (i + 1));
            listOfAllStudents.add(new Student());
            enrollStudent(listOfAllStudents.get(i));
        }
    }

    //A method to enroll the student, after creation.
    private static void enrollStudent(Student student) {
        System.out.println("Choose the course(s) you would like to enroll the student in");
        Method.printListOfCourses(Course.getListOfAllCourses());
        Course.getListOfAllCourses().get(Method.inputInteger(1, Course.getListOfAllCourses().size()) - 1).addStudent(student);
        Method.applyAgain(s -> enrollStudent(student));                         //Lambda with closure?
    }
}
