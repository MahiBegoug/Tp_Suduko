package fr.univ_montpellier.fsd.sudoku.imp;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Sudoku {

	int n;
	int s;
	int[][] grid;


	/*
	 * Create an instance of the problem sudoku (nxn)
	 * 
	 */

	public Sudoku(int n) {
		this.n = n;
		this.s = (int) Math.sqrt(n);
		this.grid = new int[n][n];
	}

	/*
	 * check if this.grid is a correct sudoku solution.
	 * 
	 */

	public boolean solutionChecker() {

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (!isOk(i,j, grid[i][j])) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isNotInRow(int row, int col ,int number) {
		/*if (col == n-1) {
			return true;
		}*/
		for (int i = 0; i < n ; i++) {
			if ((grid[row][i] == number ) && ( i!= col)) return false;
		}
		return true;
	}

	private boolean isNotInCol(int row,int col, int number) {
		/*if(row == n-1) {
			return true;
		}*/
		for (int j = 0; j < n ; j++) {
			if ((grid[j][col] == number) && ( j!= row)) return false;
		}
		return true;
	}

	private boolean isNotInBox(int row, int col, int number) {
		int vertical = row - row % s;
		int horizontal = col - col % s;

		for (int i = vertical; i < vertical + s; i++ )
			for (int j = horizontal; j < horizontal +s; j++)
				if ( (grid[i][j] == number) && (  i != row && j!= col) ) return false;
		return true;
	}

	private boolean isOk(int row, int col, int number) {
		//printSudoku();
		//System.out.println("and "+Arrays.deepToString(grid));
		return isNotInRow(row, col, number) && isNotInCol(row,col, number) && isNotInBox(row, col,number) ;
	}

	public void printSudoku()
	{
		int r = 0;
		int c = 0;
		String repeatedStar = new String(new char[4*grid.length+4]).replace('\0', '-');
		for (int[] i: grid)
		{
			if (r % n == 0)
				System.out.print(repeatedStar+'\n');
			for(int j: i)
			{
				if (c % n == 0)
					System.out.print('|');
				if (j < 10)
					System.out.print("  " + j + " ");
				else
					System.out.print(" " + j + " ");
				c++;
			}
			r++;
			System.out.print("|");
			System.out.println();
		}
		System.out.print(repeatedStar+'\n');
	}

	/*
	 * Generate a random grid solution
	 * 
	 */

	public void generateSolution() {

		this.grid = new int[][]{
				{1, 1, 2, 3, 4, 5, 6, 7, 8},
				{3, 4, 4, 1, 6, 7, 2, 5, 9},
				{5, 6, 7, 2, 8, 9, 1, 3, 4},
				{1, 2, 3, 4, 5, 4, 7, 8, 6},
				{4, 5, 6, 7, 1, 1, 3, 9, 2},
				{7, 8, 9, 6, 2, 3, 4, 1, 5},
				{2, 3, 1, 5, 9, 6, 8, 4, 7},
				{6, 4, 5, 8, 7, 3, 9, 2, 1},
				{8, 9, 7, 4, 1, 2, 5, 6, 3}
		};
		/*ArrayList<Integer> numbers = new ArrayList<>();
		for (int ind = 1; ind <=n; ind++) numbers.add(ind);
		for (int i=0; i<n; i++){
			//Random random = new Random();
			Collections.shuffle(numbers);
			for (int j = 0; j< n; j++){
				grid[i][j]= numbers.get(j);
			}
		}*/

	}

	/*
	 * Find a solution to the sudoku problem
	 * 
	 */
	public boolean findSolution() {
		int cpt = 1;
		while (true) {
			generateSolution();
			//System.out.println(Arrays.deepToString(grid));
			if (solutionChecker()) {
				return true;
			}
			cpt ++;
		}
	}

	/**
	 * Find All solution to the sudoku problem
	 * @return
	 */
	int cpt = 0;
	public boolean findAllSolution()
	{

		for(int r = 0; r < n; r++){
			for(int c = 0; c < n; c++){
				if(grid[r][c] == 0){
					// check for numbers in range
					for(int num = 1; num <= n; num++){
						System.out.println(num + " and "+ isOk(r,c,num));
						if(isOk(r,c,num)){
							// set number if it passes all checks
							grid[r][c] = num;
							printSudoku();
							// backtrack if we run into constraints, other wise return true to finish sudoku
							if(findAllSolution()){
								//System.out.println(Arrays.deepToString(grid));
								cpt++;
								grid[r][c] = grid[r][c] + 1;
								if (grid[r][c] > num) grid[r][c] = 0;
								System.out.println("count = "+cpt);
								//return true;
							} else {
								// reset the cell to 0 and backtrack
								grid[r][c] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		// true if solved
		return true;

	}

	public static void main(String args[]) {
		new Sudoku(4).findSolution();

	}
}
