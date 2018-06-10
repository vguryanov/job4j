package ru.job4j.stockexchange;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import ru.job4j.stockexchange.ExchangePlatform.OfferType;


/**
 * Created by User2 on 23.05.2018.
 */
public class ExchangePlatformTest {
    private ExchangePlatform exchangePlatform = new ExchangePlatform();
    private static String[] emitters = new String[]{"Apple Inc.", "IBM", "Tesla Ltd."};

    @Before
    public void setUp() throws Exception {
        exchangePlatform = new ExchangePlatform();
        Offer.resetIdCounter();

        exchangePlatform.addEmitter(emitters[0]);
        exchangePlatform.addEmitter(emitters[1]);
        exchangePlatform.addEmitter(emitters[2]);

        exchangePlatform.createOffer(new Offer(OfferType.BUY, 5, 10, emitters[0]));
        exchangePlatform.createOffer(new Offer(OfferType.SELL, 7, 20, emitters[0]));
        exchangePlatform.createOffer(new Offer(OfferType.BUY, 12, 13, emitters[1]));
        exchangePlatform.createOffer(new Offer(OfferType.SELL, 14, 13, emitters[1]));
        exchangePlatform.createOffer(new Offer(OfferType.BUY, 200, 1, emitters[2]));
        exchangePlatform.createOffer(new Offer(OfferType.SELL, 205, 5, emitters[2]));
        exchangePlatform.work(true);
    }

    @Test
    public void addOffer() throws Exception {
        exchangePlatform.createOffer(new Offer(OfferType.SELL, 4, 10, emitters[0]));
        exchangePlatform.createOffer(new Offer(OfferType.BUY, 8, 20, emitters[0]));
        exchangePlatform.work(true);
        String result = exchangePlatform.getViewForEmitter(emitters[0]);
        String expected = "Apple Inc. offers:\n"
                + "no available offers.";
        assertThat(result, is(expected));
    }

    @Test
    public void removeOffer() throws Exception {
        exchangePlatform.closeOffer(3, emitters[1]);
        exchangePlatform.closeOffer(4, emitters[1]);
        exchangePlatform.work(true);
        String result = exchangePlatform.getViewForEmitter(emitters[1]);
        String expected = "IBM offers:\n"
                + "no available offers.";
        assertThat(result, is(expected));
    }

    @Test
    public void print() throws Exception {
        String expected0 = "Apple Inc. offers:\n\n"
                + "Sell\tPrice\tBuy\n"
                + "20\t\t7\t\t0\n"
                + "0\t\t5\t\t10\n\n\n";
        String expected1 = "IBM offers:\n\n"
                + "Sell\tPrice\tBuy\n"
                + "13\t\t14\t\t0\n"
                + "0\t\t12\t\t13\n\n\n";
        String expected2 = "Tesla Ltd. offers:\n\n"
                + "Sell\tPrice\tBuy\n"
                + "5\t\t205\t\t0\n"
                + "0\t\t200\t\t1\n\n";
        String expected = expected0 + expected1 + expected2;
        String result = exchangePlatform.getFullView();
        assertThat(result, is(expected));
    }
}