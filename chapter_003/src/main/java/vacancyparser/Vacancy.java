package vacancyparser;

import java.sql.Timestamp;

/**
 * Created by User2 on 24.06.2018.
 */
public class Vacancy implements Comparable<Vacancy> {
    private String title, description, hLink;
    private Timestamp date;

    public Vacancy(String title, String hLink, Timestamp date) {
        this.title = title;
        this.hLink = hLink;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String gethLink() {
        return hLink;
    }

    public void sethLink(String hLink) {
        this.hLink = hLink;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public int compareTo(Vacancy o) {
        return date.compareTo(o.getDate());
    }

    @Override
    public String toString() {
        return date + " " + title + " " + hLink;
    }
}
