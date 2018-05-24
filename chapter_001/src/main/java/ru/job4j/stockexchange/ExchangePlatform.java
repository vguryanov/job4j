package ru.job4j.stockexchange;

import com.google.common.collect.TreeMultiset;

import java.util.*;
import java.util.function.BiPredicate;

/**
 * Created by User2 on 20.05.2018.
 */
class ExchangePlatform {
    private int offersIdCounter;
    private Map<String, Bucket> buckets;
    private Queue<Task> tasks;

    public enum OfferType {
        BUY, SELL
    }

    ExchangePlatform() {
        this.buckets = new TreeMap<>();
        tasks = new LinkedList<>();
    }

    void addEmitter(String name) {
        buckets.put(name, new Bucket(name));
    }

    boolean work(boolean executeAllTasks) {
        if (!executeAllTasks) {
            return executeLastTask();
        }
        return executeAllTasks();
    }

    private boolean executeLastTask() {
        Task task = tasks.poll();
        switch (task.type) {
            case ADD:
                return addOffer(task.offer);
            case REMOVE:
                return removeOffer(task.offer);
            default:
                return false;
        }
    }

    private boolean executeAllTasks() {
        boolean result = true;
        while (!tasks.isEmpty()) {
            result &= executeLastTask();
        }
        return result;
    }

    boolean createOffer(OfferType type, int price, int volume, String emitterName) {
        return tasks.offer(
                new Task(
                        Task.Type.ADD,
                        new Offer(type, price, volume, emitterName)
                )
        );
    }

    boolean closeOffer(int id, String emitterName) {
        return tasks.offer(
                new Task(
                        Task.Type.REMOVE,
                        new Offer(id, emitterName)
                )
        );
    }

    private boolean addOffer(Offer offer) {
        return buckets.get(offer.emitterName).addOffer(offer);
    }

    private boolean removeOffer(Offer o) {
        return buckets.get(o.emitterName).removeOffer(o);
    }

    String getViewForEmitter(String emitterName) {
        return buckets.get(emitterName).getView();
    }

    String getFullView() {
        StringJoiner result = new StringJoiner("\n");
        for (Bucket bucket : buckets.values()) {
            result.add(bucket.getView());
        }
        return result.toString();
    }

    private static class Task {
        private Type type;
        private Offer offer;

        private enum Type {
            ADD, REMOVE
        }

        private Task(Type type, Offer offer) {
            this.type = type;
            this.offer = offer;
        }
    }

    public class Offer implements Comparable<Offer> {
        private int id;
        private int price, volume;
        private OfferType type;
        private String emitterName;

        private Offer(int id, String emitterName) {
            this.id = id;
            this.emitterName = emitterName;
        }

        private Offer(OfferType type, int price, int volume, String emitterName) {
            this.type = type;
            this.id = ++offersIdCounter;
            this.price = price;
            this.volume = volume;
            this.emitterName = emitterName;
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

    private static class Bucket {
        private String emitterName;
        private TreeMultiset<Offer> offersToBuy;
        private TreeMultiset<Offer> offersToSell;
        private static Comparator<Integer> viewComparator = (o1, o2) -> Integer.compare(o2, o1);

        private Bucket(String emitterName) {
            this.emitterName = emitterName;
            offersToBuy = TreeMultiset.create();
            offersToSell = TreeMultiset.create();
        }

        private boolean removeOffer(Offer o) {
            for (Offer offer : offersToBuy) {
                if (offer.id == o.id) {
                    offersToBuy.remove(offer);
                    return true;
                }
            }
            for (Offer offer : offersToSell) {
                if (offer.id == o.id) {
                    offersToSell.remove(offer);
                    return true;
                }
            }
            return false;
        }

        private boolean addOffer(Offer o) {
            if (checkPossibleDeal(o) > 0) {
                TreeMultiset<Offer> resultedSet = o.type == OfferType.BUY ? offersToBuy : offersToSell;
                return resultedSet.add(o);
            }
            return false;
        }

        private int checkPossibleDeal(Offer o) {
            TreeMultiset<Offer> offersForDeal = o.type == OfferType.BUY ? offersToSell : offersToBuy;
            BiPredicate<Offer, Offer> isDealPossible = (offer1, offer2) ->
                    o.type == OfferType.BUY
                            ? offer1.price >= offer2.price
                            : offer1.price <= offer2.price;

            ArrayList<Offer> offersForRemove = new ArrayList<>();
            for (Offer offer : offersForDeal) {
                if (isDealPossible.test(o, offer)) {
                    int dealVolume = Math.min(o.volume, offer.volume);
                    o.volume -= dealVolume;
                    offer.volume -= dealVolume;
                    if (offer.volume == 0) {
                        offersForRemove.add(offer);
                    }
                    if (o.volume == 0) {
                        break;
                    }
                }
            }
            offersForDeal.removeAll(offersForRemove);
            return o.volume;
        }

        private String getView() {
            StringJoiner view = new StringJoiner("\n");
            view.add(emitterName + " offers:");
            if (offersToBuy.size() == 0 && offersToSell.size() == 0) {
                view.add("no available offers.");
                return view.toString();
            }
            TreeMap<Integer, ViewContainer> result = new TreeMap<>(viewComparator);
            for (Offer o : offersToBuy) {
                int price = o.price;
                if (!result.containsKey(price)) {
                    result.put(price, new ViewContainer());
                }
                result.get(price).bVolume += o.volume;
            }
            for (Offer o : offersToSell) {
                int price = o.price;
                if (!result.containsKey(price)) {
                    result.put(price, new ViewContainer());
                }
                result.get(price).sVolume += o.volume;
            }
            view.add("\nSell\tPrice\tBuy");
            for (Map.Entry<Integer, ViewContainer> entry : result.entrySet()) {
                view.add(String.format("%d\t\t%d\t\t%d", entry.getValue().sVolume, entry.getKey(), entry.getValue().bVolume));
            }
            view.add("\n");
            return view.toString();
        }

        private class ViewContainer {
            private int bVolume, sVolume;
        }
    }
}
