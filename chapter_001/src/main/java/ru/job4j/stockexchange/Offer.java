package ru.job4j.stockexchange;

/**
 * Created by User2 on 30.05.2018.
 */
public class Offer implements Comparable<Offer> {
    private static int idCounter;
    private int id;
    private int price, volume;
    private ExchangePlatform.OfferType type;
    private String emitterName;

    public Offer(int id, String emitterName) {
        this.id = id;
        this.emitterName = emitterName;
    }

    public Offer(ExchangePlatform.OfferType type, int price, int volume, String emitterName) {
        this.type = type;
        this.id = ++idCounter;
        this.price = price;
        this.volume = volume;
        this.emitterName = emitterName;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public void increaseVolume(int amount) {
        this.volume += amount;
    }

    public ExchangePlatform.OfferType getType() {
        return type;
    }

    public String getEmitterName() {
        return emitterName;
    }

    public static void resetIdCounter() {
        idCounter = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Offer)) {
            return false;
        }
        Offer offer = (Offer) o;
        return id == offer.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(Offer o) {
        return Integer.compare(o.price, this.price);
    }
}
