/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import api.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 25/1/2020
 * @author George.Giazitzis
 * @version 1.1
 */
public class Trainer {

    private String firstName;
    private String lastName;
    private String subject;
    private static List<Trainer> listOfAllTrainers = new ArrayList();
    private static int count = 0;
    public final int ID;

    public Trainer(String firstName, String lastName, String subject) {
        count++;
        this.ID = count;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    //Constructor with user input.
    public Trainer() {
        count++;
        this.ID = count;
        setFirstName();
        setLastName();
        setSubject();
    }

    @Override
    public String toString() {
        return String.format("#%-15s%-15s%-15s%-15s", ID, firstName, lastName, subject);
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
        this.subject = Method.inputTitleString("trainer's subject");
    }
}
