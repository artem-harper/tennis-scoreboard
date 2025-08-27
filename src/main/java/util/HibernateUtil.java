package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration();

        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
