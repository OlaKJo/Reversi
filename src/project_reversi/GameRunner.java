package project_reversi;

import java.util.Scanner;

public class GameRunner {
	
	public static void main(String[] args) {
		Game board = new Game();
		runGame(board, Util.PLAYER1);
	}

	public static void runGame(Game board, char firstPlayer) {
		char curPlayer = firstPlayer;
		Scanner console = new Scanner(System.in);
		
		board.printBoard();

		boolean finished = false;
		while (!finished) {
			boolean success = false;
			while (!success) {
				System.out.println("player " + curPlayer + "'s turn:");
				String move = console.nextLine();
				int row = (int) (move.charAt(0) - 'a') + 1;
				int col = (int) (move.charAt(1) - '0');
				success = board.makeMove(row, col, curPlayer);
			}
			board.printBoard();
			if(board.getValidMoves().isEmpty()) {
				board.updateValid(curPlayer);
				if(board.getValidMoves().isEmpty()) {
					finished = true;
				}
			} else {
				curPlayer = Util.inversePlayer(curPlayer);
			}
			// finished = board.isFinished();
		}
		board.printScore();
	}

}
