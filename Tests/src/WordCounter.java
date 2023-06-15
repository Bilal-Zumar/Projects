//name:      date:

import java.util.*;
import java.io.*;
public class WordCounter
{
   public static void main(String[] args) 
   {
      Scanner sc = new Scanner(System.in);
      String filename = "declaration.txt";

      try {
         Scanner infile = new Scanner(new File(filename));

         processFile(infile);

         infile.close();
      } catch (IOException e) {
         System.out.println("Error: " + e);
      }
      sc.close();
   }

   public static void processFile(Scanner infile) {
      /*
       * The HashMap uses <Key, Value> pair. The key is a word (String) and the value is a
       * reference to a WordFrequency object.
       *
       * Note the use of Generics.
       */
      HashMap<String, WordFrequency> myMap = new HashMap<String, WordFrequency>();

      while (infile.hasNext()) {
         String word = infile.next();
         // process word: lower case, remove puncuation
         word = word.toLowerCase();
         word = word.replaceAll("[^a-zA-Z0-9]", "");
         System.out.print(word + ", ");

         // use get to see if the word is in myMap
         WordFrequency wf = myMap.get(word);
         if (wf == null) {
            wf = new WordFrequency(word);
            myMap.put(word, wf);
         }
         wf.incrementCount();
      }
      System.out.println();

      /*
       * refer to the lab writeup for help with these steps
       */

      // create an array of WordFrequency references
      WordFrequency[] wfArray = new WordFrequency[myMap.size()];
      int i = 0;
      for (WordFrequency wf : myMap.values()) {
         wfArray[i++] = wf;
      }

      // use one of your SortData.java sort methods to sort the array of Comparable objects.
      SortData.mergeSort(wfArray);

      // traverse your array and call the toString method of WordFrequency. Refer to the lab writeup
      // for expected results
      for (WordFrequency wf : wfArray) {
         System.out.println(wf);
      }
   }

}
