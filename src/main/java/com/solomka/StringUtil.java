/*
 * @author Oksana Solomka
 * @version 1.0
 */
package com.solomka;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public final class StringUtil {
    private StringUtil() {
    }

    /**
     * @param words list of words
     * @param file  file to read swear words that have to be excluded
     * @param n     size words that have to be excluded
     * @return new list of words, except swear words and n-letter words
     */
    public static List<String> excludeSwearAndNLengthWords(final List<String> words,
                                                           final File file, final int n) {
        List<String> swearWords = FileAccess.readFile(file);
        List<String> newWords = new ArrayList<>();
        for (String word : words) {
            if (!swearWords.contains(word) && word.length() > n) {
                newWords.add(word);
            }
        }
        return newWords;
    }

    /**
     * @param words list of words
     * @param file  file to read swear words that have to be excluded
     * @param n     size words that have to be excluded
     * @return list of excluded words
     */
    public static String[] excludedWords(final List<String> words,
                                         final File file, final int n) {
        List<String> excludedWords = new ArrayList<>();
        List<String> swearWords = FileAccess.readFile(file);
        for (String word : words) {
            if (swearWords.contains(word) || word.length() <= n) {
                excludedWords.add(word);
            }
        }
        return excludedWords.toArray(new String[0]);
    }

    /**
     * @param words  list of words
     * @param number number of high frequency words that have to be found
     * @return list of of high frequency words
     */
    public static List<String> highFrequencyWords(final List<String> words,
                                                  final int number) {
        Map<String, Integer> resultMap = new HashMap<>();
        List<String> highFrequencyWords = new ArrayList<>();
        for (String word : words) {
            if (!resultMap.containsKey(word)) {
                resultMap.put(word, 1);
            } else {
                int value = resultMap.get(word);
                resultMap.put(word, value + 1);
            }
        }
        List<Map.Entry<String, Integer>> mapEntry = resultMap.entrySet()
                .stream()
                .sorted((x, y) -> y.getValue()
                        .compareTo(x.getValue()))
                .collect(Collectors.toList());
        for (int i = 0; i < number; i++) {
            highFrequencyWords.add(mapEntry.get(i).getKey());
        }
        return highFrequencyWords;
    }
}
