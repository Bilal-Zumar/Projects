import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Toptal {

//    public static int solution(int X, int[] B, int Z) {
//        // write your code in Java SE 8
//
//        int remainingDownload=X;
//        int sum=0;
//        for(int i=0; i<B.length; i++)
//        {
//            remainingDownload=remainingDownload-B[i];
//            if(B.length-i<=Z)
//            {
//                sum+=B[i];
//            }
//        }
//
//        int avg = sum / Z;
//        if(avg == 0)
//            return 0;
//        return remainingDownload/avg;
//    }

//    public static String solution(int X) {
//        // write your code in Java SE 8
//
//        String notations = "";
//
//        String[] names = {"w", "d", "h", "m", "s"};
//        int[] value = {0, 0, 0, 0, 0};
//
//        value[4] = X % 60;
//        X = X / 60;
//        value[3] = X % 60;
//        X = X / 60;
//        value[2] = X % 24;
//        X = X / 24;
//        value[1] = X % 7;
//        value[0] = X / 7;
//
//        int check=0;
//        for (int i = 0; i < value.length; i++) {
//            if(check == 1 && value[i] == 0)
//                continue;
//            if(check == 1)
//            {
//                if(i+1<value.length && value[i+1]!=0)
//                {
//                    return notations+(value[i]+1)+names[i];
//                }
//                return notations+value[i]+names[i];
//            }
//            if(value[i]!=0 && i<value.length)
//            {
//                notations = value[i]+names[i];
//                check = 1;
//            }
//        }
//        return notations;
//    }
//
//    public static int solution(int[] A, int K, int L) {
//        // write your code in Java SE 8
//
//        if (K + L > A.length)
//            return -1;
//
//        int[] sumOfK = new int[A.length - K + 1];
//        int[] sumOfL = new int[A.length - L + 1];
//
//        for (int i = 0; i < sumOfK.length; i++) {
//            int sum = 0;
//            for (int j = i; j < i + K; j++) {
//                sum += A[j];
//            }
//            sumOfK[i] = sum;
//        }
//
//        for (int i = 0; i < sumOfL.length; i++) {
//            int sum = 0;
//            for (int j = i; j < i + L; j++) {
//                sum += A[j];
//            }
//            sumOfL[i] = sum;
//        }
//
//        for (int i = 0; i < sumOfK.length; i++) {
//            System.out.print(sumOfK[i] + ",");
//        }
//        System.out.println();
//        for (int i = 0; i < sumOfL.length; i++) {
//            System.out.print(sumOfL[i] + ",");
//        }
//        System.out.println();
//
//        int max = -1;
//        for (int i = 0; i < sumOfK.length; i++) {
//            for (int j = 0; j < sumOfL.length; j++) {
//                if (sumOfK[i] + sumOfL[j] > max && j < i && j < i-L) {
//                    max = sumOfK[i] + sumOfL[j];
//                }
//                if (sumOfK[i] + sumOfL[j] > max && j > i && j > i+K) {
//                    max = sumOfK[i] + sumOfL[j];
//                }
//            }
//        }
//
//        return max;
//    }

    public static int[] getChange(double amount, double purchase) {
        int[] coins = {0, 0, 0, 0, 0, 0};

        amount = amount - purchase;

        coins[5] = (int) amount;

        double floatingNumber = amount - ((int) (amount));
        floatingNumber = Math.round(floatingNumber * 100.0);

        coins[4] = (int) floatingNumber / 50;
        floatingNumber = floatingNumber - 50 * coins[4];

        coins[3] = (int) floatingNumber / 25;
        floatingNumber = floatingNumber - 25 * coins[3];

        coins[2] = (int) floatingNumber / 10;
        floatingNumber = floatingNumber - 10 * coins[2];

        coins[1] = (int) floatingNumber / 5;
        floatingNumber = floatingNumber - 5 * coins[1];

        coins[0] = (int) floatingNumber / 1;
        floatingNumber = floatingNumber - 1 * coins[0];

        return coins;
    }

    public static int rpnCalculator(String str) {
        int num1 = 0;
        int num2 = 0;

        List number = new LinkedList<Integer>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                if (str.charAt(i) == '+' || str.charAt(i) == '*') {
                    num1 = (int) number.get(number.size() - 1);
                    num2 = (int) number.get(number.size() - 2);

                    number.remove(number.size() - 1);
                    number.remove(number.size() - 1);
                    if(str.charAt(i) == '+')
                    {
                        number.add(num1+num2);
//                        System.out.println(num1+num2);
                    }
                    if(str.charAt(i) == '*')
                    {
                        number.add(num1*num2);
//                        System.out.println(num1*num2);
                    }
                } else {
                    number.add(Integer.parseInt(str.charAt(i) + ""));
                }
            }
        }

        return (int)number.get(0);
    }

    public static void main(String[] args) throws IOException, ParseException {
//        int[] arr = {6, 1, 4, 6, 3, 2, 7, 4};
//        Toptal.solution(100, arr, 2);

//        int [] coins = getChange(0.45, 0.34);
//
//        for (int i = 0; i < 6; i++) {
//            System.out.print(coins[i]);
//        }

        System.out.println(rpnCalculator("3 2 2 4 + + *"));

//        System.out.println(solution(arr, 2, 3));
    }
}
