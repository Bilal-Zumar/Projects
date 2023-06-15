public class RationalDriver {
    /*
     * create and display a list of Rationals in sorted order.
     * To get this to work, you only need to implement the following methods in the Rational class:
     *    toString() and compareTo()
     */
    public static void displaySortedRats() {
        Rational[] ratList = {new Rational(2, 3), new Rational(-2, 4), new Rational(3, 5),
                new Rational(8, 4), new Rational(1), new Rational(1, 2),
                new Rational(5, 6), new Rational(7, 8), new Rational(3, 2)};

        SortData.printArray("before", ratList);
//        SortData.selectionSort(ratList);
        SortData.printArray("after", ratList);
    }

    public static void main(String[] args) throws Exception {

        displaySortedRats();

        Rational n1 = new Rational(2, 3);  // 2/3
        Rational n2 = new Rational(1, 4);  // 1/4
        Rational n2b = new Rational(2, 8);
        System.out.println(n1 + " equals? " + n2 + "  : " + n1.equals(n2));
        System.out.println(n2 + " equals? " + n2b + "  : " + n2.equals(n2b));


        Rational n3 = n1.add(n2);
        System.out.println(n1 + " + " + n2 + " = " + n3);


//   	  TODO:

        Rational n4 = n1.multiply(n3);
        System.out.println(n1 + " * " + n3 + " = " + n4);

        Rational n5 = n1.divide(n2);
        System.out.println(n1 + " / " + n2 + " = " + n5);


//   	  TODO: add additional test cases
//            verify you support negative rationals

        n1 = new Rational(-2, 3);  // 2/3
        n2 = new Rational(1, -4);  // 1/4
        n2b = new Rational(-2, 8);
        System.out.println(n1 + " equals? " + n2 + "  : " + n1.equals(n2));
        System.out.println(n2 + " equals? " + n2b + "  : " + n2.equals(n2b));

    }
}
