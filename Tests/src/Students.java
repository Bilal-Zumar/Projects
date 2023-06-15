/**********************
 Student.java

 Define a student class that stores name, score on test 1, and
 score on test 2.  Methods prompt for and read in grades,
 compute the average, and print student's name.
 **********************/
import java.util.Scanner;
public class Students
{
  /*Data fields that each Student object keeps track of*/
    private String name;
    private int score_test1;
    private int score_test2;


    /*------------------------------------------------------
     1-Arg Constructor: Sets the student's name.
     Test scores are given a default value of 0
    --------------------------------------------------------*/
    public Students(String studentName)
    {
        //add definition of constructor
        name = studentName;
        score_test1 = 0;
        score_test2 = 0;
    }

    /*---------------------------------------------------------
     Method: inputGrades
     Precondition: N/A
     Postcondition: Uses name and prompts for and sets both
     test scores. e.g., "Enter Joe's score for test1".
   ------------------------------------------------------------*/
    public void inputGrades()
    {
        //add body of inputGrades
        do
        {

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter "+ name+ "'s score for test1 between 1 to 100");
            score_test1= sc.nextInt();
            System.out.println("Enter"+ name+ "'s score for test2 between 1 to 100");
            score_test2= sc.nextInt();
            if(((score_test1<0 || score_test2<0) || (score_test1>100 &&score_test2>100)))
            {
                System.out.println("Invalid entry");
            }
        }while ((score_test1<0 || score_test2<0) || (score_test1>100 &&score_test2>100));
    }

    /*---------------------------------------------------------
     Method: getAverage
     Precondition: test1 and test2 have been set to positive ints
     Postcondition: returns true decimal test average
   ------------------------------------------------------------*/
    public double getAverage()
    {

        return (score_test1+score_test2)/2;
    }


    /*---------------------------------------------------------
      Method: printName
      Precondition: N/A
      Postcondition: simply prints student's name, returns nothing
    ------------------------------------------------------------*/
    public void printName()
    {
        System.out.println(name);
    }

   /*---------------------------------------------------------
   An Accessor Method that return the name
   Precondition: N/A
   Postcondition: simply  returns name
   It is an alterate to printName()
 ------------------------------------------------------------*/

    public String getName()
    {

        return name;
    }
}