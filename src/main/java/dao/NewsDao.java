package dao;

import models.News;

import java.util.List;

public interface NewsDao {
    News find(int id);
    int save(News news);
    void update(News news);
    void delete(int id);
    List<News> findAll();
}
