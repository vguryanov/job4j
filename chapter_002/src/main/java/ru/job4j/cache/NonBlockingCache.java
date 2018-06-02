package ru.job4j.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by User2 on 01.06.2018.
 */
public class NonBlockingCache {
    private ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    void add(Base model) {
        map.put(model.getId(), model);
    }

    void update(Base model) {
        map.computeIfPresent(model.getId(), (integer, base) -> {
            if (model.getVersion() != base.getVersion() + 1) {
                throw new OptimisticException();
            }
            return model;
        });
    }

    void delete(Base model) {
        map.remove(model);
    }
}
