package dao;

import models.Employees;

import java.util.List;

public interface EmployeesDao {
    Employees find(int id);
    int save(Employees employees);
    void update(Employees employees);
    void delete(int id);
    List<Employees> findAll();
}
