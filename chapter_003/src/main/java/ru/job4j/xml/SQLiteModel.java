package ru.job4j.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User2 on 17.06.2018.
 */
public class SQLiteModel {
    private Connection connection;
    private final String path;

    SQLiteModel(String path) {
        this.path = path;
    }

    private void openConnection() {
        File f = new File(path);
        f.mkdirs();
        String url = "jdbc:sqlite:" + path + "\\test.db";
        try {
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            if (conn != null) {
                connection = conn;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void ensureConnection() {
        if (connection == null) {
            openConnection();
        }
    }

    private void executeQuery(String query) {
        ensureConnection();
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTable() {
        executeQuery("CREATE TABLE IF NOT EXISTS account (\n"
                + "	id serial PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	password text NOT NULL,\n"
                + "	intValue int NOT NULL\n"
                + ");");
        executeQuery("delete from account;");
    }

    private void fillTable() {
        try {
            Statement statement = connection.createStatement();
            for (int i = 0; i < 100; i++) {
                statement.executeUpdate(String.format("insert into account(name, password, intValue) values('name%d', 'pass%d', %d)", i, i, i));
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    void createAndFill() {
        createTable();
        fillTable();
    }

    private List<Account> getAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from account");

            while (resultSet.next()) {
                accounts.add(
                        new Account(
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getInt(4)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    void serializeAccountsToXml(File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Accounts.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(
                    new Accounts(this.getAccounts()),
                    file
            );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

