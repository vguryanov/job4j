package ru.job4j.filestatuschecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Contains main logic. Scan filesystem, then compares scanned files list with files list saved on last scanning.
 * If some files have been modified then last modifications list will be rewritten.
 */
public class ModificationsRegistry {
    private FileSystem fileSystem;

    /**
     * Field that keeps map with file paths and their last modifications types
     */
    private Map<String, FileStatus> lastModifications = new HashMap<>();

    /**
     * Field that contains set of files saved on last filesystem scan. Needed for comparison of file statuses during
     * scanning for files modifications.
     */
    private Set<String> lastFileSystemSnapshot = new HashSet<>();

    public ModificationsRegistry(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    public void scanFileSystemForModifications() {
        Map<String, FileStatus> modifications = new HashMap<>();
        Set<String> scannedFilePaths = fileSystem.getFiles();
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
