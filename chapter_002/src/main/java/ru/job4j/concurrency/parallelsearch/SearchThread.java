package ru.job4j.concurrency.parallelsearch;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Created by User2 on 31.05.2018.
 */
public class SearchThread extends Thread {
    private final Path pathSource;
    private final String text;
    private final List<String> extensions;
    private final List<Future<Path>> result;
    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2 - 1);

    public SearchThread(String pathSource, String text, List<String> extensions, List<Future<Path>> result) {
        this.pathSource = Paths.get(pathSource);
        this.text = text;
        this.extensions = extensions;
        this.result = result;
    }

    @Override
    public void run() {
        try {
            Files.walkFileTree(
                    pathSource,
                    new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            String fileName = file.getFileName().toString();
                            for (String ext : extensions) {
                                if (fileName.endsWith(ext)) {
                                    addFuture(file);
                                }
                            }
                            return super.visitFile(file, attrs);
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    private void addFuture(final Path file) {
        result.add(
                CompletableFuture.supplyAsync(
                        () -> {
                            try {
                                String fileContent = new String(Files.readAllBytes(file));
                                if (fileContent.contains(text)) {
                                    return file;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        },
                        executorService
                )
        );
    }
}
