package Servlets;

import dao.ClientDaoImpl;
import dao.Login_passwordDaoImpl;
import services.ClientServise;
import dao.ClientDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dmitrij on 28.05.2017.
 */
public class AutorizationServlet extends HttpServlet {

    private ClientDao clientDao;

    @Override
    public void init() throws ServletException {
        super.init();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // кладем как атрибут запроса под названием "users" список юзеров
        String tocken = req.getParameter("tocken");
        //String id =

        req.getRequestDispatcher("/jsp/autorization.jsp").forward(req, resp);
        // перенаправлем запрос на JSP-станицу

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Login_passwordDaoImpl login_passwordDao = new Login_passwordDaoImpl();
        ClientDaoImpl clientDao = new ClientDaoImpl();
        String tocken = req.getParameter("tocken");
        String login = req.getParameter("login");
        int temp = login_passwordDao.findLogin(login);
        if (login != null) {
            if (temp == 1) {
                String passwd = req.getParameter("password");
                String status = ClientServise.autorization(login, passwd);
                if (status.compareTo("Ok") == 0) {
                    if ((tocken.compareTo("") == 0) || tocken == null) {
                        tocken = clientDao.createTocken(login, passwd);
                        req.setAttribute("tocken", tocken);

                    } else {
                        //already autorizate
                    }

                } else {
                    //login or password is incorrect
                }
            }
            else {
                if (temp == 0) {
                    //user is not registrate
                }
                else {
                    //error
                }
            }
        } else {
            // login is null;
        }

    }

}

