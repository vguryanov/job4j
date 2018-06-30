package vacancyparser;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TreeSet;

/**
 * Created by User2 on 24.06.2018.
 */
public class ParseUtils {
    private final static Logger LOGGER = Logger.getLogger(ParseUtils.class);
    private static final String START_URL = "http://www.sql.ru/forum/actualsearch.aspx?search=java&sin=1&bid=66&a=&ma=0&dt=-1&s=1&so=1&pg=";

    public static void main(String[] args) {
    }

    public static TreeSet<Vacancy> loadVacanciesToDate(Timestamp lastDate) {
        TreeSet<Vacancy> result = new TreeSet<>();
        try {
            Document doc = getPage(1);
            int pageCount = getPageCount(doc);
            boolean isLastVacancyLoadDateAchieved = false;
            for (int i = 1; !isLastVacancyLoadDateAchieved && i <= pageCount; i++) {
                if (i > 1) {
                    doc = getPage(i);
                }
                Elements rows = doc.select("table.forumTable").first().select("tbody>tr");
                for (int j = 1; j < rows.size(); j++) {
                    Element row = rows.get(j);
                    Vacancy v = new Vacancy(
                            row.select("a").first().text(),
                            row.select("a").first().attr("href"),
                            parseDate(row.select("td.altCol").last().text())
                    );
                    if (v.getDate().compareTo(lastDate) <= 0) {
                        isLastVacancyLoadDateAchieved = true;
                        LOGGER.info("Last parse date achieved: " + lastDate);
                        break;
                    }
                    result.add(v);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Vacancy parse error", e);
        }
        return result;
    }

    public static Document getPage(int pageIndex) throws IOException {
        return Jsoup.connect(START_URL + pageIndex).get();
    }

    private static Timestamp parseDate(String s) {
        String date = s.split(", ")[0];
        String time = s.split(", ")[1];
        Date resultDate = null;
        if (s.contains("сегодня")) {
            resultDate = new Date();
        } else if (s.contains("вчера")) {
            resultDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        } else {
            Locale ru = new Locale("ru");
            DateFormatSymbols symbols = DateFormatSymbols.getInstance(ru);
            String[] months = {"янв", "фев", "мар", "апр", "мая", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"};
            symbols.setMonths(months);
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yy", ru);
            try {
                resultDate = format.parse(date);
            } catch (ParseException e) {
                LOGGER.error("Date format error: " + s, e);
            }
        }
        Timestamp resultStamp = new Timestamp(resultDate.getTime());
        resultStamp.setHours(Integer.parseInt(time.split(":")[0]));
        resultStamp.setMinutes(Integer.parseInt(time.split(":")[1]));
        resultStamp.setSeconds(0);
        resultStamp.setNanos(0);
        return resultStamp;
    }

    private static int getPageCount(Document doc) {
        int result = 0;
        Element table = doc.getElementsByClass("forumtable_results").last();
        result = Integer.parseInt(table.select("a").last().text());
        return result;
    }
}
