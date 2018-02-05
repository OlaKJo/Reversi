package project_reversi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

class BoardTest {

	private Game testGame;
	
	@Test
	void testPrintUpdateValid() {
		Game b = new Game();
		b.printBoard();
	}
	
	@Test
	void testInitialUpdateValid() {
		Game b = new Game();
		List<Tuple> testList = new ArrayList<Tuple>();
		testList.add(new Tuple(5,3));
		testList.add(new Tuple(6,4));
		testList.add(new Tuple(3,5));
		testList.add(new Tuple(4,6));
		
		System.out.println(b.getValidMoves());
		System.out.println(testList);
		
		for(Tuple t : testList) {
			assert(b.getValidMoves().contains(t));
		}		
		assertEquals(b.getValidMoves().size(), testList.size());
	}
	

	@Test
	void testListContains() {
		Game b = new Game();
				
		assert(b.getValidMoves().contains(new Tuple(3,5)));
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
	void testFullBoard() {
		setUpFullBoard();
		GameRunner.runGame(testGame, Util.PLAYER1);
	}
	
	@Test
	 void testPartialBoard() {
		setUpBoard1();
		GameRunner.runGame(testGame, Util.PLAYER2);

	}
	
	@Test
	 void testLockedBoard() {
		setUpLockedBoard();
		GameRunner.runGame(testGame, Util.PLAYER2);

	}
}
