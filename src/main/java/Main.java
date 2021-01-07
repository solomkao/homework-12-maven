import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File lyricsFile = new File("src/main/resources/data.txt");
        List<String> list = FileAccess.readFile(lyricsFile);
        //Task #1 Підрахунок загальної кількості слів в тексті
        System.out.printf("The number of words in text file: %s%n",list.size());
        System.out.println("Words from file " + list);

        //Task #2 Виключити нецензурні слова && слова, довжина яких менше 3 символів
        int numberOfLetters = 3;
        File swearWordsFile = new File("src/main/resources/swear-words.txt");
        List<String>alteredList = StringUtil.excludeSwearAndNlengthWords(list,swearWordsFile,numberOfLetters);
        System.out.println("Bunch of words, except swear words and <"+numberOfLetters+"-letters words " + alteredList);
        System.out.printf("The number of words in the list: %s%n",alteredList.size());

        //Task #3 Підрахувати кількість слів, які необхідно виключити та записати їх в окремий масив
        System.out.printf("%d words were excluded from list.%n", (list.size() - alteredList.size()));
        String[]array = StringUtil.excludedWords(list,swearWordsFile,numberOfLetters);
        System.out.println("Excluded words " + Arrays.toString(array));

        //Task #4 Вивести N слів, які зустрічаються найчастіше
        int number = 5;
        List<String> highFrequencyWords = StringUtil.highFrequencyWords(alteredList,number);
        System.out.println("High frequency words " + highFrequencyWords);


    }


}
