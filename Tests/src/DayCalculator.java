
import java.util.Scanner;

public class DayCalculator {

    public static void main(String[] args) {

        int month, day, year;   //date read in from user
        int daysInMonth = 0;        //number of days in month read in
        boolean monthValid, yearValid, dayValid;  //true if input from user is valid
        boolean leapYear = false;  //true if user's year is a leap year

        Scanner scan = new Scanner(System.in);

        System.out.println("Month");
        month = scan.nextInt();  //Get integer month, day, and year from user

        System.out.println("Day");
        day = scan.nextInt();

        System.out.println("Year");
        year = scan.nextInt();

        if (month > 1 && month <= 12) {
            monthValid = true;
            System.out.println("Month is valid");   //Check to see if month is valid, set monthValid accordingly

            if (year > 1000 && year <= 1999) {
                yearValid = true;
                System.out.println("Year is valid");      //Check to see if year is valid, set yearValid accordingly

                if ((year % 400 == 0 || year % 4 == 0) && year % 100 != 0) {
                    leapYear = true;
                    System.out.println("It is a leap year");
                }  //If date is valid only: Print if date is a leap year

                if (month == 1) {
                    daysInMonth = 31;
                }//Set daysInMonth based on what month user entered.
                else if (month == 2 && leapYear) {
                    daysInMonth = 29;
                } else if (month == 2) {
                    daysInMonth = 28;
                } else if (month == 3) {
                    daysInMonth = 31;
                } else if (month == 4) {
                    daysInMonth = 30;
                } else if (month == 5) {
                    daysInMonth = 31;
                } else if (month == 6) {
                    daysInMonth = 30;
                } else if (month == 7) {
                    daysInMonth = 31;
                } else if (month == 8) {
                    daysInMonth = 31;
                } else if (month == 9) {
                    daysInMonth = 30;
                } else if (month == 10) {
                    daysInMonth = 31;
                } else if (month == 11) {
                    daysInMonth = 30;
                } else if (month == 12) {
                    daysInMonth = 31;
                }

                if (day > 1 && day < daysInMonth) {
                    dayValid = true;
                }  //Determine whether day entered is valid, based on value set in daysInMonth. 12 times
                else {
                    dayValid = false;
                }

                if (monthValid && dayValid && yearValid) {
                    System.out.println("Date is valid"); //Determine whether ENTIRE date is valid and print appropriate message
                    if (leapYear) {
                        leapYear = true;
                        System.out.println("It is a leap year");
                    }//If date is valid only: Print if date is a leap year
                } else {
                    System.out.println("Date is not valid");
                }
            }
        }
    }
}