import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * This is a program of an interactive game of Tic-Tac-Toe on a 4x4x4 3D layout
 * The game is played against an "intelligent" computer and has the capacity
 * to win the game.
 *
 * This code implments methods and arrays written by Delbert Bailey in the code
 * BoardTest.java, found on the eCommons website.
 *
 * Author: Nelson Perez (neeperez@ucsc.edu)
 */
 public class TTT3D {
	static final boolean WIN = true;
	static final boolean LOSE = false;
	static final int USERNUM = 5;
	static final int COMPNUM = 1;
	static int[][][] board = new int[4][4][4];
	static int[] sums = new int[76];
	static final int[][][] lines = {
		{{0,0,0},{0,0,1},{0,0,2},{0,0,3}},  //lev 0; row 0 rows per lvl
		{{0,1,0},{0,1,1},{0,1,2},{0,1,3}},  //       row 1     
		{{0,2,0},{0,2,1},{0,2,2},{0,2,3}},  //       row 2     
		{{0,3,0},{0,3,1},{0,3,2},{0,3,3}},  //       row 3     
		{{1,0,0},{1,0,1},{1,0,2},{1,0,3}},  //lev 1; row 0     
		{{1,1,0},{1,1,1},{1,1,2},{1,1,3}},  //       row 1     
		{{1,2,0},{1,2,1},{1,2,2},{1,2,3}},  //       row 2     
		{{1,3,0},{1,3,1},{1,3,2},{1,3,3}},  //       row 3     
		{{2,0,0},{2,0,1},{2,0,2},{2,0,3}},  //lev 2; row 0     
		{{2,1,0},{2,1,1},{2,1,2},{2,1,3}},  //       row 1     
		{{2,2,0},{2,2,1},{2,2,2},{2,2,3}},  //       row 2       
		{{2,3,0},{2,3,1},{2,3,2},{2,3,3}},  //       row 3     
		{{3,0,0},{3,0,1},{3,0,2},{3,0,3}},  //lev 3; row 0     
		{{3,1,0},{3,1,1},{3,1,2},{3,1,3}},  //       row 1 
		{{3,2,0},{3,2,1},{3,2,2},{3,2,3}},  //       row 2       
		{{3,3,0},{3,3,1},{3,3,2},{3,3,3}},  //       row 3           
		
		{{0,0,0},{0,1,0},{0,2,0},{0,3,0}},  //lev 0; col 0 columns
		{{0,0,1},{0,1,1},{0,2,1},{0,3,1}},  //       col 1    
		{{0,0,2},{0,1,2},{0,2,2},{0,3,2}},  //       col 2    
		{{0,0,3},{0,1,3},{0,2,3},{0,3,3}},  //       col 3    
		{{1,0,0},{1,1,0},{1,2,0},{1,3,0}},  //lev 1; col 0     
		{{1,0,1},{1,1,1},{1,2,1},{1,3,1}},  //       col 1    
		{{1,0,2},{1,1,2},{1,2,2},{1,3,2}},  //       col 2    
		{{1,0,3},{1,1,3},{1,2,3},{1,3,3}},  //       col 3    
		{{2,0,0},{2,1,0},{2,2,0},{2,3,0}},  //lev 2; col 0     
		{{2,0,1},{2,1,1},{2,2,1},{2,3,1}},  //       col 1    
		{{2,0,2},{2,1,2},{2,2,2},{2,3,2}},  //       col 2    
		{{2,0,3},{2,1,3},{2,2,3},{2,3,3}},  //       col 3    
		{{3,0,0},{3,1,0},{3,2,0},{3,3,0}},  //lev 3; col 0     
		{{3,0,1},{3,1,1},{3,2,1},{3,3,1}},  //       col 1
		{{3,0,2},{3,1,2},{3,2,2},{3,3,2}},  //       col 2
		{{3,0,3},{3,1,3},{3,2,3},{3,3,3}},  //       col 3
	        
		{{0,0,0},{1,0,0},{2,0,0},{3,0,0}},  //front vert. plane cols
	        {{0,0,1},{1,0,1},{2,0,1},{3,0,1}},
	        {{0,0,2},{1,0,2},{2,0,2},{3,0,2}},
	        {{0,0,3},{1,0,3},{2,0,3},{3,0,3}},
	        {{0,1,0},{1,1,0},{2,1,0},{3,1,0}},  //cols vert. plane 1 back
	        {{0,1,1},{1,1,1},{2,1,1},{3,1,1}},
	        {{0,1,2},{1,1,2},{2,1,2},{3,1,2}},
	        {{0,1,3},{1,1,3},{2,1,3},{3,1,3}},
	        {{0,2,0},{1,2,0},{2,2,0},{3,2,0}},  //cols in vert plane 2 back
	        {{0,2,1},{1,2,1},{2,2,1},{3,2,1}},
	        {{0,2,2},{1,2,2},{2,2,2},{3,2,2}},
	        {{0,2,3},{1,2,3},{2,2,3},{3,2,3}},
	        {{0,3,0},{1,3,0},{2,3,0},{3,3,0}},  //cols in vert plane in rear
	        {{0,3,1},{1,3,1},{2,3,1},{3,3,1}},
	        {{0,3,2},{1,3,2},{2,3,2},{3,3,2}},
	        {{0,3,3},{1,3,3},{2,3,3},{3,3,3}},
	     
	        {{0,0,0},{0,1,1},{0,2,2},{0,3,3}},  //diags in lev 0
		{{0,3,0},{0,2,1},{0,1,2},{0,0,3}},
	        {{1,0,0},{1,1,1},{1,2,2},{1,3,3}},  //diags in lev 1
	        {{1,3,0},{1,2,1},{1,1,2},{1,0,3}},
	        {{2,0,0},{2,1,1},{2,2,2},{2,3,3}},  //diags in lev 2
	        {{2,3,0},{2,2,1},{2,1,2},{2,0,3}},
	        {{3,0,0},{3,1,1},{3,2,2},{3,3,3}},  //diags in lev 3
	        {{3,3,0},{3,2,1},{3,1,2},{3,0,3}},
	        {{0,0,0},{1,0,1},{2,0,2},{3,0,3}},  //diags in vert plane front
	        {{3,0,0},{2,0,1},{1,0,2},{0,0,3}},
	        {{0,1,0},{1,1,1},{2,1,2},{3,1,3}},  //diags in vert plane 1 back
	        {{3,1,0},{2,1,1},{1,1,2},{0,1,3}},
	        {{0,2,0},{1,2,1},{2,2,2},{3,2,3}},  //diags in vert plane 2 back
	        {{3,2,0},{2,2,1},{1,2,2},{0,2,3}},
	        {{0,3,0},{1,3,1},{2,3,2},{3,3,3}},  //diags in vert plane rear
	        {{3,3,0},{2,3,1},{1,3,2},{0,3,3}},
	        {{0,0,0},{1,1,0},{2,2,0},{3,3,0}},  //diags left slice      
	        {{3,0,0},{2,1,0},{1,2,0},{0,3,0}},        
	        {{0,0,1},{1,1,1},{2,2,1},{3,3,1}},  //diags slice one to right
	        {{3,0,1},{2,1,1},{1,2,1},{0,3,1}},        
	        {{0,0,2},{1,1,2},{2,2,2},{3,3,2}},  //diags slice two to right 
	        {{3,0,2},{2,1,2},{1,2,2},{0,3,2}},        
	        {{0,0,3},{1,1,3},{2,2,3},{3,3,3}},  //diags right slice      
	        {{3,0,3},{2,1,3},{1,2,3},{0,3,3}},        
	        {{0,0,0},{1,1,1},{2,2,2},{3,3,3}},  //cube vertex diags
	        {{3,0,0},{2,1,1},{1,2,2},{0,3,3}},
	        {{0,3,0},{1,2,1},{2,1,2},{3,0,3}},
	        {{3,3,0},{2,2,1},{1,1,2},{0,0,3}}        
	    };
	    
    public static void main(String[] args) throws FileNotFoundException {
	//number of moves will be used to determine if there is a draw
	int numberOfMoves = 0;
	//If there is a file in the command line, read it
    	if (args.length > 0) {
		numberOfMoves = readFile(args);
		}
	Scanner scan = new Scanner(System.in);
	boolean compWin = false;
	boolean userWin = false;
	boolean draw = false;
	printBoard();
	//While the computer or user haven't won or come to a draw, play game
	while (!compWin && !userWin && !draw) {
		System.out.println("Type your move as one three-digit number "
					+ "(Level, Row, Column)");
		String num = scan.next();
		// Try and read ints separately
		int userLvl = Integer.parseInt(num.substring(0, 1));
		int userRow = Integer.parseInt(num.substring(1, 2));
		int userCol = Integer.parseInt(num.substring(2));
		//Test to see ig the user's input is invalid
		while ((userLvl > 3 || userLvl < 0) || 
			(userRow > 3 || userRow < 0) || 
			(userCol > 3 || userCol < 0)) {
			System.out.println("That is an invalid input, "
 				 + "please enter a valid three-digit number.");
			num = scan.next();
			userLvl = Integer.parseInt(num.substring(0, 1));
			userRow = Integer.parseInt(num.substring(1, 2));
			userCol = Integer.parseInt(num.substring(2));
		}
		//Check to see if the cell is already occupied
		while (!isEmpty(userLvl, userRow, userCol)) {
			System.out.println("That cell is already occupied! " 
			+ "Please enter a valid three-digit number.");
			num = scan.next();
			userLvl = Integer.parseInt(num.substring(0, 1));
			userRow = Integer.parseInt(num.substring(1, 2));
			userCol = Integer.parseInt(num.substring(2));
		}
		move(userLvl, userRow, userCol, USERNUM);
		numberOfMoves++;
		//If the player has made the 64th move, end the game
		if (numberOfMoves == 64) {
			draw = true;
			System.out.println("The game is a draw!");
			break;
		}
		compLineSums();
		//IF there is a line occupied completely by the user, 
		//Set userwin to true.
		for (int i = 0; i < 76; i++) {
			if (sums[i] == 20) {
				userWin = true;
				printBoard();
				System.out.println("Congratulations, you win "
						+ "the game!");
			} 
		}
		//Break the loop if the user has won
		if (userWin) {
			break;
		}
		compMove();
		numberOfMoves++;
		//Check for a draw
		if (numberOfMoves == 64) {
			System.out.println("The game is a draw!");
			draw = true;
			break;
		}
		compLineSums();
		//check for a win
		for (int i = 0; i < 76; i++) {
			if (sums[i] == 4) {
				compWin = true;
				printBoard();
				System.out.println("Computer wins!");
				break;
			}
		}
		if (compWin){
			break;
		}
		printBoard();
	}
}

	/*This method computes the computer's move, checking various conditions
 	 *in order to make the computer choose the correct move
	 */
	static void compMove() {
		Random rand = new Random();
		int randLine = rand.nextInt(77);
		compLineSums();
		//lineSum1 & 2 used in checking to see if line is occupied with
		//3 X's or 3 O's
		int lineSum1 = findLineSum(COMPNUM * 3, 0);
		int lineSum2 = findLineSum(USERNUM * 3, 0);
		//Possible fork line for X's and O's
		int posForkLine1 = findLineSum(USERNUM * 2, 0);
		int posForkLine2 = findLineSum(USERNUM * 2, posForkLine1 + 1);
		int posForkLine3 = findLineSum(COMPNUM * 2, 0);
		int posForkLine4 = findLineSum(COMPNUM * 2, posForkLine3 + 1);
		//Cell address used to make a move
		int[][] mtCelAdr = new int[76][1];
		//If there is a line with 3 O's (computer), play the empty cell
		if (lineSum1 != -1) {
			mtCelAdr[lineSum1] = findEmptyCel(lines[lineSum1]);
			move(mtCelAdr[lineSum1], COMPNUM);
		//If there is a line with 3 X's, block the user
		} else if (lineSum2 != -1) {
			mtCelAdr[lineSum2] = findEmptyCel(lines[lineSum2]);
			move(mtCelAdr[lineSum2], COMPNUM);
		/*If there 2 lines that make a possible fork, check if they
		 *share a common empty cell. If they don't look for another
		 *line that would make a fork. If there is no other line, play
		 in a non-dead cell. If there is a fork, play the empty cell
		 */
		} else if (posForkLine1 != -1 && posForkLine2 != -1) {
			while (findComMtCel(lines[posForkLine1],
				 lines[posForkLine2]) == null) {
				posForkLine2 = findLineSum(USERNUM * 2,
							 posForkLine2 + 1);
				if (posForkLine1 == -1 || posForkLine2 == -1) {
					break;
				}
			} 
			if (posForkLine1 == -1 || posForkLine2 == -1) {
				while (isDeadLine(lines[randLine])) {
					randLine = rand.nextInt(77);
				}
				mtCelAdr[randLine] =
						 findEmptyCel(lines[randLine]);
				move(mtCelAdr[randLine], COMPNUM);
			} else {
				mtCelAdr[posForkLine1] = 
				findComMtCel(lines[posForkLine1],
				lines[posForkLine2]);
				move(mtCelAdr[posForkLine1], COMPNUM);
			}
		//Same thing, but if there is a fork for the computer
		} else if (posForkLine3 != -1 && posForkLine4 != -1) {
			while (findComMtCel(lines[posForkLine3], 
				lines[posForkLine4]) == null) {
				posForkLine4 = findLineSum(COMPNUM * 2,
						 posForkLine4 + 1);
				if (posForkLine3 == -1 || posForkLine4 == -1) {
					break;
				}
			}
			if (posForkLine3 == -1 || posForkLine4 == -1) {
				while (isDeadLine(lines[randLine])) {
					randLine = rand.nextInt(77);
				}
				mtCelAdr[randLine] = 
						  findEmptyCel(lines[randLine]);
				move(mtCelAdr[randLine], COMPNUM);
			} else {
				mtCelAdr[posForkLine3] =
					 findComMtCel(lines[posForkLine3],
					 lines[posForkLine4]);
				move(mtCelAdr[posForkLine3], COMPNUM);
			}
		//Play in a non dead line 
		} else {
			while (isDeadLine(lines[randLine])) {
				randLine = rand.nextInt(77);
			}
			mtCelAdr[randLine] = findEmptyCel(lines[randLine]);
			move(mtCelAdr[randLine], COMPNUM);
		}
	}
	/*This method reads the setup file given in the command line,
	 *using the String given in the parameters, setting up the game board
       	 *and returning an integer with the number of moves used
	 */ 
	static int readFile(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new FileInputStream(args[0]));
		int numberofRecords = scan.nextInt();
		int lvl, row, col, val;
		for (int i = 1; i <= numberofRecords; i++) {
			lvl = scan.nextInt();
			row = scan.nextInt();
			col = scan.nextInt();
			val = scan.nextInt();
			move(lvl, row, col, val);
		}
		return numberofRecords;
	
	}

	//Prints the board, checking the numbers in each cell to see which char
	//(x's or o's or _'s) to place on the board
	static void printBoard() {
		for (int lev = 3; lev >= 0; lev--) {
			for (int row = 3; row >= 0; row--) {
				System.out.print(lev + "" + row + " ");
				for (int col = 0; col < 4; col++) {
					if (board[lev][row][col] == 0) {
						System.out.print(" _");
					} else if (board[lev][row][col] == 1) {
						System.out.print(" O");
					} else {
						System.out.print(" X");
					}
				}
				System.out.printf("\n");
			}
			System.out.printf("\n");
		}
		System.out.println("    0 1 2 3");
	}

	//Check to see if the given cell address is empty
	static boolean isEmpty(int[] celAdr) {
		if (board[celAdr[0]][celAdr[1]][celAdr[2]] == 0) {
			return true;
		} else {
			return false;
		}
	}
	//Check to see if the given cell is occupied by the user
	static boolean occupiedByUser(int[] celAdr) {
		if(board[celAdr[0]][celAdr[1]][celAdr[2]] == 5) {
			return true;
		} else {
			return false;
		}
	}
	
	//Check to see if the given cell is occupied by the computer
	static boolean occupiedByComp(int[] celAdr) {
		if(board[celAdr[0]][celAdr[1]][celAdr[2]] == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	//Same isEmpty method with different parameters (given the lvl, row, &
	//col rather than the cell address
	static boolean isEmpty(int lvl, int row, int col) {
		if (board[lvl][row][col] == 0) {
			return true;
		} else {
			return false;
		}
	}

	//Place a move at the given cell address with the specified value
	static void move(int[] celAdr, int val) {
		move(celAdr[0], celAdr[1], celAdr[2], val);
	}

	//Place a move using the specified lvl, col, row for the given value
	static void move(int lev, int row, int col, int val) {
		board[lev][row][col] = val;
	}


	//See if two given cells are equal
	static boolean isEqual(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}

	//Find the empty cell in a given line
	static int[] findEmptyCel(int[][] line) {
		for (int i = 0; i < 4; i++) {
			if (isEmpty(line[i]))
				return line[i];
		}
		return null;
	}

	//Find common empty cell to two lines
	static int[] findComMtCel(int[][] line1, int[][] line2) {
		for (int i = 0; i < line1.length; i++) {
			for (int j = 0; j < line1.length; j++) {
				if (isEqual(line1[i], line2[j])
				 && isEmpty(line1[i]) && isEmpty(line2[j])) {
					return line1[i];
				}
			}
		}
		return null;
	}
	//Compute the sum in each line
	static void compLineSums() {
		for (int i = 0; i < sums.length; i++) {
			sums[i] = 0;
			for (int j = 0; j < 4; j++) {
				sums[i] +=
				 board[lines[i][j][0]][lines[i][j][1]]
					[lines[i][j][2]];
			}
		}
	}

	//Find a line with a specified sum
	static int findLineSum(int sum, int startIndex) {
		if (startIndex != -1) {
			for (int i = startIndex; i < sums.length; i++) {
				if (sums[i] == sum)
					return i; // line i has the sum
			}
		}
		return -1; // no such sum found
	}
	
	//Check to see if the given line is a dead line
	static boolean isDeadLine(int[][] line) {
		int userCount = 0;
		int compCount = 0;
		for (int i = 0; i < 4; i++) {
			if (occupiedByUser(line[i])) {
				userCount++;
			} else if (occupiedByComp(line[i])) {
				compCount++;
			}
		}
		if(userCount > 0 && compCount > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
			
}
