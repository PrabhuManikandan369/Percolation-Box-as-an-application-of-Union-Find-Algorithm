
import java.util.*;

public class Percolation {

    private static final int FULL = 0;
    private static final int OPEN = 1;

    private int N;
    private static int[][] grid;

    private WeightedQuickUnionUF ufHelper;

    public Percolation(int n) {
        N = n;
        grid = new int[N][N];
        ufHelper = new WeightedQuickUnionUF((N * N) + 2);
    }

    public void open(int i, int j) {
        int row = i - 1;
        int col = j - 1;
        if (row < 0 || row > N || col < 0 || col > N)
            throw new IndexOutOfBoundsException("Illegal parameter value.");
        grid[row][col] = OPEN;
        if (row == 0) {
            ufHelper.union(0, xyTo1D(row, col));
        }
        if (row == N - 1) {
            ufHelper.union((N * N) + 1, xyTo1D(row, col));
        }
        if ((row + 1) < N) {
            if (grid[row + 1][col] == OPEN)
                ufHelper.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        if ((row - 1) >= 0) {
            if (grid[row - 1][col] == OPEN)
                ufHelper.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        if ((col + 1) < N) {
            if (grid[row][col + 1] == OPEN)
                ufHelper.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
        if ((col - 1) >= 0) {
            if (grid[row][col - 1] == OPEN)
                ufHelper.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
    }

    public boolean isOpen(int i, int j) {
        int row = i - 1;
        int col = j - 1;
        if (row < 0 || row > N || col < 0 || col > N)
            throw new IndexOutOfBoundsException("Illegal parameter value.");
        return grid[row][col] == OPEN;
    }

    public boolean isFull(int i, int j) {
        int row = i - 1;
        int col = j - 1;
        if (row < 0 || row > N || col < 0 || col > N)
            throw new IndexOutOfBoundsException("Illegal parameter value.");
        return grid[row][col] == FULL;
    }

    public boolean percolates() {
        return ufHelper.connected(0, (N * N) + 1);
    }

    private int xyTo1D(int i, int j) {
        if (i < 0 || i > N || j < 0 || j > N)
            throw new IndexOutOfBoundsException("Illegal parameter value.");
        return ((i * N) + j) + 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of rows or columns in the grid :");
        int GRID_SIZE = in.nextInt();
        System.out.println("Enter number of open sites in the system :");
        final int TESTS = in.nextInt();
        in.close();
        if (TESTS > GRID_SIZE * GRID_SIZE)
            throw new IndexOutOfBoundsException("Illegal parameter value.");
        System.out.println("Successfully created Percolation object.");
        System.out.println();
        int arr[] = new int[GRID_SIZE * GRID_SIZE];

        System.out.println("Starting to open random sites...");
        int row, col, count = 0;
        int i = 0;
        while (i < 100) {
            Percolation perc = new Percolation(GRID_SIZE);
            for (int l = 0; l < GRID_SIZE; l++) {
                for (int m = 0; m < GRID_SIZE; m++) {
                    grid[l][m] = 0;
                }
            }
            int k = 1;
            while (!perc.percolates() && k <= TESTS) {
                row = (int) (Math.random() * (GRID_SIZE) + 1);
                col = (int) (Math.random() * (GRID_SIZE) + 1);
                if (perc.isFull(row, col)) {
                    perc.open(row, col);
                }
                if (perc.percolates()) {
                    count = count + 1;
                    int index = 0;
                    for (int lmk = 0; lmk < GRID_SIZE; lmk++)
                        for (int nmk = 0; nmk < GRID_SIZE; nmk++) {
                            arr[index] = grid[lmk][nmk];
                            index++;
                        }
                }
                k++;
            }
            i++;
        }
        System.out.println("Out of 100 random combinations only " + count + " combinations percolates .");
        System.out.println("PROBABLITY :" + (double) count / 100);
        MyPanel.setPercolationArr(arr, GRID_SIZE);
        Main.display();

    }
}