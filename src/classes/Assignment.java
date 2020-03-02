/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import api.Method;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 25/1/2020
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
    private static int count = 0;
    public final int ID;

    //Constructor to create synthetic data manually.
    public Assignment(String title, String description, int oralMark, int totalMark, LocalDateTime subDateTime) {
        count++;
        this.ID = count;
        this.title = title;
        this.description = description;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
        this.subDateTime = subDateTime;
    }

    //Constructor to create deep copies for course assignments, through database import wihtout having ID mismatch.
    public Assignment(int ID, String title, String description, int oralMark, int totalMark, LocalDateTime subDateTime) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
        this.subDateTime = subDateTime;
    }

    //Constructor with user input.
    public Assignment() {
        count++;
        this.ID = count;
        setTitle();
        setDescription();
        setOralMark();
        this.totalMark = 100;
    }

    //Constructor for deep copies from course assignments.
    public Assignment(Assignment assignment) {
        this.ID = assignment.ID;
        this.title = assignment.title;
        this.description = assignment.description;
        this.oralMark = assignment.oralMark;
        setSubDateTime(assignment);
        setTotalMark(assignment);
    }

    @Override
    public String toString() {
        return String.format("#%-15s%-25s%-25s%-15s%-15s%-15s", ID, title, description, oralMark, totalMark, subDateTime);
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

    public int getOralMark() {
        return oralMark;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    public int getTotalMark() {
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
        this.title = Method.inputTitleString("assignment's title");
    }

    private void setDescription() {
        this.description = Method.inputTitleString("assignment's description");
    }

    private void setSubDateTime(Assignment assignment) {
        System.out.println("Insert the date that the student submitted their assignment (minimum date is set as 14 days before the final date of submission)");
        this.subDateTime = LocalDateTime.of(Method.inputLocalDate(assignment.subDateTime.toLocalDate().minusDays(14)), LocalTime.of(23, 59, 59));
    }

    protected void setSubDateTime(LocalDate startDate, LocalDate endDate) {
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
}
