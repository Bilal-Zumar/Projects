import java.io.*;
import java.text.*;
import java.util.Date;

public class CarInfoRetriever {
    //declare and initialize variables
    private static final String file = "cars.csv";
    private static final String output_file = "formatted_cars.txt";
    private static final String date_format = "yyyy-MM-dd";
    private static final int my_id = 85;
    private static final int cars_total = 1000;


    //array called "cars"
//array has "Car" objects 
    private Car[] cars = new Car[cars_total];


    public void readCars() {
        cars = new Car[cars_total];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            // skip header row
            br.readLine();

            //array index will increment 
            //keep on looping till it reaches cars_total 
            while ((line = br.readLine()) != null && i < cars_total) {

                //separate the three fields by a comma
                String[] f = line.split(",");

                //array has 3 fields in each line
                if (f.length == 3) {
                    //identifier of length 17 characters
                    String vin = f[0].trim() + my_id;

                    //fullname is made up of two part
                    //firstname and lastname
                    String[] fullname = f[1].trim().split(" ");
                    //first name and last name, separated by a space 
                    String first = fullname[0];
                    String last = fullname[1];
                    //car owner date of birth 
                    //DOB value is parsed into dob object 
                    Date dob = parseDate(f[2].trim());

                    //create a new "Car" object 
                    //store the object in the array 
                    cars[i] = new Car(vin, first, last, dob);

                    //increment the index of array
                    i++;
                }
            }
            //print error message if file is not found 

        } catch (IOException e) {
            System.out.printf("File not found");
        }
    }

    //create an output file of type txt 

    public void writeFormattedCars() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(output_file))) {
            pw.printf("%-6s %-17s %-25s %s%n", "No.", "VIN", "Name", "DOB");

            //No. starts ate id+1
            int id = my_id + 1;

            for (int i = 0; i < cars_total; i++) {
                if (cars[i] != null) {
                    pw.printf("%-6d %-17s %-25s %tF%n", id, cars[i].getVin(), cars[i].getName(), cars[i].getDob());
                    id++;
                }
            }
        } catch (IOException e) {
            System.out.printf("File not found");
        }
    }


    private static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(date_format);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.printf("File not found");
            return null;
        }
    }


    private static class Car {
        private String vin;
        private String name;
        private Date dob;

        //full constructor
        public Car(String vin, String first, String last, Date dob) {
            this.vin = vin;
            this.name = first + " " + last;
            this.dob = dob;
        }

        //getters and setters 
        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDob() {
            return dob;
        }


        public void setDob(Date dob) {
            this.dob = dob;
        }

    }

    public int swaps;
    public int comparisons;

    //swap method
    //used in the bubblesort method 
    public void swap(Car[] cars, int x, int y) {
        Car temp = cars[x];
        cars[x] = cars[y];
        cars[y] = temp;
    }

    //bubblesort method
    public void bubbleSort() {
        if (cars == null || cars.length == 0) {
            return;
        }
        int n = cars.length;
        boolean swapped = false;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (cars[j].getName().toLowerCase().compareTo(cars[j + 1].getName().toLowerCase()) > 0) {
                    swap(cars, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public void writebsorted() {
        // TODO Auto-generated method stub


        try (PrintWriter p = new PrintWriter(new FileWriter("bsorted.txt"))) {
            p.printf("%-6s %-20s %-15s %s %n", "No.", "Name", "DOB", "VIN");
            int id = my_id + 1;
            for (int i = 0; i < cars.length; i++) {
                if (cars[i] != null) {
                    p.printf("%-6d %-20s %-15tF %-17s%n", id, cars[i].getName(), cars[i].getDob(), cars[i].getVin());
                    id++;
                }
            }

        } catch (IOException e) {
            System.out.printf("Error writing to file bsorted.txt");
        }


    }

    public int swaps_2;
    public int comparisons_2;


    public void quicksort1(int F, int L) {
        int lastS1;
        String pivot;
        if (F < L) {
            pivot = cars[F].getName();
            lastS1 = F;
            for (int FU = F + 1; FU <= L; FU++) {
                comparisons_2++;
                if (cars[FU].getName().compareTo(pivot) < 0) {
                    swaps_2++;
                    lastS1++;
                    swap(cars, FU, lastS1);
                }
            }
            comparisons_2++;
            if (F != lastS1) {
                swaps_2++;
                swap(cars, F, lastS1);
            }
            comparisons_2++;
            if (F < (lastS1 - 1))
                quicksort1(F, lastS1 - 1);
            comparisons_2++;
            if ((lastS1 + 1) < L)
                quicksort1(lastS1 + 1, L);
        }

    }


    public void quickSort() {
        quicksort1(0, cars.length - 1);
    }


    public void writequicksort() {
        // TODO Auto-generated method stub
        try (PrintWriter q = new PrintWriter(new FileWriter("qsorted.txt"))) {
            q.printf("%-6s %-20s %-15s %s %n", "No.", "Name", "DOB", "VIN");
            int id = my_id + 1;
            for (int i = 0; i < cars.length; i++) {
                if (cars[i] != null) {
                    q.printf("%-6d %-20s %-15tF %-17s%n", id, cars[i].getName(), cars[i].getDob(), cars[i].getVin());
                    id++;
                }
            }

        } catch (IOException e) {
            System.out.printf("Error writing to file qsorted.txt");
        }

    }

    int comparisons_3 = 0;
    int swaps_3 = 0;
    int reheap = 0;

    public void reheapdown(int root, int end) {
        int child = 2 * root + 1;
        comparisons_3++;
        while (child <= end) {

            comparisons_3++;
            if (child < end && cars[child].getName().compareTo(cars[child + 1].getName()) < 0) {
                child++;
            }

            comparisons_3++;
            if (cars[root].getName().compareTo(cars[child].getName()) < 0) {
                swaps_3++;
                swap(cars, root, child);
                root = child;
                child = 2 * root + 1;
            } else {
                return;
            }
            reheap++;
        }
    }

    public void heapSort() {
        if (cars == null || cars.length == 0) {
            return;
        }

        int numValues = cars.length;

        // Convert array values[0..numValues-1] into a heap
        for (int index = numValues / 2 - 1; index >= 0; index--) {
            comparisons_3++;
            reheapdown(index, numValues - 1);
            reheap++;
        }

        // Sort the heap
        for (int index = numValues - 1; index >= 1; index--) {
            comparisons_3++;
            // Increment the number of swaps made

            // Swap the root node with the last node
            comparisons_3++;
            if (cars[0].getName().toLowerCase().compareTo(cars[index].getName().toLowerCase()) > 0) {
                swap(cars, 0, index);
                swaps_3++;
            }

            // Re-heapify the remaining nodes
            reheapdown(0, index - 1);
            reheap++;
        }
    }


    public void writeheapsort() {
        // TODO Auto-generated method stub
        try (PrintWriter h = new PrintWriter(new FileWriter("hsorted.txt"))) {
            h.printf("%-6s %-20s %-15s %s %n", "No.", "Name", "DOB", "VIN");
            int id = my_id + 1;
            for (int i = 0; i < cars.length; i++) {
                if (cars[i] != null) {
                    h.printf("%-6d %-20s %-15tF %-17s%n", id, cars[i].getName(), cars[i].getDob(), cars[i].getVin());
                    id++;
                }
            }

        } catch (IOException e) {
            System.out.printf("Error writing to file hsorted.txt");
        }

    }

    int swaps_4 = 0;
    int comparisons_4 = 0;
    int merge = 0;

    public void mergeSort() { //wrapper method/class to be called in main
        mergeSort(0, cars.length - 1);
    }

    public void mergeSort(int f, int l) {
        comparisons_4++;
        if (f < l) {
            int m = (f + l) / 2;
            mergeSort(f, m);
            mergeSort(m + 1, l);
            merge++;
            merge(f, m, l);
        }
    }

    private void merge(int left, int mid, int right) {
        Car[] tempArray = new Car[cars.length];
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            comparisons_4+=2;
            if (cars[i].getName().compareTo(cars[j].getName()) <= 0) {
                tempArray[k++] = cars[i++];
                swaps_4++;
            } else {
                swaps_4++;
                tempArray[k++] = cars[j++];
            }
        }
        while (i <= mid) {
            comparisons_4++;
            swaps_4++;
            tempArray[k++] = cars[i++];
        }
        while (j <= right) {
            comparisons_4++;
            swaps_4++;
            tempArray[k++] = cars[j++];
        }
        for (int index = left; index <= right; index++) {
            comparisons_4++;
            cars[index] = tempArray[index];
        }
    }

    public void writemergesort() {
        // TODO Auto-generated method stub

        try (PrintWriter m = new PrintWriter(new FileWriter("msorted.txt"))) {
            m.printf("%-6s %-20s %-15s %s %n", "No.", "Name", "DOB", "VIN");
            int id = my_id + 1;
            for (int i = 0; i < cars.length; i++) {
                if (cars[i] != null) {
                    m.printf("%-6d %-20s %-15tF %-17s%n", id, cars[i].getName(), cars[i].getDob(), cars[i].getVin());
                    id++;
                }
            }

        } catch (IOException e) {
            System.out.printf("Error writing to file msorted.txt");
        }

    }


}
