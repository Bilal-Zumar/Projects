
//import java.util.*;
import arrayList.*;

public class TestArrayList
{

	/**
	 * @param args
	 */

   // note, this method tests the ArrayList size() method
   public static void printArray(String msg, ArrayList a)
   {
      if (msg != null)
         System.out.println(msg+ ":");

      for (int k=0; k<a.size(); k++)
      {
         System.out.printf("%5s,", a.get(k));
         if ((k+1) %10 == 0)
            System.out.println();
      }
      System.out.println();
   }

   public static void printTestHeader( int testNum, String msg )
   {
      System.out.println("\n=================================================================");
      System.out.println("Test: " + testNum + ". " + msg + "\n");
   }
   public static void printTestTrailer(  )
   {
      System.out.println("=================================================================\n");
   }

   public static void main(String[] args)
   {
      int testCount = 1;
      int element=0;

      /*
       * PLEASE READ: uncomment each test as you go to simplify debugging.
       * Note printArray calls the ArrayList methods: get(index) and size(), so start off implementing
       *      the constructors, size, get, and add.
       */
      printTestHeader(testCount++,  "create an ArrayList, add 2 elements, then call size()");
      ArrayList myList = new ArrayList();
      //ArrayList<Integer> myList = new ArrayList<Integer>();
      myList.add(32);
      myList.add(16);
      int size = myList.size();
      System.out.println("size of array is: " + size);
      printArray("myList", myList);
      printTestTrailer();

      /* ===============================================
       * Test: get method
       * ===============================================
       */

      printTestHeader(testCount++,  "test get(index)");
      element = myList.get(0);
      System.out.println("element at 0: " + element);
      element = myList.get(1);
      System.out.println("element at 1: " + element);
      printTestTrailer();

      /* ==================================================================================
       *  Test the add: forcing the ArrayList to increase the size of the internal array.
       * ==================================================================================
       */


      printTestHeader(testCount++,  "add several more elements forcing a call to expandArray()");
      for (int k=0; k<10; k++)
         myList.add(k + 5);
      printArray(null, myList);
      printTestTrailer();


      /* ===============================================
       * Test:  set method
       * ===============================================
       */
      printTestHeader(testCount++,  "test set method, set 1st element to 100, middle to 555 and last to 999 ");
      element = myList.set(0, 100);
      System.out.println("previous value at 0 was: " + element);
      int middle = (myList.size()-1)/2;
      element = myList.set(middle, 555);
      System.out.println("previous value at " + middle + " was: " + element);
      element = myList.set(myList.size()-1, 999);
      System.out.println("previous value at last index was: " + element);
      printArray(null, myList);
      printTestTrailer();


      /* ===============================================
       * Test: indexOf method
       * ===============================================
       */
      printTestHeader(testCount++,  "test indexOf method, find specified element at which index ");
      printArray(null, myList);
      int index = myList.indexOf(12);
      System.out.println("found element 12 at: " + index);
      index = myList.indexOf(100);
      System.out.println("found element 100 at: " + index);
      index = myList.indexOf(999);
      System.out.println("found element 999 at: " + index);
      index = myList.indexOf(123);
      System.out.println("found element 123 at: " + index);
      printTestTrailer();


      /* ===============================================
       * Test: adds at a specific index
       * ===============================================
       */
      printTestHeader(testCount++,  "test add (int index, Integer element) ");
      printArray(null, myList);

      System.out.println("adding 1111 at index 0: ");
      myList.add(0, 1111);
      printArray(null, myList);

      System.out.println("adding 2222 at index : " + myList.size()/2);
      myList.add(myList.size()/2, 2222);
      printArray(null, myList);

      System.out.println("adding 3333 at the end, index : " + myList.size());
      myList.add(myList.size(), 3333);
      printArray(null, myList);

      printTestTrailer();

      /* ===============================================
       * Test: removes
       * ===============================================
       */
      printTestHeader(testCount++,  "test removes, the last element, the first, the middle ");


      System.out.println("after removing last element");
      int lastElement = myList.remove(myList.size() - 1);
      printArray("elm: "+lastElement, myList);

      System.out.println("after removing first element");
      int firstElement = myList.remove(0);
      printArray("elm: "+firstElement, myList);

      System.out.println("after removing middle element");
      int midElement = myList.remove(myList.size()/2);
      printArray("elm: "+midElement, myList);
      printTestTrailer();


      /* ====================================================================
       * Test: invalid indices - should generate error messages...for now...
       * ====================================================================
       */
      printTestHeader(testCount++,  "test invalid indices ");

      System.out.println("testing invalid add");
      myList.add(myList.size() + 1, 1111);

      System.out.println("testing invalid get");
      Integer badValRef = myList.get(myList.size());
      System.out.println("get returned: " + badValRef);

      System.out.println("testing invalid remove");
      badValRef = myList.remove(myList.size() + 1);

      printTestTrailer();

   }

}
