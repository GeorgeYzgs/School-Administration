/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import static classes.Method.formatter;
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

    public Course(String title, String stream, String type, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Constructor with user input.
    public Course() {
        setTitle();
        setStream();
        setType();
        setStartDate();
        setEndDate();
    }

    @Override
    public String toString() {
        return String.format("%-25s%-15s%-15s%-15s%-15s", title, stream, type, startDate.format(formatter), endDate.format(formatter));
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
        System.out.println("Insert the course's title");
        this.title = sc.nextLine().trim();
    }

    private void setStream() {
        System.out.println("Insert the course's stream");
        this.stream = sc.nextLine().trim();
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

    //A method to create courses, with user input.
    public static void createCourses() {
        System.out.println("How many courses would you like to create?");
        int input = Method.inputInteger(1, 10);
        for (int i = 0; i < input; i++) {
            System.out.println("Course: " + (i + 1));
            listOfAllCourses.add(new Course());
        }
    }

    //A method to enroll a student to a course & update his tuition fees.
    public void addStudent(Student student) {
        if (listOfCourseStudents.contains(student)) {
            System.out.println("Student already enrolled in this course!");
        } else {
            listOfCourseStudents.add(student);
            System.out.println("Student enrolled successfully!");
            student.setTuitionFees();
        }
    }

    //A method to hire a trainer for a course, only if he is not teached in that course already.
    public void addTrainer(Trainer trainer) {
        if (listOfCourseTrainers.contains(trainer)) {
            System.out.println("Trainer is already teaching in this course");
        } else {
            listOfCourseTrainers.add(trainer);
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
        }
    }

    //A method to display all the students that have enrolled in more than 1 course.
    public static void showStudentsInMultipleCourses() {
        Student.getListOfAllStudents().forEach(s -> {                           //Behold, the beauty of lambdas.
            long count = listOfAllCourses.stream().filter(c -> c.listOfCourseStudents.contains(s)).count();
            if (count >= 2) {
                System.out.println(s.getFirstName() + " " + s.getLastName() + " #" + s.ID);
                listOfAllCourses.stream().filter(c -> c.listOfCourseStudents.contains(s)).forEach(System.out::println);
            }
        });
    }

    //A method to display the students that have to submit an assignment on a given date, with user input.
    public static void findStudentAssignment() {
        LocalDate date = Method.inputLocalDate(LocalDate.of(2010, 01, 02), LocalDate.of(2030, 01, 02));
        if (date.getDayOfWeek().getValue() == 7 || date.getDayOfWeek().getValue() == 6) {
            date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        }
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(23, 59, 59));
        System.out.println("Student(s) that have to submit an assignment from Monday: "
                + date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                + " to Friday : " + date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)) + "\n");
        listOfAllCourses.forEach(c -> {
            c.listOfCourseAssignments.forEach(a -> {
                if (a.getSubDateTime().isAfter(dateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)))
                        && a.getSubDateTime().isBefore(dateTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)))) {
                    System.out.println(a);
                    Method.printListOfStudents(c.listOfCourseStudents);
                }
            });
        });
    }
}
