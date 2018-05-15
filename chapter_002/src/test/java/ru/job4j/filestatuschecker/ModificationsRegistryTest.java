package ru.job4j.filestatuschecker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by User2 on 07.05.2018.
 */
public class ModificationsRegistryTest {
    private FileSystem testFileSystem;

    @Before
    public void setUp(){
        testFileSystem = new FileSystem();
    }

    @After
    public void after() {
        testFileSystem.clear();
    }

    @Test
    public void ifAddingThenStatusOfModificationIsAdded() {
        String testFilePath = "file1";
        testFileSystem.add(testFilePath);
        ModificationsRegistry testRegistry = new ModificationsRegistry(testFileSystem);
        testRegistry.scanFileSystemForModifications();
        FileStatus result = testRegistry.getLastModificationStatusOfFile(testFilePath);
        FileStatus expected = FileStatus.Added;
        assertThat(result, is(expected));
    }

    @Test
    public void ifRemovingThenStatusOfModificationIsRemoved() {
        String testFilePath = "file1";
        testFileSystem.add(testFilePath);
        ModificationsRegistry testRegistry = new ModificationsRegistry(testFileSystem);
        testRegistry.scanFileSystemForModifications();
        testFileSystem.removeFiles(testFilePath);
        testRegistry.scanFileSystemForModifications();
        FileStatus result = testRegistry.getLastModificationStatusOfFile(testFilePath);
        FileStatus expected = FileStatus.Removed;
        assertThat(result, is(expected));
    }
}