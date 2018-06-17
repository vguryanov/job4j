package ru.job4j.tracker;

import java.sql.*;
import java.util.Properties;

/**
 * Created by User2 on 15.06.2018.
 */
public class DBUtils {
    private static Connection connection;

    private DBUtils() {
    }

    private static void openConnection() {
        String url = PropertiesUtils.getDbURL();
        Properties props = new Properties();
        props.setProperty("user", PropertiesUtils.getUserName());
        props.setProperty("password", PropertiesUtils.getPassword());
        try {
            connection = DriverManager.getConnection(url, props);
            ensureItemTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void ensureConnection() {
        if (connection == null) {
            openConnection();
        }
    }

    private static void ensureItemTable() {
        executeQuery(PropertiesUtils.getInitQuery());
    }

    private static ResultSet executeQuery(String query) {
        ResultSet result = null;
        try {
            ensureConnection();
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void addItem(Item item) {
        executeQuery(
                String.format(
                        "insert into item (name, description) values ('%s', '%s')",
                        item.getName(),
                        item.getDescription()
                )
        );
    }

    public static Item getItemById(String id) throws SQLException {
        ResultSet rs = executeQuery("select * from item where id = " + id);
        rs.next();
        return getItemFromResultSet(rs);
    }

    public static Item getItemByName(String name) throws SQLException {
        ResultSet rs = executeQuery(String.format("select * from item where name = '%s'", name));
        rs.next();
        return getItemFromResultSet(rs);
    }

    private static Item getItemFromResultSet(ResultSet rs) throws SQLException {
        return new Item(
                String.valueOf(rs.getInt(1)),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getTimestamp(5)
        );
    }

    public static ResultSet getAllItems() {
        return executeQuery("select * from item");
    }

    public static void removeItem(String id) {
        executeQuery("update item set is_deleted = true where id = " + id);
    }

    public static void replaceItemById(String id, Item item) {
        executeQuery(
                String.format(
                        "update item set name = '%s', description = '%s', comments = '%s', created = '%s' where id = %s",
                        item.getName(),
                        item.getDescription(),
                        item.getComments(),
                        item.getCreated(),
                        id
                )
        );
    }
}
