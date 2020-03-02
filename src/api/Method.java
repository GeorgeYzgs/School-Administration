/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import classes.*;
import database.*;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * @since 25/1/2020
 * @author George.Giazitzis
 * @version 1.1
 */
public abstract class Method {

    public static Scanner sc = new Scanner(System.in);
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy").withResolverStyle(ResolverStyle.SMART);

    //A method to get a valid string with only alphabetic characters, with user input.
    public static String inputString(String name) {
        System.out.println("Insert the " + name + ", your input must only contain alphabetic characters (no spaces) & must be 4 to 30 characters long!");
        String input = sc.nextLine();
        while (!input.matches("[a-zA-Z]{4,30}")) {
            System.out.println("Invalid input, must only contain alphabetic characters & must be 4 to 30 characters long!");
            input = sc.nextLine();
        }
        return input;
    }

    //A method to get a valid string with only alphabetic characters, with user input.
    public static String inputTitleString(String name) {
        System.out.println("Insert the " + name + ", your input must only contain alphanumeric characters & must be 4 to 30 characters long!");
        String input = sc.nextLine();
        while (!input.matches("[a-zA-Z0-9 #]+{4,30}")) {
            System.out.println("Invalid input, cannot contain special characters & must be 4 to 30 characters long!");
            input = sc.nextLine();
        }
        return input.trim();
    }

    //A method to get a valid integer, within a range of two given integers, with user input.
    public static int inputInteger(int min, int max) {
        int input;
        do {
            System.out.println("Please enter a positive integer from " + min + " to " + max);
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input, that's not a number!");
                sc.nextLine();
            }
            input = sc.nextInt();
            sc.nextLine();
        } while (input < min || input > max);
        return input;
    }

    //A method to get a valid date After a given date, with user input.
    public static LocalDate inputLocalDate(LocalDate startDate) {
        String endDate;
        LocalDate date = LocalDate.MIN;
        do {
            System.out.println("Date must be in the following format: (DD/MM/YYYY) and after " + startDate.format(formatter));
            endDate = sc.nextLine();
            try {
                date = LocalDate.parse(endDate, formatter);
            } catch (DateTimeParseException ignore) {
                System.out.println("Invalid date format!");
            }
        } while (!date.isAfter(startDate));
        return date;
    }

    //A method to get a valid date, within two given dates, with user input.
    public static LocalDate inputLocalDate(LocalDate startDate, LocalDate endDate) {
        String subDate;
        LocalDate date = LocalDate.MIN;
        do {
            System.out.println("Date must be in the following format: (DD/MM/YYYY)"
                    + "\nAfter the date of: " + startDate.format(formatter) + " and before the date of: " + endDate.format(formatter));
            subDate = sc.nextLine();
            try {
                date = LocalDate.parse(subDate, formatter);
            } catch (DateTimeParseException ignore) {
                System.out.println("Invalid date format!");
            }
        } while (date.isBefore(startDate) || date.isAfter(endDate));
        return date;
    }

    //Introductory menu.
    private static void run() {
        System.out.println("Hello! Would you like to create your own data for our database or use the premade ones?"
                + "\n\"1\" to use premade data"
                + "\n\"2\" to create your own data");
        loop:
        do {
            switch (sc.nextLine()) {
                case "1":
                    System.out.println("Welcome to Coding Bootcamp 9 School Structure!");
                    retrieveLists();
                    break loop;
                case "2":
                    System.out.println("Lets Create new data then!");
                    createData();
                    break loop;
                default:
                    System.out.println("Invalid input, please try again!");
            }
        } while (true);
    }

    //Methods to import data from database.
    public static void importData() {
        try {
            DbConnect.establishConnection();
            DbConnect.importCourses(Course.getListOfAllCourses());
            DbConnect.importTrainers(Trainer.getListOfAllTrainers());
            DbConnect.importStudents(Student.getListOfAllStudents());
            DbConnect.importAssignments(Assignment.getListOfAllAssignments());
            run();
        } catch (SQLException ex) {
            System.out.printf("Unable to retrieve data, %s", ex.getMessage());
        }
    }

    //Methods to run for synthetic data, not used for Project part B.
    private static void setPremadeData() throws SQLException {
        SyntheticData.setPremadeCourses();
        SyntheticData.setPremadeTrainers();
        SyntheticData.setPremadeStudents();
        SyntheticData.setPremadeAssignments();
        retrieveLists();
    }

    //Methods to run for user input date.
    private static void createData() {
        CreateData.createCourses();
        CreateData.createTrainers();
        CreateData.createStudents();
        CreateData.createAssignments();
        retrieveLists();
    }

    //A method that is used for students & trainers to be applied on multiple courses, with lamda expressions.
    public static <T> void applyAgain(Consumer<T> consumer) {                   //used java.util.functions Consumer
        System.out.println("Would you like to apply to another course?"
                + "\nInsert \"1\" for yes or \"2\" for no");
        loop:
        do {
            switch (sc.nextLine()) {
                case "1":
                    consumer.accept((T) consumer);
                    break loop;
                case "2":
                    break loop;
                default:
                    System.out.println("Invalid input, please try again!");
            }
        } while (true);
    }

    //Main menu.
    private static void retrieveLists() {
        DbConnect.terminateConnection();
        loop:
        do {
            System.out.println("-------------------------------------------------------------------------------------------"
                    + "\nEnter the corresponding number to go through our database"
                    + "\n\"1\" for a list of all our current students"
                    + "\n\"2\" for a list of all our trainers"
                    + "\n\"3\" for a list of all the given out assignments"
                    + "\n\"4\" for a list of all our available courses"
                    + "\n\"5\" for a list of students per course"
                    + "\n\"6\" for a list of trainers per course"
                    + "\n\"7\" for a list of assignments per course"
                    + "\n\"8\" for a list of assignments per student"
                    + "\n\"9\" for a list of students that have enrolled in more than one course"
                    + "\n\"10\" to insert a date and check which students have to submit an assignment in that week"
                    + "\n\"end\" when you are finished!"
                    + "\n-------------------------------------------------------------------------------------------");
            switch (sc.nextLine()) {
                case "1":
                    System.out.format("%-15s%-15s%-15s%-15s%-15s\n", "Student ID", "First Name", "Last Name", "Date of Birth", "Tuition Fees");
                    Student.getListOfAllStudents().forEach(System.out::println);
                    break;
                case "2":
                    System.out.format("%-15s%-15s%-15s%-15s\n", "Trainer ID", "First Name", "Last Name", "Subject");
                    Trainer.getListOfAllTrainers().forEach(System.out::println);
                    break;
                case "3":
                    System.out.format("%-15s%-25s%-25s%-15s%-15s%-15s\n", "Assignment ID", "Title", "Description", "Oral Mark", "Total Mark", "Sub Date");
                    Assignment.getListOfAllAssignments().forEach(System.out::println);
                    break;
                case "4":
                    System.out.format("%-15s%-25s%-15s%-15s%-15s%-15s\n", "Course ID", "Title", "Stream", "Type", "Start Date", "End Date");
                    Course.getListOfAllCourses().forEach(System.out::println);
                    break;
                case "5":
                    System.out.println("Which course's students would you like to see?");
                    browseLists("course's Students", Course.getListOfAllCourses(),
                            i -> Method.printListOfStudents(Course.getListOfAllCourses().get(i).getListOfCourseStudents()));
                    break loop;
                case "6":
                    System.out.println("Which course's trainers would you like to see?");
                    browseLists("course's Trainers", Course.getListOfAllCourses(),
                            i -> Method.printListOfTrainers(Course.getListOfAllCourses().get(i).getListOfCourseTrainers()));
                    break loop;
                case "7":
                    System.out.println("Which course's assignments would you like to see?");
                    browseLists("course's Assignments", Course.getListOfAllCourses(),
                            i -> Method.printListOfAssignments(Course.getListOfAllCourses().get(i).getListOfCourseAssignments()));
                    break loop;
                case "8":
                    System.out.println("Which student's assignements would you like to see?");
                    browseLists("student's Assignments", Student.getListOfAllStudents(),
                            i -> Method.printListOfAssignments(Student.getListOfAllStudents().get(i).getListOfStudentAssignments()));
                    break loop;
                case "9":
                    System.out.println("Here is a list of the students that have enrolled in more than one course :");
                    showStudentsInMultipleCourses();
                    break;
                case "10":
                    System.out.println("Lets find the Students that have submit an assignment on a given week");
                    findStudentAssignment();
                    break;
                case "end":
                    System.out.println("Thank you for your time!");
                    break loop;
                default:
                    System.out.println("Invalid input, please try again!");
            }
        } while (true);
    }

    //A method to browse lists , with lambda expression!
    private static <T> void browseLists(String name, List<T> list, IntConsumer lambda) {    //used java.util.functions IntConsumer
        do {
            if (list.containsAll(Course.getListOfAllCourses())) {
                printListOfCourses((List<Course>) list);
            } else {
                printListOfStudents((List<Student>) list);
            }
            System.out.println("Insert a corresponding number to review a " + name + ", "
                    + "\"end\" to terminate the program, \"back\" to go back to the main menu");
            String choice = sc.nextLine().replaceAll("\\s", "");
            Scanner test = new Scanner(choice);
            if (test.hasNextInt()) {
                if (Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > list.size()) {
                    System.out.println("Wrong numeric value, please try again!");
                } else {
                    lambda.accept(Integer.parseInt(choice) - 1);
                }
            } else {
                if (choice.equalsIgnoreCase("end")) {
                    System.out.println("Thank you for your time!");
                    break;
                } else if (choice.equalsIgnoreCase("back")) {
                    retrieveLists();
                    break;
                }
                System.out.println("Wrong input, please try again!");
            }
        } while (true);
    }

    //A method to display all the students that have enrolled in more than 1 course.
    private static void showStudentsInMultipleCourses() {
        Student.getListOfAllStudents().forEach(s -> {
            long count = Course.getListOfAllCourses().stream().filter(c -> c.getListOfCourseStudents().contains(s)).count();
            if (count >= 2) {
                System.out.println(s.getFirstName() + " " + s.getLastName() + " ID #" + s.ID);
                Course.getListOfAllCourses().stream().filter(c -> c.getListOfCourseStudents().contains(s)).forEach(System.out::println);
            }
        });
    }

    //A method to display the students that have to submit an assignment on a given date, with user input.
    private static void findStudentAssignment() {
        LocalDate date = Method.inputLocalDate(LocalDate.of(2010, 01, 02), LocalDate.of(2030, 01, 02));
        if (date.getDayOfWeek().getValue() == 7 || date.getDayOfWeek().getValue() == 6) {
            date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        }
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(23, 59, 59));
        System.out.println("Student(s) that have to submit an assignment from Monday: "
                + date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                + " to Friday : " + date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)) + "\n");
        Course.getListOfAllCourses().forEach(c -> {
            c.getListOfCourseAssignments().forEach(a -> {
                if (a.getSubDateTime().isAfter(dateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)))
                        && a.getSubDateTime().isBefore(dateTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)))) {
                    System.out.println(a);
                    Method.printListOfStudents(c.getListOfCourseStudents());
                }
            });
        });
    }

    //Methods to print full lists and course lists, because toString looks bad.
    public static void printListOfCourses(List<Course> courseList) {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.format("%-15s%-25s%-15s%-15s%-15s%-15s\n", "Course ID", "Title", "Stream", "Type", "Start Date", "End Date");
        courseList.forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private static void printListOfStudents(List<Student> studentList) {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.format("%-15s%-15s%-15s%-15s%-15s\n", "Student ID", "First Name", "Last Name", "Date of Birth", "Tuition Fees");
        studentList.forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private static void printListOfTrainers(List<Trainer> trainerList) {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.format("%-15s%-15s%-15s%-15s\n", "Trainer ID", "First Name", "Last Name", "Subject");
        trainerList.forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private static void printListOfAssignments(List<Assignment> assignmentList) {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.format("%-15s%-25s%-25s%-15s%-15s%-15s\n", "Assignment ID", "Title", "Description", "Oral Mark", "Total Mark", "Sub Date");
        assignmentList.forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------------------------------");
    }
}
