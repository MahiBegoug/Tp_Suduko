package fr.univ_montpellier.fsd.sudoku;

import fr.univ_montpellier.fsd.sudoku.imp.Sudoku;
import gnu.trove.impl.sync.TSynchronizedShortByteMap;
import junit.framework.Assert;
import junit.framework.TestCase;

public class SudokuTest extends TestCase {

    public void testApp()
    {
        Sudoku sudoku = new Sudoku(9);
        sudoku.generateSolution();
        //System.out.println(sudoku.solutionChecker());

        assertTrue(sudoku.solutionChecker());
    }


}
