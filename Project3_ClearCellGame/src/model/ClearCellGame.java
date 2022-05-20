package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game.
 * We define an empty cell as BoardCell.EMPTY. An empty row is defined as one
 * where every cell corresponds to BoardCell.EMPTY.
 * 
 * @author Department of Computer Science, UMCP
 */

public class ClearCellGame extends Game {
	private Random random;
	private int strategy; 
	private int score;
	private int animationSteps;

	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board. The random parameter is used for the generation of random
	 * cells. The strategy parameter defines which clearing cell strategy to use
	 * (for this project it will be 1). For fun, you can add your own strategy by
	 * using a value different that one.
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random; 
		this.strategy = strategy; 
		animationSteps=0;
		score=0;
	}

	/**
	 * The game is over when the last board row (row with index board.length -1) is
	 * different from empty row.
	 */
	public boolean isGameOver() {
		return !emptyRow(getMaxRows()-1);
	}
	

	public int getScore() {//Returns score
		return score;
	}

	/**
	 * This method will attempt to insert a row of random BoardCell objects if the
	 * last board row (row with index board.length -1) corresponds to the empty row;
	 * otherwise no operation will take place.
	 */
	public void nextAnimationStep() {//Produces the next animation step
		if(emptyRow(getMaxRows()-1)==true) {//Processes only if last row is empty 
			for (int i = 0;i<(getMaxRows());i++) {
				if (emptyRow(i)==true) {
					for(int j=0;j<getMaxCols();j++) {
						if(i==0) {
							setBoardCell(i,j,BoardCell.getNonEmptyRandomBoardCell(random));//if its the first row 
						}
						else {
							for(int row=i;row>0;row--) {//Shifts the rows upward 
								super.board[row][j]=super.board[row-1][j];	
							}
							setBoardCell(0,j,BoardCell.getNonEmptyRandomBoardCell(random));
						}
					}
					animationSteps++;//For future use to ensure maximum efficiency of the program
					break;//To make sure only one row is added 
				}			
			}			
		}
		
	}

	/**
	 * This method will turn to BoardCell.EMPTY the cell selected and any adjacent
	 * surrounding cells in the vertical, horizontal, and diagonal directions that
	 * have the same color. The clearing of adjacent cells will continue as long as
	 * cells have a color that corresponds to the selected cell. Notice that the
	 * clearing process does not clear every single cell that surrounds a cell
	 * selected (only those found in the vertical, horizontal or diagonal
	 * directions).
	 * 
	 * IMPORTANT: Clearing a cell adds one point to the game's score.<br />
	 * <br />
	 * 
	 * If after processing cells, any rows in the board are empty,those rows will
	 * collapse, moving non-empty rows upward. For example, if we have the following
	 * board (an * represents an empty cell):<br />
	 * <br />
	 * RRR<br />
	 * GGG<br />
	 * YYY<br />
	 * * * *<br/>
	 * <br />
	 * then processing each cell of the second row will generate the following
	 * board<br />
	 * <br />
	 * RRR<br />
	 * YYY<br />
	 * * * *<br/>
	 * * * *<br/>
	 * <br />
	 * IMPORTANT: If the game has ended no action will take place.
	 * 
	 * 
	 */
	public void processCell(int rowIndex, int colIndex) {
		BoardCell selected = super.board[rowIndex][colIndex];//Storing the set copy of the cell 
		if(!(selected==BoardCell.EMPTY)){//Checking if the clicked cell is empty
			setBoardCell(rowIndex,colIndex,BoardCell.EMPTY);//Setting the selected cell to empty 
			score++;//Increasing score
			int i =1;
			while ((colIndex+i)<getMaxCols()) {//Checking Horizontal Right 
				if (selected==super.board[rowIndex][colIndex+i]) {
					setBoardCell(rowIndex, (colIndex+i), BoardCell.EMPTY);
					score++;
				}
				else {
					break;
				}
				i++;
			}
			i=1;
			while((colIndex-i)>=0) {//Checking Horizontal left 
				if(selected==super.board[rowIndex][colIndex-i]) {
					setBoardCell(rowIndex, (colIndex-i), BoardCell.EMPTY);
					score++;
				}
				else {
					break;
				}
				i++;
			}
			i=1;
			while ((rowIndex+i)<getMaxRows()) {//Checking vertical down
				if (selected==super.board[rowIndex+i][colIndex]) {
					setBoardCell(rowIndex+i, colIndex, BoardCell.EMPTY);
					score++;
				}
				else {
					break;
				}
				i++;
			}
			i=1;
			while((rowIndex-i)>=0) {//Checking vertical up
				if(selected==super.board[rowIndex-i][colIndex]) {
					setBoardCell(rowIndex-i, colIndex, BoardCell.EMPTY);
					score++;
				}
				else {
					break;
				}
				i++;
			}
			i=1;
			while(((rowIndex-i)>=0)&&((colIndex-i)>=0)){//Checking Diagnal up left 
				if(selected==super.board[rowIndex-i][colIndex-i]) {
					setBoardCell(rowIndex-i, colIndex-i, BoardCell.EMPTY);
					score++;
				}
				else {
					break;
				}
				i++;
			}
			i=1;
			while(((rowIndex-i)>=0)&&((colIndex+i)<getMaxCols())){//Checking Diagnal up right
				if(selected==super.board[rowIndex-i][colIndex+i]) {
					setBoardCell(rowIndex-i, colIndex+i, BoardCell.EMPTY);
					score++;
				}
				else {
					break;
				}
				i++;
			}
			i=1;
			while(((rowIndex+i)<getMaxRows())&&((colIndex-i)>=0)){//Checking Diagnal down left 
				if(selected==super.board[rowIndex+i][colIndex-i]) {
					setBoardCell(rowIndex+i, colIndex-i, BoardCell.EMPTY);
					score++;
				}
				else {
					break;
				}
				i++;
			}
			i=1;
			while ((rowIndex+i)<getMaxRows()&&(colIndex+i)<getMaxCols()) {//Checking diagnal down right
				if (selected==super.board[rowIndex+i][colIndex+i]) {
					setBoardCell(rowIndex+i, colIndex+i, BoardCell.EMPTY);
					score++;
				}
				else {
					break;
				}
				i++;
			}
			for (int rowNo=0;rowNo<getMaxRows();rowNo++) {//Checking for empty rows 
				collapseRow(rowNo);
			}
		}	
	}
	
	/**
	 * Checks if a row is empty 
	 */
	public boolean emptyRow(int rowIndex) {
		if(super.board==null) {//Making sure the board is not null
			return false; 
		}
		BoardCell[][]copy = super.board;
		if(copy.length<=rowIndex) {//Check if the length of array is valid 
			return false; 
		}
		for (int i =0; i<copy[rowIndex].length;i++) {
			if (copy[rowIndex][i]!=BoardCell.EMPTY) {//Checks if the row is empty 
				return false;
			}
		}
		return true;
	}

	/**
	 * Collapses a row if a row is empty
	 */
	public void collapseRow(int Index) {
		if(emptyRow(Index)==true) {
			for(int row=Index;row<getMaxRows()-1;row++) {//Iterator to collapse row. 
				for(int col = 0;col<getMaxCols();col++) {
					setBoardCell(row,col, super.board[row+1][col]);
				}
			}
		}
		
	}

}