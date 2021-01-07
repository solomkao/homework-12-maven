package com.solomka;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public final class StringUtil {
    public static List<String> excludeSwearAndNlengthWords(List<String> words, File file, int n) {
        List<String> resultList = excludeSwearWords(words, file);
        return excludeNlengthWords(resultList, n);
    }

    private static List<String> excludeSwearWords(List<String> words, File file) {
        List<String> swearWords = FileAccess.readFile(file);
        List<String> newWords = new ArrayList<>();
        for (String word : words) {
            if (!swearWords.contains(word)) {
                newWords.add(word);
            }
        }
        return newWords;
    }

    private static List<String> excludeNlengthWords(List<String> words, int n) {
        List<String> newWords = new ArrayList<>();
        for (String word : words) {
            if (word.length() > n) {
                newWords.add(word);
            }
        }
        return newWords;
    }

    public static String[] excludedWords(List<String> words, File file, int n) {
        List<String> excludedWords = new ArrayList<>();
        List<String> swearWords = FileAccess.readFile(file);
        for (String word : words) {
            if (swearWords.contains(word) || word.length() <= n) {
                excludedWords.add(word);
            }
        }
        return excludedWords.toArray(new String[0]);
    }

    public static List<String> highFrequencyWords(List<String> words, int number) {
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
        List<Map.Entry<String, Integer>> mapEntry = resultMap.entrySet().stream()
                .sorted((x, y) -> y.getValue().compareTo(x.getValue()))
                .collect(Collectors.toList());
        for (int i = 0; i < number; i++) {
            highFrequencyWords.add(mapEntry.get(i).getKey());
        }
        return highFrequencyWords;
    }
}
