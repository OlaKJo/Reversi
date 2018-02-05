package project_reversi;


public class Util {

	public final static char PLAYER1 = 'X';
	public final static char PLAYER2 = 'O';
	public final static char EMPTY = '.';
	public final static char VALIDMOVE = '+';
	
	public static char inversePlayer(char p1) {
		if(p1 == PLAYER1) 
			return PLAYER2;
		return PLAYER1;
	}
}
