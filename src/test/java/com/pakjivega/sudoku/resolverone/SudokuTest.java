package com.pakjivega.sudoku.resolverone;

import org.junit.Assert;
import org.junit.Test;

import com.pakjivega.sudoku.resolverone.NumberSudoku;
import com.pakjivega.sudoku.resolverone.Sudoku;

public class SudokuTest {

    /**
     * Test easy Sudoku :-)
     */
	@Test
    public void testEasyOne()
    {
		Sudoku sudoku = generateSudokuEasyOne();
		sudoku.resolve();
		Assert.assertTrue( sudoku.howManySure() == 81 );
    }

    /**
     * Test easy Sudoku :-)
     */
	@Test
    public void testEasyTwo()
    {
		Sudoku sudoku = generateNewSudokuTwo();
		sudoku.resolve();
		Assert.assertTrue( sudoku.howManySure() == 81 );
    }

    /**
     * Test easy Sudoku :-)
     */
	@Test
    public void testMedium()
    {
		Sudoku sudoku = generateSudokuMedium();
		sudoku.resolve();
		Assert.assertTrue( sudoku.howManySure() == 81 );
    }
	
	/**
     * Test hard Sudoku :-)
     */
	//@Test
    public void testHardSudoku()
    {
		Sudoku sudoku = generateSudokuHardOne();
		sudoku.resolve();
		sudoku.printSudokuChoices();
		Assert.assertTrue( sudoku.howManySure() == 81 );
    }
	private static Sudoku generateSudokuEasyOne(){
        Sudoku sudoku = new Sudoku();
        sudoku.getSudokuSure()[0][4] = NumberSudoku.FOUR;
        sudoku.getSudokuSure()[1][2] = NumberSudoku.FOUR;
        sudoku.getSudokuSure()[1][3] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[1][5] = NumberSudoku.TWO;
        sudoku.getSudokuSure()[1][8] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[2][1] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[2][3] = NumberSudoku.NINE;
        sudoku.getSudokuSure()[3][1] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[3][2] = NumberSudoku.ONE;
        sudoku.getSudokuSure()[3][6] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[3][8] = NumberSudoku.FIVE;
        sudoku.getSudokuSure()[4][0] = NumberSudoku.FIVE;
        sudoku.getSudokuSure()[4][5] = NumberSudoku.SEVEN;
        sudoku.getSudokuSure()[4][7] = NumberSudoku.TWO;
        sudoku.getSudokuSure()[4][8] = NumberSudoku.EIGHT;
        sudoku.getSudokuSure()[5][1] = NumberSudoku.EIGHT;
        sudoku.getSudokuSure()[5][4] = NumberSudoku.ONE;
        sudoku.getSudokuSure()[5][7] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[5][8] = NumberSudoku.NINE;
        sudoku.getSudokuSure()[6][3] = NumberSudoku.SEVEN;
        sudoku.getSudokuSure()[6][7] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[6][8] = NumberSudoku.ONE;
        sudoku.getSudokuSure()[7][4] = NumberSudoku.TWO;
        sudoku.getSudokuSure()[7][5] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[7][6] = NumberSudoku.SEVEN;
        sudoku.getSudokuSure()[8][1] = NumberSudoku.SEVEN;
        sudoku.getSudokuSure()[8][3] = NumberSudoku.FOUR;
        sudoku.getSudokuSure()[8][4] = NumberSudoku.FIVE;
        sudoku.getSudokuSure()[8][5] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[8][6] = NumberSudoku.NINE;
        return sudoku;
    }

    private static  Sudoku generateNewSudokuTwo(){
        Sudoku sudoku = new Sudoku();
        sudoku.getSudokuSure()[0][3] = NumberSudoku.ONE;
        sudoku.getSudokuSure()[0][4] = NumberSudoku.EIGHT;
        sudoku.getSudokuSure()[0][5] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[1][3] = NumberSudoku.NINE;
        sudoku.getSudokuSure()[1][5] = NumberSudoku.TWO;
        sudoku.getSudokuSure()[2][2] = NumberSudoku.EIGHT;
        sudoku.getSudokuSure()[2][6] = NumberSudoku.ONE;
        sudoku.getSudokuSure()[3][1] = NumberSudoku.EIGHT;
        sudoku.getSudokuSure()[3][7] = NumberSudoku.NINE;
        sudoku.getSudokuSure()[4][0] = NumberSudoku.TWO;
        sudoku.getSudokuSure()[4][1] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[4][7] = NumberSudoku.FOUR;
        sudoku.getSudokuSure()[4][8] = NumberSudoku.SEVEN;
        sudoku.getSudokuSure()[5][0] = NumberSudoku.NINE;
        sudoku.getSudokuSure()[5][1] = NumberSudoku.SEVEN;
        sudoku.getSudokuSure()[5][3] = NumberSudoku.EIGHT;
        sudoku.getSudokuSure()[5][5] = NumberSudoku.FIVE;
        sudoku.getSudokuSure()[5][7] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[5][8] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[6][3] = NumberSudoku.TWO;
        sudoku.getSudokuSure()[6][4] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[6][5] = NumberSudoku.NINE;
        sudoku.getSudokuSure()[7][1] = NumberSudoku.ONE;
        sudoku.getSudokuSure()[7][2] = NumberSudoku.NINE;
        sudoku.getSudokuSure()[7][3] = NumberSudoku.FOUR;
        sudoku.getSudokuSure()[7][5] = NumberSudoku.EIGHT;
        sudoku.getSudokuSure()[7][6] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[7][7] = NumberSudoku.SEVEN;
        sudoku.getSudokuSure()[8][1] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[8][7] = NumberSudoku.EIGHT;
        return sudoku;
    }
    private static  Sudoku generateSudokuMedium(){
        Sudoku sudoku = new Sudoku();
        sudoku.getSudokuSure()[0][3] = NumberSudoku.TWO;
        sudoku.getSudokuSure()[1][0] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[1][5] = NumberSudoku.NINE;
        sudoku.getSudokuSure()[1][7] = NumberSudoku.SEVEN;
        sudoku.getSudokuSure()[2][1] = NumberSudoku.TWO;
        sudoku.getSudokuSure()[2][2] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[2][4] = NumberSudoku.SEVEN;
        sudoku.getSudokuSure()[2][5] = NumberSudoku.FOUR;
        sudoku.getSudokuSure()[2][8] = NumberSudoku.EIGHT;
        sudoku.getSudokuSure()[3][1] = NumberSudoku.ONE;
        sudoku.getSudokuSure()[3][6] = NumberSudoku.NINE;
        sudoku.getSudokuSure()[4][0] = NumberSudoku.EIGHT;
        sudoku.getSudokuSure()[4][2] = NumberSudoku.FIVE;
        sudoku.getSudokuSure()[4][3] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[4][5] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[4][6] = NumberSudoku.FOUR;
        sudoku.getSudokuSure()[4][8] = NumberSudoku.ONE;
        sudoku.getSudokuSure()[5][2] = NumberSudoku.FOUR;
        sudoku.getSudokuSure()[5][7] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[6][0] = NumberSudoku.ONE;
        sudoku.getSudokuSure()[6][3] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[6][4] = NumberSudoku.FOUR;
        sudoku.getSudokuSure()[6][6] = NumberSudoku.SEVEN;
        sudoku.getSudokuSure()[6][7] = NumberSudoku.NINE;
        sudoku.getSudokuSure()[7][1] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[7][3] = NumberSudoku.NINE;
        sudoku.getSudokuSure()[7][8] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[8][5] = NumberSudoku.SEVEN;
        return sudoku;
    }
    private static  Sudoku generateSudokuHardOne(){
        Sudoku sudoku = new Sudoku();
        sudoku.getSudokuSure()[0][5] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[0][6] = NumberSudoku.ONE;
        sudoku.getSudokuSure()[0][8] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[1][3] = NumberSudoku.SEVEN;
        sudoku.getSudokuSure()[1][4] = NumberSudoku.FOUR;
        sudoku.getSudokuSure()[2][2] = NumberSudoku.FIVE;
        sudoku.getSudokuSure()[2][8] = NumberSudoku.EIGHT;
        sudoku.getSudokuSure()[3][1] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[3][6] = NumberSudoku.FIVE;
        sudoku.getSudokuSure()[4][1] = NumberSudoku.EIGHT;
        sudoku.getSudokuSure()[4][5] = NumberSudoku.FOUR;
        sudoku.getSudokuSure()[4][6] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[4][7] = NumberSudoku.TWO;
        sudoku.getSudokuSure()[4][8] = NumberSudoku.ONE;
        sudoku.getSudokuSure()[5][0] = NumberSudoku.SIX;
        sudoku.getSudokuSure()[5][4] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[6][0] = NumberSudoku.NINE;
        sudoku.getSudokuSure()[6][3] = NumberSudoku.THREE;
        sudoku.getSudokuSure()[6][4] = NumberSudoku.TWO;
        sudoku.getSudokuSure()[6][7] = NumberSudoku.EIGHT;
        sudoku.getSudokuSure()[7][4] = NumberSudoku.NINE;
        sudoku.getSudokuSure()[7][6] = NumberSudoku.TWO;
        sudoku.getSudokuSure()[8][0] = NumberSudoku.FOUR;
        sudoku.getSudokuSure()[8][2] = NumberSudoku.SEVEN;
        sudoku.getSudokuSure()[8][4] = NumberSudoku.FIVE;   
        return sudoku;
    }
}
