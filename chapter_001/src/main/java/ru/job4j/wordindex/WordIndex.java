package ru.job4j.wordindex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User2 on 24.05.2018.
 */
public class WordIndex {
    private String fileContent;

    public WordIndex() {
    }

    public WordIndex(String filePath) {
        loadFile(filePath);
    }

    public String getFileContent() {
        return fileContent;
    }

    public void loadFile(String filename) {
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<Integer> getIndexes4Word(String searchWord) {
        IntegerTrieSet result = new IntegerTrieSet();
        Pattern pattern = Pattern.compile(searchWord, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(fileContent);

        while (matcher.find()) {
            int index = matcher.start();
            result.add(index);
        }

        return result;
    }
}
