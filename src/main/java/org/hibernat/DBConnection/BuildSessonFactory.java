package org.hibernat.DBConnection;

import org.hibernat.Entity.Users;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

final public class BuildSessonFactory {

    private static SessionFactory sessionFactory = null;



    private BuildSessonFactory() {

    }

    public static SessionFactory getsessonFactry() {


        if (sessionFactory == null) {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            cfg.addAnnotatedClass(Users.class);
            sessionFactory = cfg.buildSessionFactory();
        }

        return sessionFactory;
    }


}
