package ru.job4j.xml;

import java.io.File;

/**
 * Created by User2 on 23.06.2018.
 */
public class App {
    public static void main(String[] args) {
        String path = "C:\\sqlite\\db";
        SQLiteModel model = new SQLiteModel(path);
        model.createAndFill();
        model.serializeAccountsToXml(new File(path + "\\file.xml"));
        XMLUtils.format(
                new File(path + "\\file.xml"),
                new File(path + "\\f2.xml"),
                new File("chapter_003\\src\\main\\resources\\format.xsl")
        );
        System.out.println(
                XMLUtils.getIntSumFromXml(
                        new File(path + "\\f2.xml")
                )
        );
    }
}
