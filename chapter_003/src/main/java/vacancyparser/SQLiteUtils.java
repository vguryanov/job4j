package vacancyparser;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * Created by User2 on 24.06.2018.
 */
public class SQLiteUtils {
    private final static Logger LOGGER = Logger.getLogger(SQLiteUtils.class);
    private static Connection connection;

    public static void openConnection(String driver, String dbFilePath) {
        String url = String.format("jdbc:%s:%s", driver, dbFilePath);
        try {
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            connection = conn;
        } catch (SQLException e) {
            LOGGER.error("Cannot connect with database", e);
        }
    }

    private static void executeQuery(String query) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            LOGGER.error("Cannot execute SQL query: " + query, e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    LOGGER.error("Cannot rollback SQL query: " + query, e1);
                }
            }
        }
    }

    private static void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Cannot execute commit to database", e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    LOGGER.error("Cannot rollback commit to database", e1);
                }
            }
        }
    }

    public static void createTable() {
        executeQuery("create table if not exists vacancy("
                + "id serial primary key,"
                + "title varchar(200),"
                + "hlink varchar(200) not null,"
                + "create_date timestamp"
                + ")");
        commit();
    }

    private static void insertVacancy(Vacancy v) {
        try {
            LOGGER.info("inserting vacancy into DB: " + v);
            PreparedStatement ps = connection.prepareStatement(
                    "insert into vacancy (title, hlink, create_date) values(?, ?, ?)");
            ps.setString(1, v.getTitle());
            ps.setString(2, v.gethLink());
            ps.setTimestamp(3, v.getDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Cannot insert vacancy into database: " + v, e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    LOGGER.error("Cannot rollback commit to database", e1);
                }
            }
        }
    }

    public static void insertVacancies(TreeSet<Vacancy> vacancies) {
        for (Vacancy v : vacancies) {
            SQLiteUtils.insertVacancy(v);
        }
        commit();
    }

    public static ArrayList<Vacancy> getVacancies() {
        ArrayList<Vacancy> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from vacancy");
            while (resultSet.next()) {
                result.add(
                        new Vacancy(
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getTimestamp(4)
                        )
                );
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot load vacancies from database", e);
        }
        return result;
    }

    public static Timestamp getLastTimestamp() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vacancy ORDER BY ID DESC LIMIT 1");
            resultSet.next();
            return resultSet.getTimestamp(4);
        } catch (SQLException e) {
            LOGGER.warn("Cannot load last timestamp database", e);
        }
        return new Timestamp(Calendar.getInstance().get(Calendar.YEAR) - 1900, 0, 1,
                0, 0, 0, 0);
    }
}
