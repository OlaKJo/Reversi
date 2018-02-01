package project_reversi;

import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		Board board = new Board();
		char curPlayer = 'X';
		
		board.printBoard();
		
		while(true){
			String move = console.nextLine();
			int row =  (int) ( move.charAt(0) - 'a' ) + 1;
			int col = (int) ( move.charAt(1) - '0' );
			board.makeMove(row, col, curPlayer);
			board.printBoard();
			if( curPlayer == 'X'){
				curPlayer = 'O';
			}else{
				curPlayer = 'X';
			}
			
		}
		
	}

}
