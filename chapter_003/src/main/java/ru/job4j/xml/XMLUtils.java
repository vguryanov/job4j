package ru.job4j.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by User2 on 21.06.2018.
 */
public class XMLUtils {
    static File format(File source, File dest, File scheme) {
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer(new StreamSource(scheme));
            transformer.transform(new StreamSource(source), new StreamResult(dest));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return dest;
    }

    static int getIntSumFromXml(File file) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        MyHandler handler = new MyHandler();
        InputStream is;
        SAXParser parser;
        try {
            is = new FileInputStream(file);
            parser = factory.newSAXParser();
            parser.parse(is, handler);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return handler.getValue();
    }

    static class MyHandler extends DefaultHandler {
        private int value;

        int getValue() {
            return value;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.getQName(i).equals("href")) {
                    value += Integer.parseInt(attributes.getValue(i));
                }
            }
            super.startElement(uri, localName, qName, attributes);
        }
    }
}
