package services;

import dao.NewsDaoImpl;
import models.News;

import java.util.List;

/**
 * Created by Dmitrij on 15.05.2017.
 */
public class NewsServices {

    public static List<News> getNews() {
        NewsDaoImpl newsDao = new NewsDaoImpl();
        return newsDao.findAll();
    }

}
