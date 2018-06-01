package ru.job4j.concurrency.parallelsearch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;

import static java.lang.String.format;

/**
 * Created by User2 on 31.05.2018.
 */

@ThreadSafe
public class ParallelSearch {
    private final String root;
    private final String text;
    private final List<String> extensions;

    @GuardedBy("this")
    private final List<Future<Path>> futures = new ArrayList<>();

    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.extensions = exts;
    }

    public synchronized void init() throws Exception {
        Thread t = new SearchThread(root, text, extensions, futures);
        t.start();
        t.join();
    }

    synchronized List<String> result() throws Exception {
        ArrayList<String> result = new ArrayList<>();
        for (Future<Path> pathFuture : futures) {
            if (pathFuture.get() != null) {
                result.add(pathFuture.get().toString());
            }
        }
        return result;
    }
}
