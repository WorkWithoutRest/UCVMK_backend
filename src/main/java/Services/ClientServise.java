package services;

import dao.ClientDaoImpl;
import dao.Login_passwordDao;
import dao.Login_passwordDaoImpl;
import models.Client;
import models.Login_password;

import java.util.Date;

/**
 * Created by Dmitrij on 15.05.2017.
 */
public class ClientServise {

    public static boolean ERROR_IN_CONNECTION = false;
    public static boolean ERROR_IN_PASSWORD = false;

    public static String autorization(String login, String password){
        Login_password login_password = new Login_password(login,password);
        Login_passwordDaoImpl login_passwordDao = new Login_passwordDaoImpl();
        if (login_passwordDao.findLogin(login) == 1) {
            if (login_passwordDao.findPassword(login).equals(password)) {
                return "Ok";
            }
            else {
                return "ERROR_IN_PASSWORD";
                //ERROR_IN_PASSWORD = true;
            }
        }
        else {
            if (login_passwordDao.findLogin(login) == -1) {
                return "ERROR_IN_CONNECTION";
                //ERROR_IN_CONNECTION = true;
            }
        }
        return "ERROR";
    }

    public static void registration( String login, String password, String name, String surname,
                                     String patronymic_name, Date birth, String email,
                                     String city, String street, String house,
                                     String appartment, long telephone) {
        Login_password login_password = new Login_password(login,password);
        Login_passwordDaoImpl login_passwordDao = new Login_passwordDaoImpl();
        if (login_passwordDao.findLogin(login) == 1) {
            if (is_login_correct(login) && is_password_correct(password)) {
                String status = "Client";
                Client client = new Client(login, name, surname, patronymic_name, birth, email, city, street, house, appartment, telephone, status);
                ClientDaoImpl clientDao = new ClientDaoImpl();
                clientDao.save(client);
                login_passwordDao.save(login_password);

            }
        }
        else {
            if (login_passwordDao.findLogin(login) == 0) {
                //login is exist
            }
        }
    }




    private static boolean is_login_correct(String login) {
        if (login.length()>=5 && login.length() <=25) {
            return true;
        }
        return false;
    }

    private static boolean is_password_correct(String password) {
        if (password.length() >=6 && password.length() <= 25) {
           return true;
        }
        return false;
    }



}
