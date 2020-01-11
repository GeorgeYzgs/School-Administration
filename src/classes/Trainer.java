/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import static classes.Method.sc;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 2/1/2020
 * @author George.Giazitzis
 * @version 1.1
 */
public class Trainer {

    private String firstName;
    private String lastName;
    private String subject;
    private static List<Trainer> listOfAllTrainers = new ArrayList();

    public Trainer(String firstName, String lastName, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    //Constructor with user input.
    private Trainer() {
        setFirstName();
        setLastName();
        setSubject();
    }

    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s", firstName, lastName, subject);
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public static List<Trainer> getListOfAllTrainers() {
        return listOfAllTrainers;
    }

    //Overloaded setters for trainer creation, with user input.
    private void setFirstName() {
        this.firstName = Method.inputString("first name of the trainer");
    }

    private void setLastName() {
        this.lastName = Method.inputString("last name of the trainer");
    }

    private void setSubject() {
        System.out.println("Insert the subject this trainer teaches");
        this.subject = sc.nextLine().trim();
    }

    //A method to create trainers, with user input.
    public static void createTrainers() {
        System.out.println("How many Trainers would you like to create?");
        int input = Method.inputInteger(1, 10);
        for (int i = 0; i < input; i++) {
            System.out.println("Trainer: " + (i + 1));
            listOfAllTrainers.add(new Trainer());
            hireTrainer(listOfAllTrainers.get(i));
        }
    }

    //A method to hire the trainer, after creation.
    private static void hireTrainer(Trainer trainer) {
        System.out.println("Choose the course(s) you would like to hire the trainer for");
        Method.printListOfCourses(Course.getListOfAllCourses());
        Course.getListOfAllCourses().get(Method.inputInteger(1, Course.getListOfAllCourses().size()) - 1).addTrainer(trainer);
        Method.applyAgain(t -> hireTrainer(trainer));                           //Lambda with closure?
    }
}
