package ru.job4j.filestatuschecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by User2 on 07.05.2018.
 */
public class ModificationsRegistry {
    private Map<String, FileStatus> lastModifications = new HashMap<>();
    private Set<String> lastFileSystemSnapshot = new HashSet<>();

    public void scanFileSystemForModifications() {
        Map<String, FileStatus> modifications = new HashMap<>();
        Set<String> scannedFilePaths = FileSystem.getFiles();
        for (String filePath : scannedFilePaths) {
            if (!lastFileSystemSnapshot.contains(filePath)) {
                modifications.put(filePath, FileStatus.Added);
            }
        }
        for (String filePath : lastFileSystemSnapshot) {
            if (!scannedFilePaths.contains(filePath)) {
                modifications.put(filePath, FileStatus.Removed);
            }
        }
        lastFileSystemSnapshot = new HashSet<>(scannedFilePaths);
        if (modifications.size() > 0) {
            lastModifications = modifications;
        }
    }

    public Map<String, FileStatus> getLastModifications() {
        return new HashMap<>(lastModifications);
    }

    public FileStatus getLastModificationStatusOfFile(String filePath) {
        return lastModifications.get(filePath);
    }
}
