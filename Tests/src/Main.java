public class Main {
    public static void main(String[] args) {
        CarInfoRetriever c = new CarInfoRetriever();
        c.readCars();
        c.writeFormattedCars();


        //bubblesort for unsorted data 
        c.comparisons = 0;
        c.swaps = 0;
        long t1 = System.nanoTime();
        //call the bubblesort method 
        c.bubbleSort();
        long t2 = System.nanoTime();
        System.out.printf("Bubble Sort Random: Comparisons= %d, Swaps=%d \n", c.comparisons, c.swaps);
        System.out.println("Bubble Sort for Random Cars Time: " + ((t2 - t1) / 1000000) + " ms");

        //bubblesort for sorted data 
        c.comparisons = 0;
        c.swaps = 0;
        long t3 = System.nanoTime();
        //call the bubblesort method 
        c.bubbleSort();
        long t4 = System.nanoTime();

        System.out.printf("Bubble Sort Sorted: Comparisons= %d, Swaps=%d \n", c.comparisons, c.swaps);
        System.out.println("Bubble Sort for Sorted Cars Time: " + ((t4 - t3) / 1000000) + " ms");
        System.out.println("___________________________________________________");

        c.writebsorted();
        c.readCars();

        //quicksort for unsorted data
        c.comparisons_2 = 0;
        c.swaps_2 = 0;
        long t5 = System.nanoTime();
        //call the quicksort method 
        c.quickSort();
        long t6 = System.nanoTime();
        System.out.printf("Quick Sort Random: Comparisons= %d, Swaps=%d \n", c.comparisons_2, c.swaps_2);
        System.out.println("Quick Sort for Random Cars Time: " + ((t6 - t5) / 1000000) + " ms");

        //quicksort for sorted data 
        c.comparisons_2 = 0;
        c.swaps_2 = 0;
        long t7 = System.nanoTime();
        //call the quicksort method 
        c.quickSort();
        long t8 = System.nanoTime();
        System.out.printf("Quick Sort Sorted: Comparisons= %d, Swaps=%d \n", c.comparisons_2, c.swaps_2);
        System.out.println("Quick Sort for Sorted Cars Time: " + ((t8 - t7) / 1000000) + " ms");
        System.out.println("___________________________________________________");

        c.writequicksort();
        c.readCars();

        //Heap sort for unsorted data
        c.comparisons_3 = 0;
        c.swaps_3 = 0;
        c.reheap = 0;
        long t9 = System.nanoTime();
        c.heapSort();
        long t10 = System.nanoTime();
        long time1 = (t10 - t9) / 1000000;


        System.out.printf("Heap Sort: Comparisons= %d, Swaps=%d, Reheap=%d\n", c.comparisons_3, c.swaps_3, c.reheap);
        System.out.printf("Heap Sort Random: Comparisons= %d, Swaps=%d, Reheaps down(s)=%d\n", c.comparisons_3, c.swaps_3, c.reheap);
        System.out.println("Heap Sort for Random Cars Time: " + time1 + " ms");


        //Heap sort for sorted data
        c.comparisons_3 = 0;
        c.swaps_3 = 0;
        c.reheap = 0;

        long t11 = System.nanoTime();
        c.heapSort();
        long t12 = System.nanoTime();
        long time = (t12 - t11) / 1000000;

        System.out.printf("Heap Sort: Comparisons= %d, Swaps=%d, Reheap=%d\n", c.comparisons_3, c.swaps_3, c.reheap);
        System.out.printf("Heap Sort Sorted: Comparisons= %d, Swaps=%d, Reheaps down(s)=%d \n", c.comparisons_3, c.swaps_3, c.reheap);
        System.out.println("Heap Sort for Sorted Cars Time: " + time + " ms");
        System.out.println("___________________________________________________");

        c.writeheapsort();
        c.readCars();


        //Merge sort for unsorted data
        c.comparisons_4 = 0;
        c.swaps_4 = 0;
        c.merge = 0;
        long t13 = System.nanoTime();
        c.mergeSort();
        long t14 = System.nanoTime();
        long time2 = (t14 - t13) / 1000000;


        System.out.printf("Merge Sort: Comparisons= %d, Swaps=%d, Merge Calls=%d\n", c.comparisons_4, c.swaps_4, c.merge);
        System.out.printf("Merge Sort Random: Comparisons= %d, Swaps=%d, Merge Calls=%d\n", c.comparisons_4, c.swaps_4, c.merge);
        System.out.println("Merge Sort for Random Cars Time: " + time2 + " ms");


        //Merge sort for sorted data
        c.comparisons_4 = 0;
        c.swaps_4 = 0;
        c.merge = 0;
        long t15 = System.nanoTime();
        c.mergeSort();
        long t16 = System.nanoTime();
        long time3 = (t16 - t15) / 1000000;


        System.out.printf("Merge Sort: Comparisons= %d, Swaps=%d, Merge Calls=%d\n", c.comparisons_4, c.swaps_4, c.merge);
        System.out.printf("Merge Sort Random: Comparisons= %d, Swaps=%d, Merge Calls=%d\n", c.comparisons_4, c.swaps_4, c.merge);
        System.out.println("Merge Sort for Sorted Cars Time: " + time3 + " ms");
        System.out.println("___________________________________________________");

        c.writemergesort();
    }
}





