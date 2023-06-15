package hashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DictionaryLookup {
    private static final String FILE_NAME = "dictionary.txt";

    public static void main(String[] args) throws IOException {
        String[] words = readWordsFromFile(FILE_NAME);
        Comparable[] comparableWords = Arrays.copyOf(words, words.length);
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
            hashMap.put(words[i], i);
        }
        System.out.println("HashMap stats:");
        System.out.println(hashMap.getStats());
        Arrays.sort(comparableWords);
        Random random = new Random();
        long hashTotalTime = 0;
        long binaryTotalTime = 0;
        int numLookups = 0;
        int errBinary = 0;
        int errHash = 0;
        int numIterations = 5000000;
        for (int i = 0; i < numIterations; i++) {
            int index = random.nextInt(words.length);
            String word = words[index];
            long startTime = System.nanoTime();
            int binaryIndex = Arrays.binarySearch(comparableWords, word);
            long endTime = System.nanoTime();
            if (binaryIndex < 0) {
                errBinary++;
            }
            binaryTotalTime += endTime - startTime;
            startTime = System.nanoTime();
            Integer hashMapIndex = hashMap.get(word);
            endTime = System.nanoTime();
            if (hashMapIndex == null) {
                errHash++;
            }
            hashTotalTime += endTime - startTime;
            numLookups++;
        }
        double hashAvgLookupTime = hashTotalTime / (double) numLookups;
        double binaryAvgLookupTime = binaryTotalTime / (double) numLookups;
        System.out.printf("HashMap average lookup time: %.2f ns\n", hashAvgLookupTime);
        System.out.printf("Binary search average lookup time: %.2f ns\n", binaryAvgLookupTime);
        System.out.printf("Number of errors in binary search: %d\n", errBinary);
        System.out.printf("Number of errors in HashMap lookup: %d\n", errHash);
    }

    private static String[] readWordsFromFile(String fileName) throws IOException {
        List<String> wordsList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] wordsInLine = line.split("\\s+");
            for (String word : wordsInLine) {
                wordsList.add(word);
            }
        }
        reader.close();
        String[] words = new String[wordsList.size()];
        words = wordsList.toArray(words);
        return words;
    }
}
