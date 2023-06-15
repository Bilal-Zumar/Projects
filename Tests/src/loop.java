import java.util.Scanner;
public class loop {

    public static void main(String[] args) {

        System.out.println("Reg+Main7".trim().toLowerCase());

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of words to be entered:");
        int num = sc.nextInt();
        int times = 0;

        sc.nextLine();
        do {


            System.out.println("Enter a word");
            String str = sc.nextLine();

            String ans = "";
            for(int i = 0; i < str.length(); i++)
            {
                ans = ans  + str.charAt(i);
                System.out.println(ans);
            }
            times++;
        }while(times<=num);
    }

}

