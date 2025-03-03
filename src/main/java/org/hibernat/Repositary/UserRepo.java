package org.hibernat.Repositary;

import org.hibernat.DBConnection.BuildSessonFactory;
import org.hibernat.Entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserRepo {

    private SessionFactory sessionFactory = null;

    public UserRepo() {
        sessionFactory = BuildSessonFactory.getsessonFactry();
    }

    public void saveUser(Users users) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(users);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Consider logging the exception
        }
    }

    public void updateUser(Users users) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.merge(users);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }


    }

    public Users getUserById(int id) {

        Session session = sessionFactory.openSession();
        Users users = session.get(Users.class, id);
        return users;
    }

    public List<Users> getUsers() {

        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM public.user_table";
        NativeQuery<Users> result = session.createNativeQuery(sql, Users.class);
        return result.getResultList();
    }

    public void removeUser(Users users) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(users);
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
            String sql = "delete from  public.users";

            int i = session.createNativeQuery(sql, Users.class).executeUpdate();
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
