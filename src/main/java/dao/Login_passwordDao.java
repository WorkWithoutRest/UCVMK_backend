package dao;

import models.Login_password;

import java.util.List;

public interface Login_passwordDao {
    String findPassword(String login);
    int save(Login_password login_password);
    void updatePassword(Login_password login_password);
    void delete(String login);
    List<Login_password> findAll();
}
