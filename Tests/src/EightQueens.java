public class EightQueens {
    static final int MAX_ROWS = 8;
    static final int MAX_COLMS = MAX_ROWS;
    static int[] queens = new int[MAX_ROWS];
    static int numIterations = 0;

    public static void printQueenArray(int row) {
        for (int k = 0; k <= row; k++)
            System.out.print(queens[k] + " ");
        System.out.println();
    }

    /*
     * validateHorizontal: verify there is not a queen in the specified row.
     */
    public static boolean validateHorizontal(int row) {
        for (int i = 0; i < queens.length; i++) {
            if (queens[i] == row)
                return false;
        }
        return true;
    }

    /*
     * validateDiagonal: verify thete is not a queen in the diagonal direction (45 degrees)
     *        - you only need to look backwards from the specified column
     *        - look in 2 directions:
     *							negative slope: row-1, col-1
     *							positive slope: row+1, col-1
     *        - return false if there is a conflict with another queen.
     *			 - return true if there is no conflict.
     */

    public static boolean validateDiagonal(int row, int col) {
        //verify going up (negative slope)
        for (int i = 0; i <= col; i++) {
            if (row == queens[col - i] + i) {
                return false;
            }
            if (row == queens[col - i] - i) {
                return false;
            }
        }
        //verify going down (positive slope)
        return true;
    }

    public static boolean tryQueen(int c) {
        numIterations++;
        //base condition: done when we have tried all columns
        if (c >= MAX_COLMS)
            return true;

        //find a valid row for the specified column
        for (int r = 0; r < MAX_ROWS; r++) {
            //test if this row is safe for the specified column
            if (validateHorizontal(r) && validateDiagonal(r, c)) {
                // set the queens array

                queens[c] = r;

                //comment out call to printQueenArray statement when you get it to work!
//                printQueenArray(c);
                //recursive call for next column, return true if successful
                if (tryQueen(c + 1))
                    return true;
                // if not successful, reset the queens array and keep going within the for loop
                queens[c] = -1;
            }
        }
        return false;
    }

    public static void displayBoard() {
        System.out.println("   0 1 2 3 4 5 6 7 ");
        for (int y = 0; y < MAX_ROWS; y++) {
            System.out.print(y + ": ");
            for (int column = 0; column < MAX_COLMS; column++) {
                if (queens[column] == y) {
                    System.out.print("Q ");
                } else {
                    System.out.print("= ");
                }
            }
            System.out.println("");
        }
        System.out.println();
    }

//    public static void main(String[] args) {
//        /*
//         * TODO: At first, get row=4 to work.  Then comment out line and use the for loop
//         *       to verify all positions work in column 0.
//         *
//         * Please verify your results....   Draw out a empty board and verify the no other queen
//         * is in the horizontal and diagonal directions.
//         *
//         */
////   	for (int row = 0; row<MAX_ROWS; row++)
//        int row = 6;
//        {
//            for (int k = 0; k < queens.length; k++)
//                queens[k] = -1;
//
//            queens[0] = row;
//            numIterations = 0;
//            if (tryQueen(1)) {
//                printQueenArray(MAX_ROWS - 1);
//                System.out.printf("row: %d, iterations: %d \n", row, numIterations);
//                displayBoard();
//            } else
//                System.out.println("failed");
//        }
//    }
}
