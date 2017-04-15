package dao;

import models.Client;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.*;
import java.math.BigInteger;


public class ClientDaoImpl implements ClientDao{

    private Connection connection;
    private String name="postgres";
    private String pass="";
    private String url="jdbc:postgresql://localhost:5432/Ucvmk";


    public ClientDaoImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, pass);
        }
        catch (Exception e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public Client find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from client WHERE id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Client client=null;
            while (resultSet.next()) {
                client = new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                        resultSet.getString(11), resultSet.getLong(12), resultSet.getString(13));
            }
            return client;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

    }

    public int save(Client client) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO client (login, name, surname, patronymic_name, birth, email, city, street, house, appartment, telephone, status) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,'user');");
            preparedStatement.setString(1, client.getLogin());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getSurname());
            preparedStatement.setString(4, client.getPatronymic_name());
            preparedStatement.setDate(5, new Date(client.getBirth().getTime()));
            preparedStatement.setString(6, client.getEmail());
            preparedStatement.setString(7, client.getCity());
            preparedStatement.setString(8, client.getStreet());
            preparedStatement.setString(9, client.getHouse());
            preparedStatement.setString(10, client.getAppartment());
            preparedStatement.setLong(11,client.getTelephone());
            preparedStatement.executeUpdate();
            return 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }

    public void update(Client client) {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE client SET login=?, name=?,surname=?, patronymic_name=?, birth=?, email=?, city=?, street=?, house=?, appartment=?, telephone=? WHERE id=?");
            preparedStatement.setString(1, client.getLogin());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getSurname());
            preparedStatement.setString(4, client.getPatronymic_name());
            preparedStatement.setDate(5,  new Date(client.getBirth().getTime()));
            preparedStatement.setString(6, client.getEmail());
            preparedStatement.setString(7, client.getCity());
            preparedStatement.setString(8, client.getStreet());
            preparedStatement.setString(9, client.getHouse());
            preparedStatement.setString(10, client.getAppartment());
            preparedStatement.setLong(11,client.getTelephone());
            preparedStatement.setInt(12,client.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM client WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Client> findAll() {
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM client");

            int id;
            String name, login, surname, patronymic_name, email, city, street, house, appartment, status;
            long telephone;
            Date birth;
            List<Client> clients=new ArrayList<Client>();

            while(resultSet.next())
            {
                id=resultSet.getInt(1);
                login=resultSet.getString(2);
                name=resultSet.getString(3);
                surname=resultSet.getString(4);
                patronymic_name=resultSet.getString(5);
                birth=resultSet.getDate(6);
                email=resultSet.getString(7);
                city=resultSet.getString(8);
                street=resultSet.getString(9);
                house=resultSet.getString(10);
                appartment=resultSet.getString(11);
                telephone=resultSet.getLong(12);
                status=resultSet.getString(13);

                Client client=new Client(id, login, name, surname,
                        patronymic_name, birth, email, city, street, house, appartment,
                        telephone, status);
                clients.add(client);
            }
            return clients;
        }
        catch (SQLException e) {
            Logger.getLogger(ClientDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
