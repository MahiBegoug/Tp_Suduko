package fr.univ_montpellier.fsd.sudoku;

import fr.univ_montpellier.fsd.sudoku.imp.Sudoku;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Sudoku sudoku = new Sudoku(4);

        System.out.println(sudoku.findAllSolution());
    }
}
