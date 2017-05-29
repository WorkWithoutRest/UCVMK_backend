package dao;

import models.Course_client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Course_clientDaoImpl implements Course_clientDao{

    private Connection connection;
    private String name="postgres";
    private String pass="";
    private String url="jdbc:postgresql://localhost:5432/Ucvmk";

    public Course_clientDaoImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, pass);
        }
        catch (Exception e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Integer> findClient(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT client_id from course_client WHERE cource_id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> clients=new ArrayList<Integer>();
            while (resultSet.next()) {
                    clients.add(resultSet.getInt(1));
                }
            return clients;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public List<Integer> findCourse(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT cource_id from course_client WHERE client_id=?");
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

    public int save(Course_client course_client) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO course_client(client_id, cource_id) " +
                    "VALUES (?,?);");
            preparedStatement.setInt(1, course_client.getClient_id());
            preparedStatement.setInt(2, course_client.getCourse_id());
            preparedStatement.executeUpdate();
            return 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }


    public void delete(int course_id, int client_id) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM course_client WHERE client_id=? AND cource_id=?");
            preparedStatement.setInt(1,client_id);
            preparedStatement.setInt(2,course_id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public boolean checkCourseForClient(int course_id, int client_id)
    {
        return true;
    }
    public List<Course_client> findAll() {
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM course_client");

            List<Course_client> courses_clients=new ArrayList<Course_client>();

            while(resultSet.next())
            {
                courses_clients.add(new Course_client(resultSet.getInt(1), resultSet.getInt(2)));
            }
            return courses_clients;
        }
        catch (SQLException e) {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
