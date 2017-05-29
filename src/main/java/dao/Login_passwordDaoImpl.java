package dao;

import models.Login_password;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login_passwordDaoImpl implements Login_passwordDao{

    private Connection connection;
    private String name="postgres";
    private String pass="";
    private String url="jdbc:postgresql://localhost:5432/Ucvmk";
    private static int workload=12;

    public Login_passwordDaoImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, pass);
        }
        catch (Exception e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }




    public String findPassword(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT password from login_password WHERE login=?");
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            String pass=null;
            while (resultSet.next()) {
                pass=resultSet.getString(1);
            }
            return pass;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public int findLogin(String login)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from login_password WHERE login=?");
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return 0;
            }
            else
                return 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return -1;
        }
    }

    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if(stored_hash==null || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return(password_verified);
    }

    public int save(Login_password login_password) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO login_password(login, password) " +
                    "VALUES (?,?);");
            preparedStatement.setString(1, login_password.getLogin());
            preparedStatement.setString(2, BCrypt.hashpw(login_password.getPassword(), BCrypt.gensalt(workload)));
            preparedStatement.executeUpdate();
            return 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }

    public void updatePassword(Login_password login_password) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE login_password SET password=? WHERE login=?");
            preparedStatement.setString(1, BCrypt.hashpw(login_password.getPassword(), BCrypt.gensalt(workload)));
            preparedStatement.setString(2, login_password.getLogin());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void delete(String login) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM login_password WHERE login=?");
            preparedStatement.setString(1,login);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Login_password> findAll() {
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM login_password");

            List<Login_password> logins_passwords=new ArrayList<Login_password>();

            while(resultSet.next())
            {
                logins_passwords.add(new Login_password(resultSet.getString(1), resultSet.getString(2)));
            }
            return logins_passwords;
        }
        catch (SQLException e) {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
