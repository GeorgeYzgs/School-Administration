/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import static classes.Method.sc;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 2/1/2020
 * @author George.Giazitzis
 * @version 1.1
 */
public class Assignment {
    
    private String title;
    private String description;
    private LocalDateTime subDateTime;
    private int oralMark;
    private int totalMark;
    private static List<Assignment> listOfAllAssignments = new ArrayList();
    
    public Assignment(String title, String description, int oralMark, int totalMark, LocalDateTime subDateTime) {
        this.title = title;
        this.description = description;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
        this.subDateTime = subDateTime;
    }

    //Constructor with user input.
    private Assignment() {
        setTitle();
        setDescription();
        setOralMark();
        this.totalMark = 100;
    }

    //Constructor for deep copies from course assignments.
    public Assignment(Assignment assignment) {
        this.title = assignment.title;
        this.description = assignment.description;
        this.oralMark = assignment.oralMark;
        setSubDateTime(assignment);
        setTotalMark(assignment);
    }
    
    @Override
    public String toString() {
        return String.format("%-25s%-25s%-15s%-15s%-15s", title, description, oralMark, totalMark, subDateTime);
    }

    //Getters & Setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getSubDateTime() {
        return subDateTime;
    }
    
    public void setSubDateTime(LocalDateTime subDateTime) {
        this.subDateTime = subDateTime;
    }
    
    public double getOralMark() {
        return oralMark;
    }
    
    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }
    
    public double getTotalMark() {
        return totalMark;
    }
    
    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }
    
    public static List<Assignment> getListOfAllAssignments() {
        return listOfAllAssignments;
    }

    //Overloaded setters for assignment creation, with user input.
    private void setTitle() {
        this.title = Method.inputString("assignments title");
    }
    
    private void setDescription() {
        System.out.println("Insert the assignment's description");
        this.description = sc.nextLine().trim();
    }
    
    private void setSubDateTime(Assignment assignment) {
        System.out.println("Insert the date that the student submitted their assignment (minimum date is set as 14 days before the final date of submission)");
        this.subDateTime = LocalDateTime.of(Method.inputLocalDate(assignment.subDateTime.toLocalDate().minusDays(14)), LocalTime.of(23, 59, 59));
    }
    
    public void setSubDateTime(LocalDate startDate, LocalDate endDate) {
        System.out.println("Insert the assignment's date of submission, "
                + "if your input date is a Saturday or Sunday then the submission date will be set to previous Friday");
        LocalDate date = Method.inputLocalDate(startDate, endDate);
        if (date.getDayOfWeek().getValue() == 7 || date.getDayOfWeek().getValue() == 6) {
            date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        }
        this.subDateTime = LocalDateTime.of(date, LocalTime.of(23, 59, 59));
    }
    
    private void setOralMark() {
        System.out.println("Insert the assignment's oral mark percentile of the total mark");
        this.oralMark = Method.inputInteger(10, 50);
    }
    
    private void setTotalMark(Assignment assignment) {
        if (subDateTime.isAfter(assignment.subDateTime)) {
            System.out.println("The student submitted their assignment too late! Their grade will be set to 0");
            this.totalMark = 0;
        } else {
            System.out.println("Insert the student's total mark for this assignment (minimum value is set as the oral mark)");
            this.totalMark = Method.inputInteger(oralMark, 100);
        }
    }

    //A method to create assignments, with user input.
    public static void createAssignments() {
        System.out.println("How many Assignments would you like to appoint?(A copy will be given to the students)");
        int input = Method.inputInteger(1, 10);
        for (int i = 0; i < input; i++) {
            System.out.println("Assignment: " + (i + 1));
            listOfAllAssignments.add(new Assignment());
            appointCourseStudentAssignment(listOfAllAssignments.get(i));
        }
    }

    //A method to appoint assignments to a course and its students, after creation.
    private static void appointCourseStudentAssignment(Assignment assignment) {
        System.out.println("Choose the course you would like to appoint this assignment to");
        Method.printListOfCourses(Course.getListOfAllCourses());
        int appoint = Method.inputInteger(1, Course.getListOfAllCourses().size());
        Course.getListOfAllCourses().get(appoint - 1).addAssignment(assignment);
        System.out.println("Lets set the dates that the students of this course submitted their assignments & their respective marks\n");    
        Course.getListOfAllCourses().get(appoint - 1).getListOfCourseStudents().forEach(s -> s.addAssignment(assignment));
    }    
}
