package Minesweeper;

import java.util.Random;

public class Minesweeper {

    public static final int MINE = 9;

    public static void main(String[] args) {

        // Hard coded size of 10 x 10
        // place 'gridSize' bombs randomly in the grid - check for repeats
        // In each remaining space, count the number of bombs adjacent to the space
        // print grid

        int gridSize = 10;
        int[][] grid = create_init_grid(gridSize);
        printGrid(grid);
    }

    /**
     * Initialize the grid with a given grid size.
     * Note, the grid will always be a square.
     *
     * @param gridSize the number of rows and columns in the 2D array
     * @return a square 2D array of <code>size</code> x <code>size</code>
     */

    public static int[][] create_init_grid(int gridSize) {
        int[][] grid = new int[gridSize][gridSize];
        placeMines(gridSize, grid);
        countAllSurroundingMines(grid);
        return grid;
    }

    /**
     * Randomly places n mines in the 2D array, <code>grid</code>,
     * where n is equal to <code>size</code>
     *
     * @param size the number of mines to place in the <code>grid</code>
     * @param grid the 2D array
     */
    private static void placeMines(int size, int[][] grid) {
        int n = MINE;
        while (n > 0) {
            Random r = new Random();
            int range = size - 1;
            int randr = r.nextInt((range - 0) + 1) + 0;
            int randc = r.nextInt((range - 0) + 1) + 0;
            if (grid[randr][randc] != -1) {
                grid[randr][randc] = -1;
                n--;
            }
        }
    }


    /**
     * After the MINEs have been set, this method is called to set the rest of the elements
     * in the grid.  Each element is set to the count of the number of surrounding mines.
     * <p>
     * This method should traverse the grid and call countSurroundingMines() for each (r,c).
     *
     * @param grid the 2D array
     */
    private static void countAllSurroundingMines(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                countSurroundingMines(i, j, grid);
            }
        }
    }


    /**
     * Counts and then sets the element to the number of mines surrounding
     * the element at the given <code>row</code> and <code>col</code>
     *
     * @param row  the row location
     * @param col  the column location
     * @param grid the 2D array
     */
    private static void countSurroundingMines(int row, int col, int[][] grid) {
        int count = 0;
        if (grid[row][col] == -1)
            return;
        if (row + 1 < grid.length && grid[row + 1][col] == -1) {
            count++;
        }
        if (row - 1 >= 0 && grid[row - 1][col] == -1) {
            count++;
        }
        if (col + 1 < grid.length && grid[row][col + 1] == -1) {
            count++;
        }
        if (col - 1 >= 0 && grid[row][col - 1] == -1) {
            count++;
        }
        if (row + 1 < grid.length && col + 1 < grid.length && grid[row + 1][col + 1] == -1) {
            count++;
        }
        if (row - 1 >= 0 && col - 1 >= 0 && grid[row - 1][col - 1] == -1) {
            count++;
        }
        if (row + 1 < grid.length && col - 1 >= 0 && grid[row + 1][col - 1] == -1) {
            count++;
        }
        if (row - 1 >= 0 && col + 1 < grid.length && grid[row - 1][col + 1] == -1) {
            count++;
        }

        grid[row][col] = count;
    }


    /**
     * Prints the 2D array of integers
     * you can use the print format method, printf, display an integer with 2 spaces:
     * System.out.printf("%2d, ", grid[r][c]);
     *
     * @param grid the 2D array
     */
    public static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

}
