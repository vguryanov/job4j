package ru.job4j.cache;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * Created by User2 on 02.06.2018.
 */
public class NonBlockingCacheTest {
    private boolean thrown = false;

    @Test
    public void update() throws Exception {
        NonBlockingCache cache = new NonBlockingCache();
        Base b1 = new Base("obj1");
        cache.add(b1);

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        for (int i = 0; i < 100; i++) {
            Runnable r = () -> {
                try {
                    b1.setName("" + (int) (Math.random() * 10000));
                    cache.update(b1);
                } catch (OptimisticException e) {
                    thrown = true;
                }
            };
            service.execute(r);
        }
        Thread.sleep(1000);
        assertTrue("", thrown);
    }
}