package fr.univ_montpellier.fsd.sudoku;

import fr.univ_montpellier.fsd.sudoku.imp.Sudoku;
import fr.univ_montpellier.fsd.sudoku.imp.SudokuSol;

import java.time.Duration;
import java.time.Instant;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Sudoku sudoku = new Sudoku(4);
        sudoku.findSolution();
        System.out.println("Solution");
        sudoku.printSudoku();

        sudoku.resetGrid();
        System.out.println("All Solutions");
        sudoku.findAllSolutions();
        sudoku.resetGrid();


        Instant start = Instant.now();
        sudoku.findSolution();
        Instant end = Instant.now();
        System.out.println("Notre solution: "+Duration.between(start, end));
    }
}
