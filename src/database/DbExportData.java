/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import classes.*;
import java.sql.*;
import static database.DbConnect.con;

/**
 * @since 25/1/2020
 * @author George.Giazitzis
 * @version 1.1
 */
public abstract class DbExportData {

    //method to insert courses to their database table
    static void exportCourses(Course c) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO COURSES VALUES (NULL, ?, ?, ?, ?, ?);");
            pst.setString(1, c.getTitle());
            pst.setString(2, c.getStream());
            pst.setString(3, c.getType());
            pst.setDate(4, Date.valueOf(c.getStartDate()));
            pst.setDate(5, Date.valueOf(c.getEndDate()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    //method to insert trainers to their database table
    static void exportTrainers(Trainer t) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO TRAINERS VALUES (NULL, ?, ?, ?);");
            pst.setString(1, t.getFirstName());
            pst.setString(2, t.getLastName());
            pst.setString(3, t.getSubject());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    //method to associate trainers with courses (export to database)
    public static void exportTrainersPerCourse(Course c, Trainer t) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO TRAINERS_PER_COURSE VALUES (?, ?);");
            pst.setInt(1, c.ID);
            pst.setInt(2, t.ID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    //method to insert students to their database table
    static void exportStudents(Student s) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO STUDENTS VALUES (NULL, ?, ?, ?, ?);");
            pst.setString(1, s.getFirstName());
            pst.setString(2, s.getLastName());
            pst.setDate(3, Date.valueOf(s.getDateOfBirth()));
            pst.setInt(4, s.getTuitionFees());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    //method to update the student fees on the database after applying the student to another course
    public static void exportUpdatedStudentFees(Student s) {
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE STUDENTS SET TUITION_FEES = ?  WHERE STUDENT_ID = ? ;");
            pst.setInt(1, s.getTuitionFees());
            pst.setInt(2, s.ID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    //method to associate students with courses (export to database)
    public static void exportStudentsPerCoruse(Course c, Student s) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO STUDENTS_PER_COURSE VALUES (?, ?);");
            pst.setInt(1, c.ID);
            pst.setInt(2, s.ID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    //method to associate assignment with a course (export to database)
    public static void exportAssignments(Course c, Assignment a) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO ASSIGNMENTS VALUES (?, NULL, ?, ?, ?, ?, ?);");
            pst.setInt(1, c.ID);
            pst.setString(2, a.getTitle());
            pst.setString(3, a.getDescription());
            pst.setInt(4, a.getOralMark());
            pst.setInt(5, a.getTotalMark());
            pst.setDate(6, Date.valueOf(a.getSubDateTime().toLocalDate()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    //method to associate assignments with students (export to database)
    public static void exportAssignmentsPerStudent(Student s, Assignment a) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO ASSIGNMENTS_PER_STUDENT_PER_COURSE VALUES (?, ?, ?, ?);");
            pst.setInt(1, a.ID);
            pst.setInt(2, s.ID);
            pst.setInt(3, a.getTotalMark());
            pst.setDate(4, Date.valueOf(a.getSubDateTime().toLocalDate()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}
