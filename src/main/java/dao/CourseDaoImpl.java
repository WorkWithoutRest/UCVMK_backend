package dao;

import models.Client;
import models.Course;

import java.sql.*;
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
        return 0;
    }

    public void update(Course course) {

    }

    public void delete(int id) {

    }

    public List<Course> findAll() {
        return null;
    }
}
