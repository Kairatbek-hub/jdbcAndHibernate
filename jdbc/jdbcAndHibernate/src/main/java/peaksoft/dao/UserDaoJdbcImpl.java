package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String SQL = "create table if not exists users(\n" +
                " id serial primary key,\n" +
                " name varchar(250) not null ,\n" +
                " last_name varchar(250) not null ,\n" +
                " age int)";
        try(Connection connection = Util.connect();
            Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(SQL);
            System.out.println("Table is created successfully");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String SQL = "drop table if exists users";
        try(Connection connection = Util.connect();
            Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(SQL);
            System.out.println("Table users deleted successfully");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "insert into users(name, last_name, age) " +
                "values(?, ?, ?)";
        try(Connection connection = Util.connect();
            PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println(name + " added to users table successfully");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String SQL = "delete from users where id = ?";
        try(Connection connection = Util.connect();
            PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User with id " + id + " deleted successfully");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        String SQL = "select * from users";
        try(Connection connection = Util.connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()){
                User user = new User(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("last_name"),
                        rs.getByte("age"));
                listOfUsers.add(user);
            }
            return listOfUsers;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void cleanUsersTable() {
        String SQL = "delete from users";
        try(Connection connection = Util.connect();
            Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(SQL);
            System.out.println("Table users cleaned");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}