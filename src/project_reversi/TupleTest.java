package project_reversi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TupleTest {

	@Test
	void testEqual() {
		Tuple t1 = new Tuple(1,1);
		Tuple t2 = new Tuple(1,1);
		assertEquals(t1, t2);
		Tuple t3 = new Tuple(2,2);
		assertFalse(t1.equals(t3));
	}

}
