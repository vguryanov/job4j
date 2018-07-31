package ru.job4j.servlets.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBStore implements Store<User> {
    private final static Logger LOGGER = Logger.getLogger(DBStore.class);
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static DBStore instance;
    private static Properties properties;

    private DBStore() {
        SOURCE.setDriverClassName(properties.getProperty("driverClassName"));
        SOURCE.setUrl(properties.getProperty("dbURL"));
        SOURCE.setUsername(properties.getProperty("userName"));
        SOURCE.setPassword(properties.getProperty("password"));
        SOURCE.setMinIdle(Integer.parseInt(properties.getProperty("minIdle")));
        SOURCE.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        SOURCE.setMaxOpenPreparedStatements(Integer.parseInt(properties.getProperty("maxOpenPreparedStatements")));
    }

    public static DBStore getInstance() {
        if (properties == null) {
            loadProps();
        }
        if (instance == null) {
            instance = new DBStore();
        }
        return instance;
    }

    public static void loadProps() {
        ClassLoader classLoader = DBStore.class.getClassLoader();
        File propertiesFile = new File(classLoader.getResource("dbcp.properties").getFile());
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(propertiesFile)) {
            properties.load(input);
            DBStore.properties = properties;
        } catch (IOException e) {
            LOGGER.error("Error during DB property load", e);
        }
    }

    @Override
    public boolean add(String name, String login, String password, String email, User.Role role) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "INSERT INTO users (name, login, email, password, permissions) VALUES (?, ?, ?, ?, ?::role_type)")) {
            st.setString(1, name);
            st.setString(2, login);
            st.setString(3, email);
            st.setString(4, password);
            st.setString(5, role.name());
            st.executeQuery();
        } catch (SQLException e) {
            LOGGER.error("Error while performing INSERT into DB", e);
        }
        return true;
    }

    @Override
    public boolean update(int id, String name, String login, String password, String email, User.Role role) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "update users set name=?, login=?, email=?, password=?, permissions=?::role_type where id=?")) {
            st.setString(1, name);
            st.setString(2, login);
            st.setString(3, email);
            st.setString(4, password);
            st.setString(5, role.toString());
            st.setInt(6, id);
            st.executeQuery();
        } catch (SQLException e) {
            LOGGER.error("Error while performing UPDATE into DB", e);
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "delete from users where id=?")) {
            st.setInt(1, id);
            st.executeQuery();
        } catch (SQLException e) {
            LOGGER.error("Error while performing DELETE from DB", e);
        }
        return true;
    }

    @Override
    public Map<Integer, User> getAll() {
        Map<Integer, User> result = new HashMap<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT * FROM users")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                result.put(
                        id,
                        new User(
                                id,
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(6),
                                rs.getTimestamp(5),
                                User.Role.valueOf(rs.getString(7))
                        )
                );
            }
        } catch (Exception e) {
            LOGGER.error("Error while performing SELECT from DB", e);
        }
        return result;
    }

    @Override
    public User findById(int id) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT * FROM users WHERE id=?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            return new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(6),
                    rs.getTimestamp(5),
                    User.Role.valueOf(rs.getString(7))
            );
        } catch (SQLException e) {
            LOGGER.error("Error while performing SELECT from DB", e);
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        return findById(id) != null;
    }

    public boolean isUserAuthDataValid(String login, String password) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT * FROM users WHERE (login=? and password=?)")) {
            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            LOGGER.error("Error while performing SELECT from DB", e);
        }
        return false;
    }

    public User.Role getRoleForLogin(String login) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT permissions FROM users WHERE login=?")) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            rs.next();
            return User.Role.valueOf(rs.getString(1));
        } catch (SQLException e) {
            LOGGER.error("Error while performing SELECT from DB", e);
        }
        return null;
    }
}