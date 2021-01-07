package com.solomka;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class FileAccess {
    private FileAccess() {
    }

    @NotNull
    public static List<String> readFile(final File file) {
        final List<String> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                words.addAll(Arrays.stream(line.split("\\p{Blank}+"))
                        .map(s -> s.replaceAll("[,.:;?!\"(){}\\[\\]]", " "))
                        .map(String::strip)
                        .filter(s -> !s.isBlank())
                        .map(String::toLowerCase)
                        .collect(Collectors.toList()));
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File doesn't exist.");
        }
        return words;
    }
}
