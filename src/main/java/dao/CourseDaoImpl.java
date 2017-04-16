package dao;

import models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDaoImpl implements CourseDao{

    private Connection connection;
    private String name="postgres";
    private String pass="";
    private String url="jdbc:postgresql://localhost:5432/Ucvmk";

    public CourseDaoImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, pass);
        }
        catch (Exception e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Course find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from course WHERE id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Course course=null;
            while (resultSet.next()) {
                course = new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getDate(4), resultSet.getDate(5), resultSet.getBoolean(6));
            }
            return course;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public int save(Course course) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO course(title, text, date_start, date_finish, status) " +
                    "VALUES (?,?,?,?,?);");
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setString(2, course.getText());
            preparedStatement.setDate(3, new Date(course.getDate_start().getTime()));
            preparedStatement.setDate(4, new Date(course.getDate_finish().getTime()));
            preparedStatement.setBoolean(5, course.getStatus());
            preparedStatement.executeUpdate();
            return 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }

    public void update(Course course) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE course SET title=?, text=?, date_start=?, date_finish=?, status=? WHERE id=?");
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setString(2, course.getText());
            preparedStatement.setDate(3, new Date(course.getDate_start().getTime()));
            preparedStatement.setDate(4, new Date(course.getDate_finish().getTime()));
            preparedStatement.setBoolean(5, course.getStatus());
            preparedStatement.setInt(6, course.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM course WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Course> findAll() {
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM course");

            int id;
            String title, text;
            Date date_start, date_finish;
            boolean status;
            List<Course> courses=new ArrayList<Course>();

            while(resultSet.next())
            {
                id=resultSet.getInt(1);
                title=resultSet.getString(2);
                text=resultSet.getString(3);
                date_start=resultSet.getDate(4);
                date_finish=resultSet.getDate(5);
                status=resultSet.getBoolean(6);

                Course course=new Course(id, title, text, date_start, date_finish, status);
                courses.add(course);
            }
            return courses;
        }
        catch (SQLException e) {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
