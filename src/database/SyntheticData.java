/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import classes.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @since 2/1/2020
 * @author George.Giazitzis
 * @version 1.1
 */
public abstract class SyntheticData {

    //Synthetic courses.
    public static void setPremadeCourses() {
        Course.getListOfAllCourses().addAll(Arrays.asList(
                new Course("Coding Bootcamp 9", "Java", "FullTime", LocalDate.of(2019, 11, 04), LocalDate.of(2020, 3, 07)),
                new Course("Coding Bootcamp 9", "Java", "PartTime", LocalDate.of(2019, 11, 11), LocalDate.of(2020, 05, 22)),
                new Course("Coding Bootcamp 9", "C#", "FullTime", LocalDate.of(2019, 11, 04), LocalDate.of(2020, 3, 07)),
                new Course("Coding Bootcamp 9", "C#", "PartTime", LocalDate.of(2019, 11, 11), LocalDate.of(2020, 05, 22))));
    }

    //Synthetic trainers.
    public static void setPremadeTrainers() {
        Trainer.getListOfAllTrainers().addAll(Arrays.asList(
                new Trainer("Michael", "Scott", "Java Object Oriented Programming"),
                new Trainer("Andrew", "Bernard", "Introduction to C#"),
                new Trainer("Dwight", "Schrute", "SQL Databases"),
                new Trainer("Jim", "Halpert", "Front-end development"),
                new Trainer("Kevin", "Malone", "Algorithms")));
        Course.getListOfAllCourses().get(0).getListOfCourseTrainers().addAll(Arrays.asList(
                Trainer.getListOfAllTrainers().get(0),
                Trainer.getListOfAllTrainers().get(2),
                Trainer.getListOfAllTrainers().get(3),
                Trainer.getListOfAllTrainers().get(4)));
        Course.getListOfAllCourses().get(1).getListOfCourseTrainers().addAll(Arrays.asList(
                Trainer.getListOfAllTrainers().get(0),
                Trainer.getListOfAllTrainers().get(2),
                Trainer.getListOfAllTrainers().get(3)));
        Course.getListOfAllCourses().get(2).getListOfCourseTrainers().addAll(Arrays.asList(
                Trainer.getListOfAllTrainers().get(1),
                Trainer.getListOfAllTrainers().get(2),
                Trainer.getListOfAllTrainers().get(3),
                Trainer.getListOfAllTrainers().get(4)));
        Course.getListOfAllCourses().get(3).getListOfCourseTrainers().addAll(Arrays.asList(
                Trainer.getListOfAllTrainers().get(1),
                Trainer.getListOfAllTrainers().get(2),
                Trainer.getListOfAllTrainers().get(3)));
    }

    //Synthetic students.
    public static void setPremadeStudents() {
        Student.getListOfAllStudents().addAll(Arrays.asList(
                new Student("Jack", "Black", LocalDate.of(1969, 8, 28), 2500),
                new Student("John", "Bravo", LocalDate.of(1995, 3, 26), 2500),
                new Student("John", "Wick", LocalDate.of(1991, 3, 05), 2500),
                new Student("Jason", "Statham", LocalDate.of(1967, 7, 26), 5000),
                new Student("Rocky", "Balboa", LocalDate.of(1976, 11, 21), 2500),
                new Student("Viktor", "Hugo", LocalDate.of(1802, 2, 26), 2500),
                new Student("Anne", "Frank", LocalDate.of(1929, 7, 12), 2500),
                new Student("Hans", "Solo", LocalDate.of(1980, 3, 17), 5000),
                new Student("Leia", "Skywalker", LocalDate.of(1981, 11, 21), 2500),
                new Student("Luke", "Skywalker", LocalDate.of(1981, 11, 21), 2500),
                new Student("Anakin", "Skywalker", LocalDate.of(1959, 4, 6), 2500),
                new Student("Darth", "Vader", LocalDate.of(1959, 4, 6), 2500),
                new Student("Lara", "Croft", LocalDate.of(1984, 4, 19), 2500)));
        Course.getListOfAllCourses().get(0).getListOfCourseStudents().addAll(Arrays.asList(
                Student.getListOfAllStudents().get(0),
                Student.getListOfAllStudents().get(1),
                Student.getListOfAllStudents().get(2),
                Student.getListOfAllStudents().get(3)));
        Course.getListOfAllCourses().get(1).getListOfCourseStudents().addAll(Arrays.asList(
                Student.getListOfAllStudents().get(4),
                Student.getListOfAllStudents().get(5),
                Student.getListOfAllStudents().get(6),
                Student.getListOfAllStudents().get(7)));
        Course.getListOfAllCourses().get(2).getListOfCourseStudents().addAll(Arrays.asList(
                Student.getListOfAllStudents().get(3),
                Student.getListOfAllStudents().get(7),
                Student.getListOfAllStudents().get(8),
                Student.getListOfAllStudents().get(9)));
        Course.getListOfAllCourses().get(3).getListOfCourseStudents().addAll(Arrays.asList(
                Student.getListOfAllStudents().get(10),
                Student.getListOfAllStudents().get(11),
                Student.getListOfAllStudents().get(12)));
    }

    //Synthetic assignments.
    public static void setPremadeAssignments() {
        Assignment.getListOfAllAssignments().addAll(Arrays.asList(
                new Assignment("Java Student Project", "Individual Full Time", 20, 100, LocalDateTime.of(2019, 12, 14, 23, 59, 59)),
                new Assignment("Java Student Project", "Individual Part Time", 25, 100, LocalDateTime.of(2020, 01, 02, 23, 59, 59)),
                new Assignment("C# Student Project", "Individual Full Time", 20, 100, LocalDateTime.of(2019, 12, 14, 23, 59, 59)),
                new Assignment("C# Student Project", "Individual Part Time", 25, 100, LocalDateTime.of(2020, 01, 02, 23, 59, 59)),
                new Assignment("Front End Development", "Final Exam Full Time", 25, 100, LocalDateTime.of(2020, 02, 20, 23, 59, 59)),
                new Assignment("Web Development", "Final Exam Part Time", 30, 100, LocalDateTime.of(2020, 5, 01, 23, 59, 59)),
                new Assignment("MySQL", "Final Exam Full Time", 25, 100, LocalDateTime.of(2020, 02, 20, 23, 59, 59)),
                new Assignment("SQL Databases", "Final Exam Part Time", 30, 100, LocalDateTime.of(2020, 5, 01, 23, 59, 59))));
        Course.getListOfAllCourses().get(0).getListOfCourseAssignments().addAll(Arrays.asList(
                Assignment.getListOfAllAssignments().get(0),
                Assignment.getListOfAllAssignments().get(4)));  
        Course.getListOfAllCourses().get(1).getListOfCourseAssignments().addAll(Arrays.asList(
                Assignment.getListOfAllAssignments().get(1),
                Assignment.getListOfAllAssignments().get(5)));
        Course.getListOfAllCourses().get(2).getListOfCourseAssignments().addAll(Arrays.asList(
                Assignment.getListOfAllAssignments().get(2),
                Assignment.getListOfAllAssignments().get(6))); 
        Course.getListOfAllCourses().get(3).getListOfCourseAssignments().addAll(Arrays.asList(
                Assignment.getListOfAllAssignments().get(3),
                Assignment.getListOfAllAssignments().get(7)));
        Course.getListOfAllCourses().get(0).getListOfCourseStudents().get(0).getListOfStudentAssignments().add(new Assignment("Java Student Project", "Individual Full Time", 20, 67, LocalDateTime.of(2019, 12, 14, 23, 59, 59)));
        Course.getListOfAllCourses().get(0).getListOfCourseStudents().get(1).getListOfStudentAssignments().add(new Assignment("Java Student Project", "Individual Full Time", 20, 70, LocalDateTime.of(2019, 12, 14, 23, 59, 59)));
        Course.getListOfAllCourses().get(0).getListOfCourseStudents().get(2).getListOfStudentAssignments().add(new Assignment("Java Student Project", "Individual Full Time", 20, 92, LocalDateTime.of(2019, 12, 14, 23, 59, 59)));
        Course.getListOfAllCourses().get(0).getListOfCourseStudents().get(3).getListOfStudentAssignments().add(new Assignment("Java Student Project", "Individual Full Time", 20, 89, LocalDateTime.of(2019, 12, 14, 23, 59, 59)));
        Course.getListOfAllCourses().get(1).getListOfCourseStudents().get(0).getListOfStudentAssignments().add(new Assignment("Java Student Project", "Individual Part Time", 25, 60, LocalDateTime.of(2020, 01, 02, 23, 59, 59)));
        Course.getListOfAllCourses().get(1).getListOfCourseStudents().get(1).getListOfStudentAssignments().add(new Assignment("Java Student Project", "Individual Part Time", 25, 82, LocalDateTime.of(2020, 01, 02, 23, 59, 59)));
        Course.getListOfAllCourses().get(1).getListOfCourseStudents().get(2).getListOfStudentAssignments().add(new Assignment("Java Student Project", "Individual Part Time", 25, 76, LocalDateTime.of(2020, 01, 02, 23, 59, 59)));
        Course.getListOfAllCourses().get(1).getListOfCourseStudents().get(3).getListOfStudentAssignments().add(new Assignment("Java Student Project", "Individual Part Time", 25, 84, LocalDateTime.of(2020, 01, 02, 23, 59, 59)));
        Course.getListOfAllCourses().get(2).getListOfCourseStudents().get(0).getListOfStudentAssignments().add(new Assignment("C# Student Project", "Individual Full Time", 25, 74, LocalDateTime.of(2019, 12, 14, 23, 59, 59)));
        Course.getListOfAllCourses().get(2).getListOfCourseStudents().get(1).getListOfStudentAssignments().add(new Assignment("C# Student Project", "Individual Full Time", 25, 68, LocalDateTime.of(2019, 12, 14, 23, 59, 59)));
        Course.getListOfAllCourses().get(2).getListOfCourseStudents().get(2).getListOfStudentAssignments().add(new Assignment("C# Student Project", "Individual Full Time", 25, 52, LocalDateTime.of(2019, 12, 14, 23, 59, 59)));
        Course.getListOfAllCourses().get(2).getListOfCourseStudents().get(3).getListOfStudentAssignments().add(new Assignment("C# Student Project", "Individual Full Time", 25, 91, LocalDateTime.of(2019, 12, 14, 23, 59, 59)));
        Course.getListOfAllCourses().get(3).getListOfCourseStudents().get(0).getListOfStudentAssignments().add(new Assignment("C# Student Project", "Individual Part Time", 30, 81, LocalDateTime.of(2020, 01, 02, 23, 59, 59)));
        Course.getListOfAllCourses().get(3).getListOfCourseStudents().get(1).getListOfStudentAssignments().add(new Assignment("C# Student Project", "Individual Part Time", 30, 42, LocalDateTime.of(2020, 01, 02, 23, 59, 59)));
        Course.getListOfAllCourses().get(3).getListOfCourseStudents().get(2).getListOfStudentAssignments().add(new Assignment("C# Student Project", "Individual Part Time", 30, 83, LocalDateTime.of(2020, 01, 02, 23, 59, 59)));
        Course.getListOfAllCourses().get(0).getListOfCourseStudents().get(0).getListOfStudentAssignments().add(new Assignment("Front End Development", "Final Exam Full Time", 20, 67, LocalDateTime.of(2020, 02, 20, 23, 59, 59)));
        Course.getListOfAllCourses().get(0).getListOfCourseStudents().get(1).getListOfStudentAssignments().add(new Assignment("Front End Development", "Final Exam Full Time", 20, 71, LocalDateTime.of(2020, 02, 20, 23, 59, 59)));
        Course.getListOfAllCourses().get(0).getListOfCourseStudents().get(2).getListOfStudentAssignments().add(new Assignment("Front End Development", "Final Exam Full Time", 20, 79, LocalDateTime.of(2020, 02, 20, 23, 59, 59)));
        Course.getListOfAllCourses().get(0).getListOfCourseStudents().get(3).getListOfStudentAssignments().add(new Assignment("Front End Development", "Final Exam Full Time", 20, 82, LocalDateTime.of(2020, 02, 20, 23, 59, 59)));
        Course.getListOfAllCourses().get(1).getListOfCourseStudents().get(0).getListOfStudentAssignments().add(new Assignment("Web Development", "Final Exam Part Time", 25, 93, LocalDateTime.of(2020, 5, 01, 23, 59, 59)));
        Course.getListOfAllCourses().get(1).getListOfCourseStudents().get(1).getListOfStudentAssignments().add(new Assignment("Web Development", "Final Exam Part Time", 25, 75, LocalDateTime.of(2020, 5, 01, 23, 59, 59)));
        Course.getListOfAllCourses().get(1).getListOfCourseStudents().get(2).getListOfStudentAssignments().add(new Assignment("Web Development", "Final Exam Part Time", 25, 59, LocalDateTime.of(2020, 5, 01, 23, 59, 59)));
        Course.getListOfAllCourses().get(1).getListOfCourseStudents().get(3).getListOfStudentAssignments().add(new Assignment("Web Development", "Final Exam Part Time", 25, 68, LocalDateTime.of(2020, 5, 01, 23, 59, 59)));
        Course.getListOfAllCourses().get(2).getListOfCourseStudents().get(0).getListOfStudentAssignments().add(new Assignment("MySQL", "Final Exam Full Time", 25, 88, LocalDateTime.of(2020, 02, 20, 23, 59, 59)));
        Course.getListOfAllCourses().get(2).getListOfCourseStudents().get(1).getListOfStudentAssignments().add(new Assignment("MySQL", "Final Exam Full Time", 25, 73, LocalDateTime.of(2020, 02, 20, 23, 59, 59)));
        Course.getListOfAllCourses().get(2).getListOfCourseStudents().get(2).getListOfStudentAssignments().add(new Assignment("MySQL", "Final Exam Full Time", 25, 61, LocalDateTime.of(2020, 02, 20, 23, 59, 59)));
        Course.getListOfAllCourses().get(2).getListOfCourseStudents().get(3).getListOfStudentAssignments().add(new Assignment("MySQL", "Final Exam Full Time", 25, 70, LocalDateTime.of(2020, 02, 20, 23, 59, 59)));
        Course.getListOfAllCourses().get(3).getListOfCourseStudents().get(0).getListOfStudentAssignments().add(new Assignment("SQL Databases", "Final Exam Part Time", 30, 81, LocalDateTime.of(2020, 5, 01, 23, 59, 59)));
        Course.getListOfAllCourses().get(3).getListOfCourseStudents().get(1).getListOfStudentAssignments().add(new Assignment("SQL Databases", "Final Exam Part Time", 30, 78, LocalDateTime.of(2020, 5, 01, 23, 59, 59)));
        Course.getListOfAllCourses().get(3).getListOfCourseStudents().get(2).getListOfStudentAssignments().add(new Assignment("SQL Databases", "Final Exam Part Time", 30, 98, LocalDateTime.of(2020, 5, 01, 23, 59, 59)));
    }
}
