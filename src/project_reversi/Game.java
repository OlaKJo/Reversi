package project_reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Game {
	
	private List<Tuple> player1ValidMoves;
	private List<Tuple> player2ValidMoves;

	private GameMatrix boardMat;
	
	public Game(){
		boardMat = new GameMatrix();
		player1ValidMoves = new ArrayList<Tuple>();
		player2ValidMoves = new ArrayList<Tuple>();
		initBoard();
		updateValid(Util.PLAYER1);
	}
	
	private void initBoard() {
		boardMat.setCoord(4, 4, Util.PLAYER1);
		boardMat.setCoord(5, 5, Util.PLAYER1);
		boardMat.setCoord(4, 5, Util.PLAYER2);
		boardMat.setCoord(5, 4, Util.PLAYER2);
	}

	public Game(char[][] boardMat, char player){
		this.boardMat = new GameMatrix(boardMat);
		player1ValidMoves = new ArrayList<Tuple>();
		player2ValidMoves = new ArrayList<Tuple>();
		updateValid(player);
	}
	
	public Game(Game g, char player) {
		this.boardMat = g.boardMat;
		player1ValidMoves = g.player1ValidMoves;
		player2ValidMoves = g.player2ValidMoves;
		updateValid(player);
	}
	
	public boolean makeMove(int x, int y, char player){
		List<Tuple> validMoves = (player == Util.PLAYER1 ? player1ValidMoves : player2ValidMoves);
		
		if(validMoves.contains(new Tuple(x,y))) {
			boardMat.setCoord(x, y, player);
			flipMarkers(x, y, player);
			updateValid(Util.inversePlayer(player));
			updateValid(player);
			
			return true;
		} else {
			return false;
		}
	}
	
	private void flipMarkers(int x, int y, char player) {
				Tuple pos = new Tuple(x, y);
								
				for(int k = -1; k <= 1; k++) {
					for(int l = -1; l <= 1; l++) {
						if(k == 0 && l == 0)
							continue;
						checkNFlip(new Tuple(pos.getX() + l, pos.getY() + k), k, l, player, true); 
					}
				}
				
	}
	
	private boolean checkNFlip(Tuple pos, int k, int l, char player, boolean firstLevel) {
		if(pos.getX() < 1 || pos.getX() > 8 || pos.getY() < 1 || pos.getY() > 8)
			return false;
		if((boardMat.getCoord(pos.getX(), pos.getY()) == Util.EMPTY))
			return false;
		if(firstLevel) {
			if((boardMat.getCoord(pos.getX(), pos.getY()) != player)) {
				boolean flip = checkNFlip(new Tuple(pos.getX()+l, pos.getY()+k), k, l, player, false);
				if( flip ) {
					boardMat.setCoord(pos.getX(), pos.getY(), player);
					return true;
				}
				return false;
			
			}else {
				return false;
			}
		}else {
			if(boardMat.getCoord(pos.getX(), pos.getY()) == player) {
				return true;
			} else {
				boolean flip = checkNFlip(new Tuple(pos.getX()+l, pos.getY()+k), k, l, player, false);
				if( flip ) {
					boardMat.setCoord(pos.getX(), pos.getY(), player);
					return true;
				}
				return false;
			}
		}
		
	}

	public void updateValid(char player) {
		List<Tuple> validMoves = (player == Util.PLAYER1 ? player1ValidMoves : player2ValidMoves);

		validMoves.clear();
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				if(boardMat.getCoord(i, j) != Util.EMPTY)
					continue;
				Tuple pos = new Tuple(i, j);
								
				for(int k = -1; k <= 1; k++) {
					for(int l = -1; l <= 1; l++) {
						if(k == 0 && l == 0)
							continue;
						
						if(checkDir(new Tuple(pos.getX() + l, pos.getY() + k), k, l, player, true)) {
							validMoves.add(pos);
							//break both for loops since valid move confirmed
							l = k = 2;
						}
					}
				}
			}
		}
	}
	
	private boolean checkDir(Tuple pos, int k, int l, char player, boolean firstLevel) {
		if(pos.getX() < 1 || pos.getX() > 8 || pos.getY() < 1 || pos.getY() > 8)
			return false;
		if((boardMat.getCoord(pos.getX(), pos.getY()) == Util.EMPTY))
			return false;
		if(firstLevel) {
			if((boardMat.getCoord(pos.getX(), pos.getY()) != player)) {
				return checkDir(new Tuple(pos.getX()+l, pos.getY()+k), k, l, player, false);
			}else {
				return false;
			}
		}else {
			if(boardMat.getCoord(pos.getX(), pos.getY()) == player) {
				return true;
			} else {
				return checkDir(new Tuple(pos.getX()+l, pos.getY()+k), k, l, player, false);
			}
		}
	}

	public void printBoard(char player) {
		List<Tuple> validMoves = (player == Util.PLAYER1 ? player1ValidMoves : player2ValidMoves);

		for(int i = 0; i < 500; i++) {
			System.out.println("");
		}
		
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				char printChar = boardMat.getCoord(i, j);
				if(validMoves.contains(new Tuple(i, j))) {
					printChar = Util.VALIDMOVE;
				}
				System.out.format("%2c", printChar);
			}
			System.out.println("");
		}
	}
	
	public List<Tuple> getValidMoves(char player) {
		List<Tuple> validMoves = (player == Util.PLAYER1 ? player1ValidMoves : player2ValidMoves);

		return new ArrayList<Tuple>(validMoves);
	}
	
	public Tuple calculateScore() {
		int score1 = 0;
		int score2 = 0;
		
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				if(boardMat.getCoord(i, j) == Util.PLAYER1) {
					score1++;
				} else if(boardMat.getCoord(i, j) == Util.PLAYER2) {
					score2++;
				}
			}
		}
		return new Tuple(score1, score2);
	}

	public void printScore() {
		
		Tuple scores = calculateScore();
		
		int score1 = scores.getX();
		int score2 = scores.getY();

		String winnerText;
		String genericWinner = "The winner is: ";
		
		if(score1 > score2) {
			winnerText = genericWinner + Util.PLAYER1;
		} else if(score1 < score2) {
			winnerText = genericWinner + Util.PLAYER2;
		} else {
			winnerText = "It's a draw!";
		}
		System.out.println(winnerText);
		System.out.println(Util.PLAYER1 + ": " + score1);
		System.out.println(Util.PLAYER2 + ": " + score2);
	}

	public char[][] getBoard() {
		return boardMat.getBoard();
	}

}
