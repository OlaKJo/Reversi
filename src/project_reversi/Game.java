package project_reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Game {
	
	private List<Tuple> validMoves;
	private GameMatrix boardMat;
	
	public Game(){
		boardMat = new GameMatrix();
		validMoves = new ArrayList<Tuple>();
		initBoard();
	}
	
	private void initBoard() {
		boardMat.setCoord(4, 4, Util.PLAYER1);
		boardMat.setCoord(5, 5, Util.PLAYER1);
		boardMat.setCoord(4, 5, Util.PLAYER2);
		boardMat.setCoord(5, 4, Util.PLAYER2);
		updateValid(Util.PLAYER1);
	}

	public Game(char[][] boardMat){
		
	}
	
	public boolean makeMove(int x, int y, char player){
		if(validMoves.contains(new Tuple(x,y))) {
			boardMat.setCoord(x, y, player);
			flipMarkers(x, y, player);
			updateValid(Util.inversePlayer(player));			
			
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

	private void updateValid(char player) {
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

	public void printBoard() {
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
	
	public List<Tuple> getValidMoves() {
		return new ArrayList<Tuple>(validMoves);
	}

}
