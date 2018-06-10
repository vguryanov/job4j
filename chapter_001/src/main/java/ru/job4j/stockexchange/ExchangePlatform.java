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
        switch (task.getType()) {
            case ADD:
                return addOffer(task.getOffer());
            case REMOVE:
                return removeOffer(task.getOffer());
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

    boolean createOffer(Offer offer) {
        return tasks.offer(
                new Task(
                        Task.Type.ADD,
                        offer
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
        return buckets.get(offer.getEmitterName()).addOffer(offer);
    }

    private boolean removeOffer(Offer o) {
        return buckets.get(o.getEmitterName()).removeOffer(o);
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
                if (offer.getId() == o.getId()) {
                    offersToBuy.remove(offer);
                    return true;
                }
            }
            for (Offer offer : offersToSell) {
                if (offer.getId() == o.getId()) {
                    offersToSell.remove(offer);
                    return true;
                }
            }
            return false;
        }

        private boolean addOffer(Offer o) {
            if (checkPossibleDeal(o) > 0) {
                TreeMultiset<Offer> resultedSet = o.getType() == OfferType.BUY ? offersToBuy : offersToSell;
                return resultedSet.add(o);
            }
            return false;
        }

        private int checkPossibleDeal(Offer o) {
            TreeMultiset<Offer> offersForDeal = o.getType() == OfferType.BUY ? offersToSell : offersToBuy;
            BiPredicate<Offer, Offer> isDealPossible = (offer1, offer2) ->
                    o.getType() == OfferType.BUY
                            ? offer1.getPrice() >= offer2.getPrice()
                            : offer1.getPrice() <= offer2.getPrice();

            ArrayList<Offer> offersForRemove = new ArrayList<>();
            for (Offer offer : offersForDeal) {
                if (isDealPossible.test(o, offer)) {
                    int dealVolume = Math.min(o.getVolume(), offer.getVolume());
                    o.increaseVolume(-dealVolume);
                    offer.increaseVolume(-dealVolume);
                    if (offer.getVolume() == 0) {
                        offersForRemove.add(offer);
                    }
                    if (o.getVolume() == 0) {
                        break;
                    }
                }
            }
            offersForDeal.removeAll(offersForRemove);
            return o.getVolume();
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
                int price = o.getPrice();
                if (!result.containsKey(price)) {
                    result.put(price, new ViewContainer());
                }
                result.get(price).bVolume += o.getVolume();
            }
            for (Offer o : offersToSell) {
                int price = o.getPrice();
                if (!result.containsKey(price)) {
                    result.put(price, new ViewContainer());
                }
                result.get(price).sVolume += o.getVolume();
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
