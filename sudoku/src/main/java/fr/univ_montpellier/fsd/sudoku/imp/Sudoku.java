package fr.univ_montpellier.fsd.sudoku.imp;

import java.util.ArrayList;
import java.util.Collections;

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
		for (int i = 0; i < n ; i++) {
			if ((grid[row][i] == number ) && ( i!= col)) return false;
		}
		return true;
	}

	private boolean isNotInCol(int row,int col, int number) {
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
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int ind = 1; ind <=n; ind++) numbers.add(ind);
		for (int i=0; i<n; i++){
			//Random random = new Random();
			Collections.shuffle(numbers);
			for (int j = 0; j< n; j++){
				grid[i][j]= numbers.get(j);
			}
		}
	}

	/*
	 * Find a solution to the sudoku problem
	 * 
	 */
	public boolean findSolution() {
		while (true) {
			generateSolution();
			if (solutionChecker()) {
				return true;
			}
		}
	}

	/**
	 * Find All solution to the sudoku problem
	 * @return
	 */
	int cpt = 0;
	public boolean findAllSolutions()
	{
		for(int r = 0; r < n; r++){
			for(int c = 0; c < n; c++){
				if(grid[r][c] == 0){
					// check for numbers in range
					for(int num = 1; num <= n; num++){
						if(isOk(r,c,num)){
							// set number if it passes all checks
							grid[r][c] = num;

							// backtrack if we run into constraints, other wise return true to finish sudoku
							if(findAllSolutions()){
								//System.out.println(Arrays.deepToString(grid));
								cpt++;
								grid[r][c] = grid[r][c] + 1;
								if (grid[r][c] > num) grid[r][c] = 0;
								System.out.println("count = "+cpt);
								printSudoku();
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

	public void resetGrid(){
		for (int i=0;i<n;i++){
			for (int j =0;j<n;j++){
				grid[i][j] = 0;
			}
		}
	}

	public static void main(String args[]) {
		Sudoku sudoku = new Sudoku(4);
		sudoku.findSolution();
		System.out.println("Solution");
		sudoku.printSudoku();

		sudoku.resetGrid();
		System.out.println("All Solutions");
		sudoku.findAllSolutions();
	}
}
