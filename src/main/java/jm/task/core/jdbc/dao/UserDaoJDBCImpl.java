package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {

        String sql = "CREATE TABLE NewUsers (id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50), lastName VARCHAR(50), age TINYINT)";

        Statement statement = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            if (statement != null) {
//                statement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }

    }

    public void dropUsersTable() throws SQLException {
//        String sql = "DROP TABLE NewUsers";
//        Statement statement = null;
//        try {
//            connection = getConnection();
//            statement = connection.createStatement();
//            statement.executeUpdate(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (statement != null) {
//                statement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        PreparedStatement ps = null;

        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {

        PreparedStatement ps = null;

        String sql = "DELETE FROM users WHERE id = ?";

        User user = new User();

        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setLastName(rs.getString("lastName"));
            user.setAge(rs.getByte("age"));

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException{
        List<User> users = new ArrayList<>();

        String sql = "select * from users";
        connection = getConnection();

        Statement stmt = null;
        try {

            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "DELETE FROM users";
        Statement stmt = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
