package testReversi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import project_reversi.Game;
import project_reversi.GameRunner;
import project_reversi.Tuple;
import project_reversi.Util;

public class BoardTest {

	private Game testGame;
	
	@Test
	public void testPrintUpdateValid() {
		Game b = new Game();
		b.printBoard(Util.PLAYER1);
	}
	
	@Test
	public void testInitialUpdateValid() {
		Game b = new Game();
		List<Tuple> testList = new ArrayList<Tuple>();
		testList.add(new Tuple(5,3));
		testList.add(new Tuple(6,4));
		testList.add(new Tuple(3,5));
		testList.add(new Tuple(4,6));
		
		System.out.println(b.getValidMoves(Util.PLAYER1));
		System.out.println(testList);
		
		for(Tuple t : testList) {
			assert(b.getValidMoves(Util.PLAYER1).contains(t));
		}		
		assertEquals(b.getValidMoves(Util.PLAYER1).size(), testList.size());
	}
	

	@Test
	public void testListContains() {
		Game b = new Game();
				
		assert(b.getValidMoves(Util.PLAYER1).contains(new Tuple(3,5)));
	}
	
	private void setUpFullBoard() {
		char[][] mat = new char[][] {	{'X' ,'X', 'X', 'X', 'X' ,'X', 'X', 'X'},
										{'X' ,'X', 'X', 'X', 'X' ,'X', 'X', 'X'}, 
										{'X' ,'X', 'X', 'X', 'X' ,'X', 'X', 'X'},		 
										{'X' ,'X', 'X', 'X', 'X' ,'X', 'X', 'X'},
										{'X' ,'X', 'X', 'X', 'X' ,'X', 'X', 'X'},
										{'X' ,'X', 'X', 'X', 'X' ,'X', 'X', 'X'}, 
										{'X' ,'X', 'X', 'X', 'X' ,'X', 'X', 'O'},		 
										{'X' ,'X', 'X', 'X', 'X' ,'X', 'X', '.'}};
										
		testGame = new Game(mat, Util.PLAYER1);
	}
	
	private void setUpBoard1() {
		char[][] mat = new char[][] {	{'.' ,'.', '.', '.', '.' ,'.', '.', '.'},
										{'.' ,'.', '.', '.', '.' ,'.', '.', '.'}, 
										{'.' ,'.', '.', '.', '.' ,'.', '.', '.'},		 
										{'.' ,'.', '.', 'X', 'O' ,'.', '.', '.'},
										{'.' ,'.', 'X', 'X', 'X' ,'.', '.', '.'},
										{'.' ,'.', '.', '.', '.' ,'.', '.', '.'}, 
										{'.' ,'.', '.', '.', '.' ,'.', '.', '.'},		 
										{'.' ,'.', '.', '.', '.' ,'.', '.', '.'}};
										
		testGame = new Game(mat, Util.PLAYER2);
	}
	
	private void setUpLockedBoard() {
		char[][] mat = new char[][] {	{'.' ,'.', '.', '.', 'O' ,'.', '.', '.'},
										{'.' ,'.', '.', '.', 'O' ,'O', '.', '.'}, 
										{'O' ,'X', 'X', 'X', 'X' ,'X', '.', 'X'},		 
										{'.' ,'.', 'O', 'O', 'O' ,'O', '.', 'X'},
										{'.' ,'.', 'O', 'O', 'O' ,'.', '.', 'X'},
										{'.' ,'.', '.', '.', '.' ,'.', '.', '.'}, 
										{'.' ,'.', '.', '.', '.' ,'.', '.', '.'},		 
										{'.' ,'.', '.', '.', '.' ,'.', '.', '.'}};
										
		testGame = new Game(mat, Util.PLAYER2);
	}
	
	@Test
	public void testFullBoard() {
		setUpFullBoard();
		GameRunner.runGame(testGame, Util.PLAYER1);
	}
	
	@Test
	 public void testPartialBoard() {
		setUpBoard1();
		GameRunner.runGame(testGame, Util.PLAYER2);

	}
	
	@Test
	 public void testLockedBoard() {
		setUpLockedBoard();
		GameRunner.runGame(testGame, Util.PLAYER2);

	}
}
