package project_reversi;

import java.util.Scanner;

public class GameRunner {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		Game board = new Game();
		char curPlayer = Util.PLAYER1;

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
			// finished = board.isFinished();
		}
		curPlayer = Util.inversePlayer(curPlayer);
	}

}
