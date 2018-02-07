package project_reversi;

import java.util.Scanner;

public class GameRunner {
	
	private static Scanner console = new Scanner(System.in);;
	
	public static void main(String[] args) {
		Game board = new Game();
		char humanPlayer = 'a';
		int thinkTime = 0;
		
		while(humanPlayer != Util.PLAYER1 && humanPlayer != Util.PLAYER2) {
			System.out.printf("Play as which marker? %c plays first. (%c/%c): \n", Util.PLAYER1, Util.PLAYER1, Util.PLAYER2);
			humanPlayer = console.nextLine().charAt(0);
		}
		while(thinkTime <= 0) {
			System.out.println("Specify amount of time per move for the agent (ms): ");
			thinkTime = Integer.parseInt(console.nextLine());
		}
		
		// choose whos going to start
		//runGame(board, Util.PLAYER1);
		runAgentGame(board, humanPlayer, thinkTime);
	}

	//For two players, not accessible at the moment.
	public static void runGame(Game board, char firstPlayer) {
		char curPlayer = firstPlayer;

		boolean finished = false;
		while (!finished) {
			board.printBoard(curPlayer);

			boolean success = false;
			while (!success) {
				System.out.println("player " + curPlayer + "'s turn:");
				String move = console.nextLine();
				int col = (int) (Character.toLowerCase(move.charAt(1)) - 'a') + 1;
				int row = (int) (move.charAt(0) - '0');
				success = board.makeMove(row, col, curPlayer);
			}
			board.updateValid(curPlayer);
			board.updateValid(Util.inversePlayer(curPlayer));
			if(board.getValidMoves(curPlayer).isEmpty()) {
				
				if(board.getValidMoves(Util.inversePlayer(curPlayer)).isEmpty()) {
					finished = true;
				}
			} else {
				curPlayer = Util.inversePlayer(curPlayer);
			}
			// finished = board.isFinished();
		}
		board.printBoard(curPlayer);
		board.printScore();
	}
	
	public static void runAgentGame(Game board, char humanPlayer, int thinkTime) {
		char curPlayer = Util.PLAYER1;

		char agentPlayer = Util.inversePlayer(humanPlayer);
		Agent agent = new Agent(agentPlayer, thinkTime);
		

		boolean finished = false;
		while (!finished) {
			board.printBoard(curPlayer);

			boolean success = false;
			while (!success) {
				if(curPlayer == agentPlayer) {
					Tuple agentMove = agent.chooseMove(board);
					int col = agentMove.getX();
					int row = agentMove.getY();
					
					success = board.makeMove(col, row, curPlayer);
				} else {
					System.out.println("player " + curPlayer + "'s turn:");
					String move = console.nextLine();
					int col = (int) (Character.toLowerCase(move.charAt(1)) - 'a') + 1;
					int row = (int) (move.charAt(0) - '0');
				
					success = board.makeMove(row, col, curPlayer);
				}
			}
			board.updateValid(curPlayer);
			board.updateValid(Util.inversePlayer(curPlayer));
			if(board.getValidMoves(Util.inversePlayer(curPlayer)).isEmpty()) {
				
				if(board.getValidMoves(curPlayer).isEmpty()) {
					finished = true;
				}
			} else {
				curPlayer = Util.inversePlayer(curPlayer);
			}
			// finished = board.isFinished();
		}
		board.printBoard(curPlayer);
		board.printScore();
	}

}
