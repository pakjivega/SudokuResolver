package com.pakjivega.sudoku.resolverone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.RowFilter;

public class Sudoku {

	private NumberSudoku[][] sudokuSure = new NumberSudoku[9][9];
	private EnumSet<?>[][] sudokuChoices = new EnumSet<?>[9][9];
	private Map<NumberSudoku, List<List<Integer>>> possibleRows = new HashMap<NumberSudoku, List<List<Integer>>>();
	private Map<NumberSudoku, List<List<Integer>>> possibleCols = new HashMap<NumberSudoku, List<List<Integer>>>();

	public NumberSudoku[][] getSudokuSure() {
		return sudokuSure;
	}

	public void setSudokuSure(NumberSudoku[][] sudokuSure) {
		this.sudokuSure = sudokuSure;
	}

	public EnumSet<?>[][] getSudokuChoices() {
		return sudokuChoices;
	}

	// public void setSudokuChoices(List<EnumSet<?> sudokuChoices) {
	// this.sudokuChoices = sudokuChoices;
	// }

	public Sudoku() {
		this.fillSudokuChoices();
		this.fillPossibleRowsandCols();
	}

	private void fillSudokuChoices() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				sudokuChoices[row][col] = EnumSet.allOf(NumberSudoku.class);
			}
		}
	}

	private void fillPossibleRowsandCols() {
		for (NumberSudoku numberSudoku : NumberSudoku.values()) {
			this.possibleRows.put(numberSudoku, new ArrayList<List<Integer>>());
			this.possibleCols.put(numberSudoku, new ArrayList<List<Integer>>());
		}
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 3; col++) {
				for (NumberSudoku numberSudoku : NumberSudoku.values()) {
					this.possibleRows.get(numberSudoku).add(Arrays.asList(row, col));
				}
			}
		}
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 3; row++) {
				for (NumberSudoku numberSudoku : NumberSudoku.values()) {
					this.possibleCols.get(numberSudoku).add(Arrays.asList(row, col));
				}
			}
		}
	}

	public int howManySure() {
		int howmanysure = 0;
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (sudokuSure[row][col] != null) {
					howmanysure++;
				}
			}
		}
		return howmanysure;
	}

	public void printSudokuSure() {
		int howmanysure = 0;
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (sudokuSure[row][col] != null) {
					howmanysure++;
				}
				System.out.print("|" + sudokuSure[row][col]);
			}
			System.out.println("|");
		}
		System.out.println("Cells sure: " + howmanysure);
	}

	public void printSudokuChoices() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				System.out.print("|" + getSudokuChoices()[row][col]);
			}
			System.out.println("|");
		}
	}

	public void setInitialChoices() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (this.sudokuSure[row][col] != null) {
					this.setChoices(row, col);
				}
			}
		}
	}

	private void setChoices(int row, int col) {
		System.out.println("Set value row: " + row + " col: " + col + " Value: " + this.sudokuSure[row][col]);
		if ( this.sudokuSure[row][col] == NumberSudoku.THREE) {
			//System.out.println("With three. row: " + row + " col " + col );
		}
		if ( ( row  == 1 ) && ( col == 8)) {
			System.out.println("EN 1,8 ");
		}
		// In the same row
		for (int col2 = 0; col2 < 9; col2++) {
			this.sudokuChoices[row][col2].remove(sudokuSure[row][col]);
			this.possibleRows.get(sudokuSure[row][col]).remove(Arrays.asList(row, col2 / 3));
		}
		this.possibleRows.get(sudokuSure[row][col]).add(Arrays.asList(row, col / 3));
		
		// In the same col
		for (int row2 = 0; row2 < 9; row2++) {
			this.sudokuChoices[row2][col].remove(sudokuSure[row][col]);
			this.possibleCols.get(sudokuSure[row][col]).remove(Arrays.asList(row2 / 3, col));
		}
		this.possibleCols.get(sudokuSure[row][col]).add(Arrays.asList(row / 3, col));

		if ( ( row  == 3 ) && ( col == 1)) {
			//System.out.println("EN Three 3, 1");
		}
		// In the same square
		int difcol3 = col % 3;
		int difrow3 = row % 3;
		for (int col2 = (col - difcol3); col2 < (col - difcol3 + 3); col2++) {
			for (int row2 = (row - difrow3); row2 < (row - difrow3 + 3); row2++) {
				sudokuChoices[row2][col2].remove(sudokuSure[row][col]);
				this.possibleRows.get(sudokuSure[row][col]).remove(Arrays.asList(row2, col2 / 3));
				this.possibleCols.get(sudokuSure[row][col]).remove(Arrays.asList(row2 / 3, col2));
			}
		}
		this.possibleRows.get(sudokuSure[row][col]).add(Arrays.asList(row, col / 3));
		this.possibleCols.get(sudokuSure[row][col]).add(Arrays.asList(row / 3, col));

		sudokuChoices[row][col] = EnumSet.of(sudokuSure[row][col]);

		//If Col is full we clean other possible possibleRows
		boolean isColFull = true;
		for (int row2 = (row - difrow3); row2 < (row - difrow3 + 3); row2++) {
			if ( this.sudokuSure[row2][col] == null ) {
				isColFull = false;
			}
		}
		if ( isColFull ) {
			//for (int row2 = (row - difrow3); row2 < (row - difrow3 + 3); row2++) {
			for (NumberSudoku numberSudoku : NumberSudoku.values()) {
					this.possibleCols.get(numberSudoku).remove(Arrays.asList(row /3 , col ));
			}
			//}	
			for (int row2 = (row - difrow3); row2 < (row - difrow3 + 3); row2++) {
				this.possibleCols.get(sudokuSure[row2][col]).add(Arrays.asList(row /3,col));
			}
		}
		//If col is full we clean other possible possibleCols
		boolean isRowFull = true;
		for (int col2 = (col - difcol3); col2 < (col - difcol3 + 3); col2++) {
			if ( this.sudokuSure[row][col2] == null ) {
				isRowFull = false;
			}
		}
		if ( isRowFull ) {
			//for (int col2 = (col - difcol3); col2 < (col - difcol3 + 3); col2++) {
				for (NumberSudoku numberSudoku : NumberSudoku.values()) {
					this.possibleRows.get(numberSudoku).remove(Arrays.asList(row, col/3));
				}
			//}	
			for (int col2 = (col - difcol3); col2 < (col - difcol3 + 3); col2++) {
				this.possibleRows.get(sudokuSure[row][col2]).add(Arrays.asList(row,col/3));
			}
		}
	}

	/**
	 * Check there is one NumberSudoku possible in each cell
	 */
	private void checkUniqueValueinCell() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (this.sudokuChoices[row][col].size() == 1) {
					if (this.sudokuSure[row][col] == null) {
						this.sudokuSure[row][col] = (NumberSudoku) this.sudokuChoices[row][col].iterator().next();
						this.setChoices(row, col);
					}
				}
			}
		}
	}

	private void checkUniqueCellforNumber(){
		
		for (int bigrow = 0; bigrow < 9; bigrow +=3 ) {
			for (int bigcol = 0; bigcol < 9; bigcol += 3 ) {
				for (NumberSudoku numberSudoku : NumberSudoku.values()) {
					Set<Integer>  howmanyRows = new HashSet<Integer>();
					Set<Integer>  howmanyCols = new HashSet<Integer>();
					for (int row = bigrow; row < bigrow + 3; row++) {
						for (int col = bigcol; col < bigcol + 3; col++) {
							if ( this.possibleRows.get(numberSudoku).contains(Arrays.asList(row, col /3)) ) {
								howmanyRows.add(row);
							}
							if ( this.possibleCols.get(numberSudoku).contains(Arrays.asList(row/3, col )) ) {
								howmanyCols.add(col);
							}
						}	
					}
					//If only feasible in one row and one col, then that cell is the one
					if ( ( howmanyCols.size() == 1 ) && ( howmanyRows.size() == 1) ){
						int rowActual = howmanyRows.iterator().next();// this.possibleRows.get(numberSudoku).get(0).get(0);
						int colActual = howmanyCols.iterator().next(); // this.possibleCols.get(numberSudoku).get(0).get(1);
						if ( sudokuSure[rowActual][colActual] == null ) {
							System.out.println("Found intersecction in row " + rowActual + " col " + colActual + " Value " + numberSudoku);
							this.sudokuSure[rowActual][colActual] = numberSudoku;
							setChoices(rowActual, colActual);
						}
					} 	
				}
				
			}
		}
	}

	private void checkSquare() {
		for (int bigrow = 0; bigrow < 9; bigrow += 3) {
			for (int bigcol = 0; bigcol < 9; bigcol += 3) {
				// We check if each NumberSudoku can be only in one cell in the square
				Map<NumberSudoku, List<List<Integer>>> mapSquare = new HashMap<NumberSudoku, List<List<Integer>>>();
				Map<NumberSudoku, Set<Integer>> mapSquareRows = new HashMap<NumberSudoku, Set<Integer>>();
				Map<NumberSudoku, Set<Integer>> mapSquareCols = new HashMap<NumberSudoku, Set<Integer>>();

				// Fill the mapSquare for each NumberSudoku
				for (NumberSudoku numberSudoku : NumberSudoku.values()) {
					mapSquare.put(numberSudoku, new ArrayList<List<Integer>>());
					mapSquareRows.put(numberSudoku, new HashSet<Integer>());
					mapSquareCols.put(numberSudoku, new HashSet<Integer>());
				}
				// For each cell we add the cell to the mapSquare for each NumberSudoku
				for (int row = bigrow; row < bigrow + 3; row++) {
					for (int col = bigcol; col < bigcol + 3; col++) {
						List<Integer> cell = new ArrayList<Integer>();
						cell.add(row);
						cell.add(col);
						for (Iterator it = this.sudokuChoices[row][col].iterator(); it.hasNext();) {
							NumberSudoku numPossible = (NumberSudoku) it.next();
							mapSquare.get(numPossible).add(cell);
							mapSquareRows.get(numPossible).add(row);
							mapSquareCols.get(numPossible).add(col);
						}
					}
				}
				// If there is only one cell possible in the square, that is the
				// cell for that NumberSudoku
				for (NumberSudoku number : mapSquare.keySet()) {
					if ( number == NumberSudoku.THREE ) {
						//System.out.println("En Three");
					}
					if (mapSquare.get(number).size() == 1) {
						int row = mapSquare.get(number).get(0).get(0);
						int col = mapSquare.get(number).get(0).get(1);
						if (this.sudokuSure[row][col] == null) {
							this.sudokuSure[row][col] = number;
							this.setChoices(row, col);
						}
					}
					// If a number can be only in one row, then is not possible
					// to be in the same row for different squares
					if (mapSquareRows.get(number).size() == 1) {
						int rowactual = mapSquareRows.get(number).iterator().next();
						for (int bigcol2 = 0; bigcol2 < 9; bigcol2 += 3) {
							this.possibleRows.get(number)
									.remove(Arrays.asList( rowactual, bigcol2 / 3));
						}
						this.possibleRows.get(number).add(Arrays.asList(mapSquareRows.get(number).iterator().next(), bigcol / 3));
					}
					if (mapSquareCols.get(number).size() == 1) {
						int colactual = mapSquareCols.get(number).iterator().next();
						for (int bigrow2 = 0; bigrow2 < 9; bigrow2 += 3) {
							if ( number == NumberSudoku.THREE ) {
								//System.out.println("In Three");
							}
							this.possibleCols.get(number)
									.remove(Arrays.asList(bigrow2 / 3, mapSquareCols.get(number).iterator().next()));
						}
						this.possibleCols.get(number).add(Arrays.asList(bigrow / 3, colactual));
					}
				}
			}
		}
	}

	private void checkRow() {
		// For each row
		for (int col = 0; col < 9; col++) {
			// We check if each NumberSudoku can be only in one cell in the
			// square
			// We have a Map for each NumberSudoku
			Map<NumberSudoku, List<List<Integer>>> mapSquare = new HashMap<NumberSudoku, List<List<Integer>>>();
			for (NumberSudoku numberSudoku : NumberSudoku.values()) {
				mapSquare.put(numberSudoku, new ArrayList<List<Integer>>());
			}
			// For each cell in the cell we feed the Map with the possible cells
			// for each NumberSudoku
			for (int row = 0; row < 9; row++) {
				// for (int col = bigcol; col < bigcol + 3; col++) {
				List<Integer> cell = new ArrayList<Integer>();
				cell.add(row);
				cell.add(col);
				// for (Iterator<Integer> iter = numbers.iterator();
				// iter.hasNext(); ) {
				for (Iterator it = this.sudokuChoices[row][col].iterator(); it.hasNext();) {
					NumberSudoku numPossible = (NumberSudoku) it.next();
					mapSquare.get(numPossible).add(cell);
					// mapSquare.get(numPossible).addAll(this.sudokuChoices[row][col]);
					// }
				}
			}
			// If a NumberSudoku has only one Choice then the NumberSudoku is in
			// that cell
			for (NumberSudoku number : mapSquare.keySet()) {
				if (mapSquare.get(number).size() == 1) {
					int rowcell = mapSquare.get(number).get(0).get(0);
					int colcell = mapSquare.get(number).get(0).get(1);
					if (this.sudokuSure[rowcell][colcell] == null) {
						this.sudokuSure[rowcell][colcell] = number;
						this.setChoices(rowcell, colcell);
					}
				}

			}
		}

	}

	private void checkCol() {
		// For each row
		for (int row = 0; row < 9; row++) {
			// We check if each NumberSudoku can be only in one cell in the
			// square
			// We have a Map for each NumberSudoku
			Map<NumberSudoku, List<List<Integer>>> mapSquare = new HashMap<NumberSudoku, List<List<Integer>>>();
			for (NumberSudoku numberSudoku : NumberSudoku.values()) {
				mapSquare.put(numberSudoku, new ArrayList<List<Integer>>());
			}
			// For each cell in the cell we feed the Map with the possible cells
			// for each NumberSudoku
			for (int col = 0; col < 9; col++) {
				List<Integer> cell = new ArrayList<Integer>();
				cell.add(row);
				cell.add(col);
				// for (Iterator<Integer> iter = numbers.iterator();
				// iter.hasNext(); ) {
				for (Iterator it = this.sudokuChoices[row][col].iterator(); it.hasNext();) {
					NumberSudoku numPossible = (NumberSudoku) it.next();
					mapSquare.get(numPossible).add(cell);
					// mapSquare.get(numPossible).addAll(this.sudokuChoices[row][col]);
					// }
				}
			}
			// If a NumberSudoku has only one Choice then the NumberSudoku is in
			// that cell
			for (NumberSudoku number : mapSquare.keySet()) {
				if (mapSquare.get(number).size() == 1) {
					int rowcell = mapSquare.get(number).get(0).get(0);
					int colcell = mapSquare.get(number).get(0).get(1);
					if (this.sudokuSure[rowcell][colcell] == null) {
						this.sudokuSure[rowcell][colcell] = number;
						this.setChoices(rowcell, colcell);
					}
				}

			}
		}

	}

	public void resolve() {
		this.printSudokuSure();
		this.setInitialChoices();
		this.printSudokuSure();
		for (int i = 0; i < 20; i++) {
			this.checkUniqueValueinCell();
			this.checkUniqueCellforNumber();
			this.checkSquare();
			this.checkRow();
			this.checkCol();
		}
		this.printSudokuSure();
		System.out.println("dddd");
		this.printSudokuChoices();
		System.out.println("dddd");
		
		/*
		 * this.printSudokuChoices(); this.checkRow(); this.checkSquare();
		 * this.checkCol(); this.checkRow(); this.checkSquare();
		 * this.checkCol(); this.checkSquare(); this.printSudokuSure();
		 */
	}
}
