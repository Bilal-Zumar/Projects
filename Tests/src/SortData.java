/*
 * Note:
 *      Get your sorting method to work first with a fixed short array of integers
 *      Then verify it works with a larger size array of both Integers and Strings.
 *          use populateWithIntegers(int size) - create a random array of Integers
 *          use populateWithStrings(int size)  - create a random array of Strings
 *
 *      Write the method, verifySort(Comparable [] a) to verify your array has been successfully
 *      sorted.
 *
 *      With Comparable, you will use the method, compareTo() to compare 2 elements.
 */


public class SortData {

    /*
     * populateWithIntegers
     *    create an array of a specified size of Integer objects
     *    returns a reference to an array of Comparable objects
     */
    private static Comparable[] populateWithIntegers(int length) {
        Comparable[] sArray = new Integer[length];

        int max = (int) Math.pow(2, 12);
        for (int k = 0; k < sArray.length; k++) {
            int val = (int) (Math.random() * max);
            sArray[k] = val;
        }
        return sArray;
    }

    /*
     * populateWithStrings
     *    create an array of a specified size of String objects
     *    returns a reference to an array of Comparable objects
     */
    private static Comparable[] populateWithStrings(int length) {
        Comparable[] sArray = new String[length];

        int maxLength = 5;
        for (int k = 0; k < sArray.length; k++) {
            int len = (int) (Math.random() * maxLength + 1);
            String str = "";
            for (int j = 0; j < len; j++)
                str += (char) (Math.random() * 26 + 'a');

            sArray[k] = str;
        }
        return sArray;
    }


    /*
     * Displays an array of Comparable objects
     *  - will space each object with at least 6 characters:  %6s
     */
    public static void printArray(String msg, Comparable[] tArray) {
        int k = 0;
        System.out.print(msg);
        for (Comparable cc : tArray) {
            System.out.printf("%6s ", cc);
            if (++k % 10 == 0)
                System.out.println();
        }
        System.out.println(" ");
    }


//    public static void selectionSort(Comparable[] arr) {
//        int n = arr.length;
//
//        for (int i = 0; i < n - 1; i++) {
//            int min_idx = i;
//            for (int j = i + 1; j < n; j++)
//                if (arr[j].compareTo(arr[min_idx]) < 0)
//                    min_idx = j;
//
//            Comparable temp = arr[min_idx];
//            arr[min_idx] = arr[i];
//            arr[i] = temp;
//        }
//    }

    public static void insertionSort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            Comparable key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }


    /*
     * verifySort - traverse your array and verify it's in a sorted order:
     *               a[i-1] <= a[i]
     *            - return true if it's sorted.
     */
    public static boolean verifySort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static void mergeSortHelper(Comparable [] a, Comparable [] copyBuffer,
                                       int low, int high) {
        if (low < high) {
            int mid = (low+high)/2;

            mergeSortHelper(a, copyBuffer, low, mid);
            mergeSortHelper(a, copyBuffer, mid+1, high);
            merge (a, copyBuffer, low, mid, high);
        }
    }

    public static void merge(Comparable[] a, Comparable[] copyBuffer, int low, int middle, int high) {
// copy all items from the original array to the copy buffer
        for (int i = low; i <= high; i++) {
            copyBuffer[i] = a[i];
        }

        int i = low; // pointer for the left side of the array
        int j = middle + 1; // pointer for the right side of the array
        int k = low; // pointer for the copy buffer

// compare and copy the smallest element from either the left or right side of the array to the original array
        while (i <= middle && j <= high) {
            if (copyBuffer[i].compareTo(copyBuffer[j]) <= 0) {
                a[k] = copyBuffer[i];
                i++;
            } else {
                a[k] = copyBuffer[j];
                j++;
            }
            k++;
        }

// copy any remaining elements from the left side of the array to the original array
        while (i <= middle) {
            a[k] = copyBuffer[i];
            i++;
            k++;
        }

// copy any remaining elements from the right side of the array to the original array
        while (j <= high) {
            a[k] = copyBuffer[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(Comparable [] arr)
    {
        Comparable [] cb = new Comparable [arr.length];
        mergeSortHelper(arr, cb, 0, arr.length-1);
    }


    /*
     * first start out with a fixed array of integers to get your sort method to work
     * then verify it works with populateWithIntegers() and populateWithStrings()
     *
     * When you are done, leave no print statements in your sort or verifySort methods.
     * You will eventually call these methods with very large array sizes....
     */
    public static void main(String[] args) {

//        Comparable[] arr = {12, 8, 55, 22, 44, 11, 3, 12, 12, 3};
//        Comparable [] arr = {"abc", "bear", "aaa", "bat", "aa", "berry", "bats", "a", "abc", "bear"};
//        Comparable [] arr = populateWithIntegers(50);
        Comparable [] arr = populateWithStrings(50);

        printArray("before\n", arr);
        insertionSort(arr);
        printArray("after\n", arr);

        boolean result = verifySort(arr);
        System.out.println("Array successfully sorted: " + result);


    }

}
