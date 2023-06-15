/*
 * The Rational class stores the numerator and denominator in reduced form.
 */
public class Rational implements Comparable {

    private int numerator;
    private int denominator;

    //Constructor: set object data and simplify
    public Rational(int n, int d) {
        numerator = n;
        denominator = d;

        //note: reduce will verify the denominator is always positive
        reduce();  //simplify
    }

    public Rational(int n) {
        numerator = n;
        denominator = 1;
    }

    //add the passed Rational with the object, return new Rational object
    public Rational add(Rational num) {
        int cmnDenominator = denominator * num.denominator;
        int addNumerator = (this.numerator * num.denominator) + (this.denominator * num.numerator);
        ;  //todo

        //note: the constructor will call reduce().
        return new Rational(addNumerator, cmnDenominator);
    }

    //TO DO:
    public boolean equals(Rational num) {
        if ((this.subtract(num)).numerator == 0)
            return true;
        else
            return false;
    }

    public Rational subtract(Rational num) {
        int n = (numerator * num.denominator) - (denominator * num.numerator);
        int d = denominator * num.denominator;
        return new Rational(n, d);
    }

    public Rational multiply(Rational num) {
        return new Rational(this.numerator * num.numerator, this.denominator * num.denominator);
    }

    public Rational divide(Rational num) {
        return this.multiply(num.reciprocal());
    }

    private Rational reciprocal() {
        return new Rational(denominator, numerator);
    }

    //TODO
    public int compareTo(Object o) {
        Rational rat = (Rational) o;
        if (this.subtract(rat).numerator > 0)
            return 1;
        else if (this.subtract(rat).numerator < 0)
            return -1;
        else
            return 0;
    }

    /*
     *  toString: generator a string: numerator / denominator
     */
    public String toString() {
        //todo
        if (denominator == 1)
            return "" + numerator;
        else
            return numerator + "/" + denominator;
    }

    /*
     *   private methods: only used within this class
     *      - verify the denominator is positive
     *      - find the greatest common factor
     *      - divide the numerator and divisor by the GCF
     */
    private void reduce() {
        // always keep the denominator positive.

        // find the greatest common factor between the numerator & denominator

        int g = gcd(denominator, numerator);
        numerator = numerator / g;
        denominator = denominator / g;

        if (denominator < 0) {
            numerator = numerator * -1;
            denominator = denominator * -1;
        }
    }

    private static int gcd(int denominator, int numerator) {
        if (0 == numerator) return denominator;
        else return gcd(numerator, denominator % numerator);
    }
}
				
         	
   
