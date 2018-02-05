package project_reversi;

public class GameMatrix {
	private char[][] theMatrix;
	
	public GameMatrix() {
		theMatrix = new char[8][8];
		initMatrix();
	}
	
	public GameMatrix(char[][] mat) {
		theMatrix = mat;
	}

	private void initMatrix() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				theMatrix[i][j] = '.';
			}
		}
	}
	
	public void setCoord(int x, int y, char val)  {
		try {
			theMatrix[x-1][y-1] = val;
		} catch(Exception e) {
			System.out.println("Error in setCoord!");
		}
	}
	
	public char getCoord(int x, int y) {
		try {
			return theMatrix[x-1][y-1];
		} catch(Exception e) {
			System.out.println("Error in getCoord!");
			return 0;
		}
	}
	
	public char[][] getBoard() {
		char[][] copyBoard = new char[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				copyBoard[i][j] = theMatrix[i][j];
			}
		}
		return copyBoard;
	}
}
