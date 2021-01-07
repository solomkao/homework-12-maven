/*
 * @author Oksana Solomka
 * @version 1.0
 */
package com.solomka;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public final class Main {
    private Main() {
    }

    /**
     * @param args empty args
     */
    public static void main(final String[] args) {
        File lyricsFile = new File("src/main/resources/data.txt");
        List<String> list = FileAccess.readFile(lyricsFile);
        //Task #1
        System.out.printf("The number of words in text file: %s%n",
                list.size());
        System.out.println("Words from file " + list);

        //Task #2
        final int numberOfLetters = 3;
        File swearWordsFile = new File("src/main/resources/swear-words.txt");
        List<String> alteredList = StringUtil.
                excludeSwearAndNLengthWords(list,
                        swearWordsFile,
                        numberOfLetters);
        System.out.println("Bunch of words, except swear words and <"
                + numberOfLetters + "-letters words " + alteredList);
        System.out.printf("The number of words in the list: %s%n",
                alteredList.size());

        //Task #3
        System.out.printf("%d words were excluded from list.%n",
                (list.size() - alteredList.size()));
        String[] array = StringUtil
                .excludedWords(list, swearWordsFile, numberOfLetters);
        System.out.println("Excluded words " + Arrays.toString(array));

        //Task #4
        final int numberOfHighFrequencyWords = 5;
        List<String> highFrequencyWords = StringUtil
                .highFrequencyWords(alteredList, numberOfHighFrequencyWords);
        System.out.println("High frequency words " + highFrequencyWords);
    }


}
