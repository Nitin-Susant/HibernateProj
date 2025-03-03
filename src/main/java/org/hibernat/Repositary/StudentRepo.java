package org.hibernat.Repositary;

import org.hibernat.DBConnection.BuildSessonFactory;
import org.hibernat.Entity.Student;
import org.hibernat.Entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class StudentRepo {


    private SessionFactory sessionFactory = null;

    public StudentRepo() {
        sessionFactory = BuildSessonFactory.getsessonFactry();
    }

    public void saveUser(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Consider logging the exception
        }
    }

    public void updateUser(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.merge(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }


    }

    public Student getUserById(int id) {

        Session session = sessionFactory.openSession();
        Student student = session.get(Student.class, id);
        return student;
    }

    public List<Student> getStudents() {

        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM public.student";
        NativeQuery<Student> result = session.createNativeQuery(sql, Student.class);
        return result.getResultList();
    }

    public void removeUser(Student student) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }

    public int removeAllUser() {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String sql = "delete from  public.student";

            int i = session.createNativeQuery(sql, Student.class).executeUpdate();
            transaction.commit();
            return i;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        return 0;
    }


}
