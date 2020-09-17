package com.student.service.student;

public interface IService<T> {
    Iterable<T> findAll();

    T findById(Integer id);

    void update(T model);

    void save(T model);

    void remove(Long id);
}
