package ru.job4j.servlets.persistence;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBStore implements Store<User> {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static DBStore instance;
    private static Properties properties;

    public DBStore() {
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
        InputStream input = null;
        try {
            input = new FileInputStream(propertiesFile);
            properties.load(input);
            DBStore.properties = properties;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean add(String name, String login, String email) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "INSERT INTO users (name, login, email) VALUES (?, ?, ?)")) {
            st.setString(1, name);
            st.setString(2, login);
            st.setString(3, email);
            st.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(int id, String name, String login, String email) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "update users set name=?, login=?, email=? where id=?")) {
            st.setString(1, name);
            st.setString(2, login);
            st.setString(3, email);
            st.setInt(4, id);
            st.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
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
                                rs.getTimestamp(5)
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                    rs.getTimestamp(5)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        return findById(id) != null;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().getAll());
    }
}