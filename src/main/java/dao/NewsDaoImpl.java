package dao;

import models.News;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsDaoImpl implements NewsDao{

    private Connection connection;
    private String name="postgres";
    private String pass="";
    private String url="jdbc:postgresql://localhost:5432/Ucvmk";

    public NewsDaoImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, pass);
        }
        catch (Exception e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public News find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from news WHERE id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            News news=null;
            while (resultSet.next()) {
                news = new News(resultSet.getInt(1), resultSet.getDate(2), resultSet.getString(3));
            }
            return news;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public int save(News news) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO news(date, text) " +
                    "VALUES (?,?);");
            preparedStatement.setDate(1, new Date(news.getDate().getTime()));
            preparedStatement.setString(2, news.getText());
            preparedStatement.executeUpdate();
            return 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }

    public void update(News news) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE news SET date=?, text=? WHERE id=?");
            preparedStatement.setDate(1, new Date(news.getDate().getTime()));
            preparedStatement.setString(2, news.getText());
            preparedStatement.setInt(3, news.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM news WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<News> findAll() {
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM news");

            int id;
            String text;
            java.util.Date date;
            List<News> newsList=new ArrayList<News>();

            while(resultSet.next())
            {
                id=resultSet.getInt(1);
                date=resultSet.getDate(2);
                text=resultSet.getString(3);

                News news=new News(id, date,text);
                newsList.add(news);
            }
            return newsList;
        }
        catch (SQLException e) {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
