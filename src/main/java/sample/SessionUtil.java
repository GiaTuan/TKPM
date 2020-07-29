package sample;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionUtil {
    private static final SessionUtil instance = new SessionUtil();
    private final SessionFactory sessionFactory;

    private SessionUtil()
    {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static SessionUtil getInstance()
    {
        return instance;
    }

    public static Session getSession()
    {
        return getInstance().sessionFactory.openSession();
    }
}
