/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import classes.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @since 25/1/2020
 * @author George.Giazitzis
 * @version 1.1
 */
public abstract class DbConnect {

    static Connection con;
    static Statement st;
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/SCHOOL_PROJECT";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static void establishConnection() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            st = con.createStatement();
        } catch (SQLException ex) {
            System.out.println("Unable to establish a connection, please validate your information!");
        }
    }

    public static void terminateConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    //method to retrieve courses from database
    public static void importCourses(List<Course> list) throws SQLException {
        ResultSet rs = st.executeQuery("select * from courses");
        while (rs.next()) {
            list.add(new Course(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), rs.getDate(5).toLocalDate()));
        }
    }

    //method to retrieve trainers from database
    public static void importTrainers(List<Trainer> list) throws SQLException {
        ResultSet rs = st.executeQuery("select * from trainers");
        while (rs.next()) {
            list.add(new Trainer(rs.getString(2), rs.getString(3), rs.getString(3)));
        }
        importTrainersPerCourse();
    }

    //method to associate trainers with courses (import from database)
    private static void importTrainersPerCourse() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT c.course_id,t.trainer_id FROM trainers t "
                + "JOIN trainers_per_course USING (trainer_id) JOIN courses c USING (course_id);");
        while (rs.next()) {
            Course.getListOfAllCourses().get(rs.getInt(1) - 1).getListOfCourseTrainers().add(Trainer.getListOfAllTrainers().get(rs.getInt(2) - 1));
        }
    }

    //method to retrieve students from database
    public static void importStudents(List<Student> list) throws SQLException {
        ResultSet rs = st.executeQuery("select * from students");
        while (rs.next()) {
            list.add(new Student(rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getInt(5)));
        }
        importStudentsPerCourse();
    }

    //method to associate students with courses (import from database)
    private static void importStudentsPerCourse() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT c.course_id,s.student_id FROM students s "
                + "JOIN students_per_Course USING (student_id) JOIN courses c USING (course_id);");
        while (rs.next()) {
            Course.getListOfAllCourses().get(rs.getInt(1) - 1).getListOfCourseStudents().add(Student.getListOfAllStudents().get(rs.getInt(2) - 1));
        }
    }

    //method to retrieve assignments from database and associates with a course.
    public static void importAssignments(List<Assignment> list) throws SQLException {
        ResultSet rs = st.executeQuery("select * from assignments");
        while (rs.next()) {
            list.add(new Assignment(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), LocalDateTime.of(rs.getDate(7).toLocalDate(), LocalTime.of(23, 59, 59))));
            Course.getListOfAllCourses().get(rs.getInt(1) - 1).getListOfCourseAssignments().add(list.get(rs.getInt(2) - 1));
        }
        importAssignmentsPerStudent();
    }

    //method to associate assignments to each individual student of a course.(import from database)
    private static void importAssignmentsPerStudent() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT s.STUDENT_ID, a.ASSIGNMENT_ID,a.TITLE,a.DESCRIPTION,a.ORAL_MARK,apsc.TOTAL_MARK,apsc.SUB_DATE "
                + "FROM ASSIGNMENTS_PER_STUDENT_PER_COURSE apsc INNER JOIN STUDENTS s "
                + "ON s.STUDENT_ID= apsc.STUDENT_ID "
                + "INNER JOIN ASSIGNMENTS a ON apsc.ASSIGNMENT_ID = a.ASSIGNMENT_ID;");
        while (rs.next()) {
            Student.getListOfAllStudents().get(rs.getInt(1) - 1).getListOfStudentAssignments().add(new Assignment(rs.getInt(2), rs.getString(3), rs.getString(4),
                    rs.getInt(5), rs.getInt(6), LocalDateTime.of(rs.getDate(7).toLocalDate(), LocalTime.of(23, 59, 59))));
        }
    }
}
