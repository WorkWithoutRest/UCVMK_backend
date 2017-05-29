package services;

import dao.NewsDaoImpl;
import models.News;

import java.util.List;

public class NewsService {
    public static List<News> getNews() {
        NewsDaoImpl newsDao = new NewsDaoImpl();
        return newsDao.findAll();
    }
}
