import dao.ClientDao;
import dao.ClientDaoImpl;
import models.Client;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientDao clientDao=new ClientDaoImpl();

        /*Date date= new Date(1996, 07, 20);
        Client client1=new Client(3, "dad","kda",
                "sda", "dsad", date, "dkasd@,ad", "sda", "sda",
                "12", "dsa", 2313123, "user");
        clientDao.save(client1);*/

        /*Date date= new Date(1996, 07, 20);
        Client client1=new Client(2, "dady","kda",
                "sda", "dsad", date, "dkasd@,ad", "sda", "sda",
                "12", "dsa", 2313123, "user");
        clientDao.update(client1);*/

        //clientDao.delete(4);

        List<Client> clients=clientDao.findAll();

        for (Client client:clients
             ) {
            System.out.println(client.getId()+client.getName());

        }


        Client client=clientDao.find(2);
        System.out.println(client.getId()+client.getName());
    }
}
