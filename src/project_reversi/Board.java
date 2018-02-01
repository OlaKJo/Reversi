package project_reversi;

import java.util.ArrayList;

public class Board {
	
	private ArrayList<Tuple> validMoves;
	private GameMatrix boardMat;
	
	public Board(){
		boardMat = new GameMatrix();
		validMoves = new ArrayList<Tuple>();
		initBoard();
	}
	
	private void initBoard() {
		boardMat.setCoord(4, 4, 'X');
		boardMat.setCoord(5, 5, 'X');
		boardMat.setCoord(4, 5, 'O');
		boardMat.setCoord(5, 4, 'O');
	}

	public Board(char[][] boardMat){
		
	}
	
	public void makeMove(int x, int y, char player){
		boardMat.setCoord(x, y, player);
	}
	
	public void printBoard() {
		for(int i = 0; i < 500; i++) {
			System.out.println("");
		}
		
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				System.out.format("%2c", boardMat.getCoord(i, j));
			}
			System.out.println("");
		}
	}

}
