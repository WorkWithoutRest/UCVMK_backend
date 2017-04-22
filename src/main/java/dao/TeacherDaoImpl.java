package dao;

import models.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeacherDaoImpl implements TeacherDao{

    private Connection connection;
    private String name="postgres";
    private String pass="";
    private String url="jdbc:postgresql://localhost:5432/Ucvmk";

    public TeacherDaoImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, pass);
        }
        catch (Exception e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Teacher find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from teacher WHERE id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Teacher teacher=null;
            while (resultSet.next()) {
                teacher = new Teacher(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }
            return teacher;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public int save(Teacher teacher) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO teacher(name, link, login) " +
                    "VALUES (?,?,?);");
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getLink());
            preparedStatement.setString(3, teacher.getLogin());
            preparedStatement.executeUpdate();
            return 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }

    public void update(Teacher teacher) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE teacher SET name=?, link=?, login=? WHERE id=?");
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getLink());
            preparedStatement.setString(3, teacher.getLogin());
            preparedStatement.setInt(4, teacher.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM teacher WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Teacher> findAll() {
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM teacher");

            int id;
            String name, link, login;
            List<Teacher> teachers=new ArrayList<Teacher>();

            while(resultSet.next())
            {
                id=resultSet.getInt(1);
                name=resultSet.getString(2);
                link=resultSet.getString(3);
                login=resultSet.getString(4);

                Teacher teacher=new Teacher(id, name, link, login);
                teachers.add(teacher);
            }
            return teachers;
        }
        catch (SQLException e) {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
