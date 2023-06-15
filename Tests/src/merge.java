public class merge {
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

//    Uncomment to test
//    public static void main(String[] args) {
//        Comparable[] arr = {5, 2, 9, 1, 5, 6};
//        mergeSort(arr);
//        System.out.println(Arrays.toString(arr));
//    }
}
