package com.student.service.student;

import com.student.config.AppConfiguration;
import com.student.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class StudentService implements IStudentService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Iterable findAll() {
        String queryStr = "SELECT c FROM Student AS c";
        TypedQuery<Student> query = entityManager.createQuery(queryStr, Student.class);
        return query.getResultList();
    }

    @Override
    public Object findById(Integer id) {
        String queryStr = "SELECT c FROM Student AS c WHERE c.id = :id";
        TypedQuery<Student> query = entityManager.createQuery(queryStr, Student.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(Object model) {

    }

    @Override
    public void save(Object model) {

    }

    @Override
    public void remove(Long id) {

    }
}
