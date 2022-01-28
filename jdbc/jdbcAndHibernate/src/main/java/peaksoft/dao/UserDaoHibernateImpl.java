package peaksoft.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        String SQL = "CREATE TABLE IF NOT EXISTS users(id serial not null primary key, " +
                "name varchar(250) not null, last_name varchar(250), age int)";
        Query query = session.createSQLQuery(SQL);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("User table created successfully");
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("DROP TABLE IF EXISTS users");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("User table deleted successfully");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User(name,lastName,age);
        session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println(user.getName() + " added successfully");
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        User u = (User) session.get(User.class, id);
        session.delete(u);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted" + u);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        session.getTransaction().commit();
        session.close();
        System.out.println("Found " + users.size() + " users");
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("User cleaned successfully");
    }
}
