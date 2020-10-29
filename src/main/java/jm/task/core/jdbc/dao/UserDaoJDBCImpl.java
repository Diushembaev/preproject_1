package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String query = "CREATE TABLE Users";
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users(" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    "name TEXT NOT NULL," +
                    "lastname TEXT NOT NULL," +
                    "age INTEGER," +
                    "PRIMARY KEY (`id`)," +
                    "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try {
            Statement statement = Util.getConnection().createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (Statement statement = Util.getConnection().createStatement();){

            statement.executeUpdate("INSERT INTO Users (name, lastname, age) VALUES (name, lastname, age");
        } /*catch (SQLException throwables) {
            //throwables.printStackTrace();
        }*/
    }

    public void removeUserById(long id) {
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.executeUpdate("DELETE FROM Users WHERE id = id ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = (List) new  User();
        try {
            Statement statement = Util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.executeQuery("delete from Users");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
