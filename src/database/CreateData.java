/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import api.Method;
import classes.*;

/**
 * @since 25/1/2020
 * @author George.Giazitzis
 * @version 1.1
 */
public abstract class CreateData {

    //A method to create courses, with user input.
    public static void createCourses() {
        System.out.println("How many courses would you like to create?");
        int input = Method.inputInteger(1, 10);
        for (int i = 0; i < input; i++) {
            System.out.println("Course: " + (i + 1));
            Course c = new Course();
            Course.getListOfAllCourses().add(c);
            DbExportData.exportCourses(c);
        }
    }

    //A method to create trainers, with user input.
    public static void createTrainers() {
        System.out.println("How many Trainers would you like to create?");
        int input = Method.inputInteger(1, 10);
        for (int i = 0; i < input; i++) {
            System.out.println("Trainer: " + (i + 1));
            Trainer t = new Trainer();
            Trainer.getListOfAllTrainers().add(t);
            DbExportData.exportTrainers(t);
            hireTrainer(t);
        }
    }

    //A method to hire the trainer, after creation.
    private static void hireTrainer(Trainer trainer) {
        System.out.println("Choose the course(s) you would like to hire the trainer for");
        Method.printListOfCourses(Course.getListOfAllCourses());
        int appoint = Method.inputInteger(1, Course.getListOfAllCourses().size());
        Course.getListOfAllCourses().get(appoint - 1).addTrainer(trainer);
        Method.applyAgain(t -> hireTrainer(trainer));
    }

    //A method to create students with user input.
    public static void createStudents() {
        System.out.println("How many Students would you like to create?");
        int input = Method.inputInteger(1, 10);
        for (int i = 0; i < input; i++) {
            System.out.println("Student: " + (i + 1));
            Student s = new Student();
            Student.getListOfAllStudents().add(s);
            DbExportData.exportStudents(s);
            enrollStudent(s);
        }
    }

    //A method to enroll the student, after creation.
    private static void enrollStudent(Student student) {
        System.out.println("Choose the course(s) you would like to enroll the student in");
        Method.printListOfCourses(Course.getListOfAllCourses());
        int appoint = Method.inputInteger(1, Course.getListOfAllCourses().size());
        Course.getListOfAllCourses().get(appoint - 1).addStudent(student);
        Method.applyAgain(s -> enrollStudent(student));
    }

    //A method to create assignments, with user input.
    public static void createAssignments() {
        System.out.println("How many Assignments would you like to appoint?(A copy will be given to the students)");
        int input = Method.inputInteger(1, 10);
        for (int i = 0; i < input; i++) {
            System.out.println("Assignment: " + (i + 1));
            Assignment a = new Assignment();
            Assignment.getListOfAllAssignments().add(a);
            appointCourseStudentAssignment(a);
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
