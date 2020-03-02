/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import api.Method;
import static api.Method.formatter;
import database.DbExportData;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 25/1/2020
 * @author George.Giazitzis
 * @version 1.1
 */
public class Course {

    private String title;
    private String stream;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private static List<Course> listOfAllCourses = new ArrayList();
    private List<Student> listOfCourseStudents = new ArrayList();
    private List<Assignment> listOfCourseAssignments = new ArrayList();
    private List<Trainer> listOfCourseTrainers = new ArrayList();
    private static int count = 0;
    public final int ID;

    public Course(String title, String stream, String type, LocalDate startDate, LocalDate endDate) {
        count++;
        this.ID = count;
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Constructor with user input.
    public Course() {
        count++;
        this.ID = count;
        setTitle();
        setStream();
        setType();
        setStartDate();
        setEndDate();
    }

    @Override
    public String toString() {
        return String.format("#%-15s%-25s%-15s%-15s%-15s%-15s", ID, title, stream, type, startDate.format(formatter), endDate.format(formatter));
    }

    //Getters & Setters
    public List<Student> getListOfCourseStudents() {
        return listOfCourseStudents;
    }

    public List<Assignment> getListOfCourseAssignments() {
        return listOfCourseAssignments;
    }

    public List<Trainer> getListOfCourseTrainers() {
        return listOfCourseTrainers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate Insert a valid date in the format of LocalDate.of(YYYY,
     * MM, DD)
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate Insert a valid date in the format of LocalDate.of(YYYY,
     * MM, DD)
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public static List<Course> getListOfAllCourses() {
        return listOfAllCourses;
    }

    //Overloaded setters for course creation, with user input.
    private void setTitle() {
        this.title = Method.inputTitleString("course's title");
    }

    private void setStream() {
        this.stream = Method.inputString("course's stream");
    }

    private void setType() {
        this.type = Method.inputString("course's type");
    }

    private void setStartDate() {
        System.out.println("Insert the start date of the course, "
                + "if your input date is a Saturday or Sunday then the start date of the course will be set to next Monday");
        LocalDate date = Method.inputLocalDate(LocalDate.of(2010, 01, 02), LocalDate.of(2030, 01, 02));
        if (date.getDayOfWeek().getValue() == 7 || date.getDayOfWeek().getValue() == 6) {
            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }
        this.startDate = date;
    }

    private void setEndDate() {
        System.out.println("Insert the end date of the course, "
                + "if your input date is a Saturday or Sunday then the end date of the course will be set to previous Friday");
        LocalDate date = Method.inputLocalDate(startDate);
        if (date.getDayOfWeek().getValue() == 7 || date.getDayOfWeek().getValue() == 6) {
            date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        }
        this.endDate = date;
    }

    //A method to enroll a student to a course & update his tuition fees.
    public void addStudent(Student student) {
        if (listOfCourseStudents.contains(student)) {
            System.out.println("Student already enrolled in this course!");
        } else {
            listOfCourseStudents.add(student);
            System.out.println("Student enrolled successfully!");
            student.setTuitionFees();
            DbExportData.exportUpdatedStudentFees(student);
            DbExportData.exportStudentsPerCoruse(this, student);
        }
    }

    //A method to hire a trainer for a course, only if he is not teached in that course already.
    public void addTrainer(Trainer trainer) {
        if (listOfCourseTrainers.contains(trainer)) {
            System.out.println("Trainer is already teaching in this course");
        } else {
            listOfCourseTrainers.add(trainer);
            DbExportData.exportTrainersPerCourse(this, trainer);
            System.out.println("Trainer hired successfully!");
        }
    }

    //A method to assign an assignment to a course, only if it is not assigned to it already. Sets new subdate if needed.
    public void addAssignment(Assignment assignment) {
        if (listOfCourseAssignments.contains(assignment)) {
            System.out.println("Assignment Already Appointed!");
        } else {
            listOfCourseAssignments.add(assignment);
            System.out.println("Assignment appointed to course successfully!");
            assignment.setSubDateTime(startDate, endDate);
            DbExportData.exportAssignments(this, assignment);
        }
    }
}
