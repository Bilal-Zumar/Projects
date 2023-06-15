import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class CompareSearchMethods {
    /*
     * numCompares will be initialized to 0 at the beginning of each search method and
     * will be incremented each time compareTo() is called.  The variable will be dislayed
     * when the results of the search method is displayed.
     */
    public static int numCompares = 0;

    public static int linearSearch(Comparable[] words, Comparable item) {
        numCompares = 0;
        for (int i = 0; i < words.length; i++) {
            numCompares++;
            if (words[i].compareTo(item) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static int recBinarySearch(Comparable[] words, Comparable item) {
        numCompares = 0;
        return recBinarySearch(words, item, 0, words.length - 1);
    }

    private static int recBinarySearch(Comparable[] words, Comparable item, int low, int high) {
        if (high < low)
            return -1;
        int mid = (low + high) / 2;
        numCompares++;
        if (words[mid].compareTo(item) == 0)
            return mid;
        else if (words[mid].compareTo(item) > 0)
            return recBinarySearch(words, item, low, mid - 1);
        else
            return recBinarySearch(words, item, mid + 1, high);
    }

    public static int binarySearch(Comparable[] words, Comparable item) {
        numCompares = 0;
        int low = 0;
        int high = words.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            numCompares++;
            if (words[mid].compareTo(item) == 0) {
                return mid;
            } else if (words[mid].compareTo(item) > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        String fileName = "Dictionary.txt";
        Scanner infile = new Scanner(new File(fileName));
        int count = 0;
        while (infile.hasNext()) {
            String s = infile.next();
            count++;
        }
        infile.close();

        //reopen file
        infile = new Scanner(new File(fileName));
        //populate wordlist array, then close file
        Comparable[] wordlist = new String[count];
        for (int x = 0; x < count; x++)
            wordlist[x] = infile.next();

        infile.close();

        //call your merge sort method
        SortData.mergeSort(wordlist);


        System.out.println("Entering main loop prompting user for word to lookup");
        while (true) {
            //prompt user for a word to look up
            String s = JOptionPane.showInputDialog("Word? (Type -1 to quit.)");
            if (s == null || s.equals("-1"))
                break;          //break out of while(true) loop

            //find the word using a linear search method
            System.out.println("word: " + s);
            long start = System.nanoTime();
            int index = linearSearch(wordlist, s);
            long end = System.nanoTime();
            int timeUsec = (int) ((end - start) / 1000);
            System.out.println("linear search, index=" + index + ", time (usecs): " + timeUsec + ", numCompares: " + numCompares);

            /*
             * you can ignore this comment:
             * the cpu will cache data to speed up memory access. In order to compare both iterative and recursive
             * binary search, we need to level the playing field.  Otherwise the one called 2nd will be faster.
             */
            index = binarySearch(wordlist, s);


            //find the word using a recursive binary search method
            start = System.nanoTime();
            index = binarySearch(wordlist, s);
            end = System.nanoTime();
            timeUsec = (int) ((end - start) / 1000);
            System.out.println("binary search, index=" + index + ", time (usecs): " + timeUsec + ", numCompares: " + numCompares);


            //find the word using a linear search method
            start = System.nanoTime();
            index = recBinarySearch(wordlist, s);
            end = System.nanoTime();
            timeUsec = (int) ((end - start) / 1000);
            System.out.println("rec binary search, index=" + index + ", time (usecs): " + timeUsec + ", numCompares: " + numCompares);

        }

        System.out.println("Good-bye.");
        System.exit(0);
    }
}