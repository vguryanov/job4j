package ru.job4j.filestatuschecker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that imitates file system
 */
public class FileSystem {
    private static Set<String> files = new HashSet<>();

    public void add(String... filePaths) {
        files.addAll(Arrays.asList(filePaths));
    }

    public Set<String> getFiles() {
        return new HashSet<>(files);
    }

    public boolean removeFiles(String... filePaths) {
        return files.removeAll(Arrays.asList(filePaths));
    }

    public void clear() {
        files.clear();
    }
}
