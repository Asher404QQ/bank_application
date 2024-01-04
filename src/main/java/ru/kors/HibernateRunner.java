package ru.kors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.kors.model.Account;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession()) {
            System.out.println("Сессия в Hibernate открыта!");

            session.beginTransaction();

            Account account1 = new Account("test1@email.com", "user1", "qwerty123");
            Account account2 = new Account("test2@email.com", "user2", "qwerty123");
            Account account3 = new Account("test3@email.com", "user3", "qwerty123");
            Account account4 = new Account("test4@email.com", "user4", "qwerty123");

            session.save(account1);
            session.persist(account2);

            session.saveOrUpdate(account1);
            session.merge(account3);
            session.persist(account4);

            session.delete(account2);
            session.remove(account4);

            Account getAcc = session.get(Account.class, 3);
            System.out.println(getAcc);

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            System.err.println("Ошибка в создании сессии!");
        }
    }
}
