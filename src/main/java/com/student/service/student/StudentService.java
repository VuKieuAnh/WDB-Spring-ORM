package com.student.service.student;

import com.student.config.AppConfiguration;
import com.student.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class StudentService implements IStudentService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Iterable<Student> findAll() {
        String queryStr = "SELECT c FROM Student AS c";
        TypedQuery<Student> query = entityManager.createQuery(queryStr, Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(Integer id) {
        String queryStr = "SELECT c FROM Student AS c WHERE c.id = :id";
        TypedQuery<Student> query = entityManager.createQuery(queryStr, Student.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Student update(Student model) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(model);
            transaction.commit();
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public Student save(Student model) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(model);
            transaction.commit();
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public void remove(Integer id) {
        String queryStr = "DELETE c FROM Student AS c WHERE c.id = :id";
        TypedQuery<Student> query = entityManager.createQuery(queryStr, Student.class);
        query.setParameter("id", id);
        query.getSingleResult();
    }
}
