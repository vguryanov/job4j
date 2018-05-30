package ru.job4j.stockexchange;

/**
 * Created by User2 on 30.05.2018.
 */
class Task {
    private Type type;
    private Offer offer;

    public enum Type {
        ADD, REMOVE
    }

    Task(Type type, Offer offer) {
        this.type = type;
        this.offer = offer;
    }

    public Type getType() {
        return type;
    }

    public Offer getOffer() {
        return offer;
    }
}
