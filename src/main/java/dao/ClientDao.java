package dao;

import models.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao {
    Client find(int id);
    int save(Client client);
    void update(Client client);
    void delete(int id);
    List<Client> findAll() throws SQLException;
}
