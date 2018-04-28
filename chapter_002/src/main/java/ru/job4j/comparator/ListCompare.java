package ru.job4j.comparator;

import java.util.Comparator;

/**
 * Created by User2 on 27.04.2018.
 */
public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int len1 = left.toCharArray().length;
        int len2 = right.toCharArray().length;
        int lim = Math.min(len1, len2);
        char v1[] = left.toCharArray();
        char v2[] = right.toCharArray();

        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }
}
