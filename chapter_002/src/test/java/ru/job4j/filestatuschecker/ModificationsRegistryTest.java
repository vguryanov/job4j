package ru.job4j.filestatuschecker;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 07.05.2018.
 */
public class ModificationsRegistryTest {
    @After
    public void after() {
        FileSystem.clear();
    }

    @Test
    public void ifAddingThenStatusOfModificationIsAdded() {
        String testFilePath = "file1";
        FileSystem.add(testFilePath);
        ModificationsRegistry testRegistry = new ModificationsRegistry();
        testRegistry.scanFileSystemForModifications();
        FileStatus result = testRegistry.getLastModificationStatusOfFile(testFilePath);
        FileStatus expected = FileStatus.Added;
        assertThat(result, is(expected));
    }

    @Test
    public void ifRemovingThenStatusOfModificationIsRemoved() {
        String testFilePath = "file1";
        FileSystem.add(testFilePath);
        ModificationsRegistry testRegistry = new ModificationsRegistry();
        testRegistry.scanFileSystemForModifications();
        FileSystem.removeFiles(testFilePath);
        testRegistry.scanFileSystemForModifications();
        FileStatus result = testRegistry.getLastModificationStatusOfFile(testFilePath);
        FileStatus expected = FileStatus.Removed;
        assertThat(result, is(expected));
    }
}