package vacancyparser;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by User2 on 30.06.2018.
 */
public class App {
    private static Properties properties = new Properties();

    private static void loadProperties() {
        ClassLoader classLoader = App.class.getClassLoader();
        File propertiesFile = new File(classLoader.getResource("vacancy_parser.properties").getFile());
        InputStream input = null;
        try {
            input = new FileInputStream(propertiesFile);
            properties.load(input);
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

    public static void main(String[] args) {
        loadProperties();
        QuartzScheduler.init(
                VacancyParseJob.class,
                properties.getProperty("cron.expression")
        );
    }

    public static class VacancyParseJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            SQLiteUtils.openConnection(
                    properties.getProperty("jdbc.driver"),
                    properties.getProperty("jdbc.url")
            );
            SQLiteUtils.createTable();
            SQLiteUtils.insertVacancies(
                    ParseUtils.loadVacanciesToDate(
                            SQLiteUtils.getLastTimestamp()
                    )
            );
        }
    }
}
