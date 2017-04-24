package dao;

import models.Course_teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Course_teacherDaoImpl implements Course_teacherDao{

    private Connection connection;
    private String name="postgres";
    private String pass="";
    private String url="jdbc:postgresql://localhost:5432/Ucvmk";

    public Course_teacherDaoImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, pass);
        }
        catch (Exception e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Integer> findTeacher(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT teacher_id from course_teacher WHERE course_id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> teachers=new ArrayList<Integer>();
            while (resultSet.next()) {
                teachers.add(resultSet.getInt(1));
            }
            return teachers;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public List<Integer> findCourse(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT course_id from course_teacher WHERE teacher_id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> courses=new ArrayList<Integer>();
            while (resultSet.next()) {
                courses.add(resultSet.getInt(1));
            }
            return courses;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public int save(Course_teacher course_teacher) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO course_teacher(course_id, teacher_id) " +
                    "VALUES (?,?);");
            preparedStatement.setInt(1, course_teacher.getCourse_id());
            preparedStatement.setInt(2, course_teacher.getTeacher_id());
            preparedStatement.executeUpdate();
            return 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }

    public void update(Course_teacher course_teacher) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE course_teacher SET teacher_id=? WHERE course_id=?");
            preparedStatement.setInt(1, course_teacher.getTeacher_id());
            preparedStatement.setInt(2, course_teacher.getCourse_id());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void delete(int course_id, int teacher_id) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM course_teacher WHERE teacher_id=? AND course_id=?");
            preparedStatement.setInt(1,teacher_id);
            preparedStatement.setInt(2,course_id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Course_teacher> findAll() {
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM course_teacher");

            List<Course_teacher> courses_teachers=new ArrayList<Course_teacher>();

            while(resultSet.next())
            {
                courses_teachers.add(new Course_teacher(resultSet.getInt(1), resultSet.getInt(2)));
            }
            return courses_teachers;
        }
        catch (SQLException e) {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
