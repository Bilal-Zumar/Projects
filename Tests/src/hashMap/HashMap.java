package hashMap;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class HashMap<K, V> {

    private final int MAX_ARRAY_SIZE = 10;
    private ArrayList<MapEntry<K, V>>[] myHashArray;
    private int mySize = 0;

    //stats - phase 2
    private int numArrayResized = 0;
    private long lastTimeToResize = 0;
    private long totalTimeToResize = 0;

    public HashMap(int size) {
        myHashArray = (ArrayList<MapEntry<K, V>>[]) new ArrayList[size];
    }

    public HashMap() {
        this(10);
    }

    public V put(K key, V value) {
        int index =Math.abs(key.hashCode()) % myHashArray.length;
        ArrayList<MapEntry<K, V>> arr = myHashArray[index];
        if (arr == null) {
            arr = new ArrayList<MapEntry<K, V>>();
            myHashArray[index] = arr;
        }
        for (int j = 0; j < arr.size(); j++) {
            MapEntry<K, V> entry = arr.get(j);
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        arr.add(new MapEntry<K, V>(key, value));
        mySize++;
        if (mySize > myHashArray.length * MAX_ARRAY_SIZE) {
            resizeHashArray();
        }
        return null;
    }

    public V get(K key) {
        int index = Math.abs(key.hashCode()) % myHashArray.length;
        ArrayList<MapEntry<K, V>> arr = myHashArray[index];
        if (arr != null) {
            for (int j = 0; j < arr.size(); j++) {
                MapEntry<K, V> entry = arr.get(j);
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }


    public int size() {
        return mySize;
    }

    public Set<K> keySet() {
        Set<K> mySet = new HashSet<K>();

        //traverse out array
        for (int k = 0; k < myHashArray.length; k++) {
            ArrayList<MapEntry<K, V>> arr = myHashArray[k];
            if (arr != null)
                for (int j = 0; j < arr.size(); j++) {
                    mySet.add(arr.get(j).getKey());
                }
        }
        return mySet;
    }


    //Phase 2
    public void resizeHashArray() {

        long startTime = System.nanoTime();

        // Save a temp reference to the existing static array
        ArrayList<MapEntry<K, V>>[] tempHashArray = myHashArray;
        // Allocate a new static array that is twice the length as the existing array
        myHashArray = (ArrayList<MapEntry<K, V>>[]) new ArrayList[MAX_ARRAY_SIZE * tempHashArray.length];
        // Reset the size of the hash map to 0
        mySize = 0;
        // Traverse the temp reference and call put() for all key,value pairs
        for (ArrayList<MapEntry<K, V>> arrayList : tempHashArray) {
            if (arrayList != null) {
                for (MapEntry<K, V> entry : arrayList) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }

        //stats
        numArrayResized++;
        long endTime = System.nanoTime();
        lastTimeToResize = endTime - startTime;
        totalTimeToResize += lastTimeToResize;
    }

    public String toString() {
        String str = "";
        for (int k = 0; k < myHashArray.length; k++) {
            ArrayList<MapEntry<K, V>> arr = myHashArray[k];
            str += String.format("%2s: ", k);  //index
            if (arr == null)
                str += "null";
            else
                for (int j = 0; j < arr.size(); j++) {
                    str += String.format("%s ", arr.get(j));  //calls MapEntry's toString()
                }
            str += "\n";
        }
        return str;
    }

    public String getStats() {
        String str = "number of times static array was resized: " + numArrayResized + "\n";
        str += String.format("last time it took to resize array: %.2f usecs\n", lastTimeToResize / 1000.0);
        str += String.format("total time it took to resize array: %.2f usecs\n", totalTimeToResize / 1000.0);
        str += String.format("static array size: %d,  number of elements: %d\n", myHashArray.length, size());

        int max = 0;
        int cntNulls = 0;
        double avg = 0;
        int totalArrayLists = 0;

        // Traverse the hash table and compute the max and average ArrayList lengths
        for (ArrayList<MapEntry<K, V>> arrayList : myHashArray) {
            if (arrayList == null) {
                cntNulls++;
                continue;
            }

            int arrayListSize = arrayList.size();
            totalArrayLists++;
            avg += (double) arrayListSize;
            if (arrayListSize > max) {
                max = arrayListSize;
            }
        }

        // Compute the average ArrayList length
        if (totalArrayLists > 0) {
            avg /= totalArrayLists;
        }

        str += String.format("max ArrayList size: %d,  average AL size: %.2f\n", max, avg);
        str += String.format("AL size threshold: %d,  num null entries: %d\n", MAX_ARRAY_SIZE, cntNulls);
        return str;
    }


    public static void testGet(HashMap<String, Integer> hmap, String str) {
        System.out.print("testGet: key=" + str + ", found: ");
        Integer value = hmap.get(str);
        if (value == null)
            System.out.println("false");
        else
            System.out.println("true (" + value + ")");

    }

    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, Integer> myDictionary = new HashMap<String, Integer>(5);
        System.out.println(myDictionary);
        myDictionary.put("dog", 11);
        myDictionary.put("cat", 22);
        myDictionary.put("hog", 33);
        myDictionary.put("frog", 44);
        myDictionary.put("bee", 55);
        myDictionary.put("abc", 66);
        myDictionary.put("flea", 77);
        System.out.println(myDictionary);

        System.out.println("\nTesting get(key)");

        testGet(myDictionary, "dog");
        testGet(myDictionary, "cat");
        testGet(myDictionary, "hog");
        testGet(myDictionary, "frog");
        testGet(myDictionary, "bee");
        testGet(myDictionary, "abc");
        testGet(myDictionary, "flea");
        testGet(myDictionary, "notInThere");
        testGet(myDictionary, "a key can contain spaces...");

        System.out.println("\nTesting keySet");
        Set<String> mySet = myDictionary.keySet();
        System.out.println("keys: " + mySet + ", size=" + mySet.size());

        System.out.println("\nAdding more gets - Phase 2: we'll resize the static array");
        int dupCnt = 0;
        int baseCnt = 100;
        for (int k = 0; k < 20; k++) {
            String str = "";
            for (int j = 0; j < 3; j++)
                str += (char) (Math.random() * 26 + 'a');

            Integer result = myDictionary.put(str, baseCnt++);
            if (result != null)
                dupCnt++;

            if (k + 1 % 20 == 0)
                System.out.println(myDictionary);
        }
        System.out.println(myDictionary);
        System.out.println("duplicate cnt: " + dupCnt);
        System.out.println("Container cnt: " + myDictionary.size());
    }
}
