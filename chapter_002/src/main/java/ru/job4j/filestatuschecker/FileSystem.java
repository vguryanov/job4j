package ru.job4j.filestatuschecker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User2 on 07.05.2018.
 */
public class FileSystem {
    private static Set<String> files = new HashSet<>();

    private FileSystem() {
    }

    public static void add(String... filePaths) {
        files.addAll(Arrays.asList(filePaths));
    }

    public static Set<String> getFiles() {
        return new HashSet<>(files);
    }

    public static boolean removeFiles(String... filePaths) {
        return files.removeAll(Arrays.asList(filePaths));
    }

    public static void clear() {
        files.clear();
    }
}
