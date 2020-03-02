/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import api.Method;
import static api.Method.formatter;
import database.DbExportData;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 25/1/2020
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
    private static int count = 0;
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
    public Student() {
        count++;
        this.ID = count;
        setFirstName();
        setLastName();
        setDateOfBirth();
        this.tuitionFees = 0;
    }

    @Override
    public String toString() {
        return String.format("#%-15s%-15s%-15s%-15s$%-15s", ID, firstName, lastName, dateOfBirth.format(formatter), tuitionFees);
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

    protected void setTuitionFees() {
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
            DbExportData.exportAssignmentsPerStudent(this, listOfStudentAssignments.get(listOfStudentAssignments.size() - 1));
            System.out.println("Assignment appointed to student successfully!\n");
        }
    }
}
