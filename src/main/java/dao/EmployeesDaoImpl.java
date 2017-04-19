package dao;

import models.Employees;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeesDaoImpl implements EmployeesDao{

    private Connection connection;
    private String name="postgres";
    private String pass="";
    private String url="jdbc:postgresql://localhost:5432/Ucvmk";

    public EmployeesDaoImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, pass);
        }
        catch (Exception e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Employees find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from employees WHERE id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Employees employees=null;
            while (resultSet.next()) {
                employees = new Employees(resultSet.getInt(1), resultSet.getString(2));
            }
            return employees;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public int save(Employees employees) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO employees(login) " +
                    "VALUES (?);");
            preparedStatement.setString(1, employees.getLogin());
            preparedStatement.executeUpdate();
            return 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }

    public void update(Employees employees) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE employees SET login=? WHERE id=?");
            preparedStatement.setString(1, employees.getLogin());
            preparedStatement.setInt(2, employees.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM employees WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Employees> findAll() {
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM employees");

            int id;
            String login;
            List<Employees> employeesList=new ArrayList<Employees>();

            while(resultSet.next())
            {
                id=resultSet.getInt(1);
                login=resultSet.getString(2);

                Employees employees=new Employees(id, login);
                employeesList.add(employees);
            }
            return employeesList;
        }
        catch (SQLException e) {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
