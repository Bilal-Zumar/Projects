public class ProcessGrades {

    public static void main(String[] args) {

        //instantiate stu1 for the student
        Students stu1 = new Students("Maira");

        //instantiate stu2 for the student
        Students stu2 = new Students("Aaisha");

        //Invoke the inputGrades method for stu1
        stu1.inputGrades();

        //Print the average for stu1 by invoking the printName and getAverage methods
        stu1.getAverage();
        //stu1.printName();

        //Invoke the inputGrades method for stu2.Print the average for stu2 by invoking the printName and getAverage methods.
        stu2.inputGrades();
        stu2.getAverage();
        //stu2.printName();

        //printing Name using the accessor method getName
        stu1.getName();


    }

}