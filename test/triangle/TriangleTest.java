package triangle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TriangleTest {
	
	@Test
	public void aTriangleDoesNotHaveNegativeSides() {
		assertEquals(Triangle.ERROR, Triangle.typeOfTriangle(-1, 24, 23));
		assertEquals(Triangle.ERROR, Triangle.typeOfTriangle(12, -4, 22));
		assertEquals(Triangle.ERROR, Triangle.typeOfTriangle(12, 32, -3));
		assertEquals(Triangle.ERROR, Triangle.typeOfTriangle(12, -2, -3));
	}
	
	@Test
	public void aTriangleThatIsEquilateral() {
		assertEquals(Triangle.EQUILATERAL, Triangle.typeOfTriangle(2, 2, 2));
		assertEquals(Triangle.EQUILATERAL, Triangle.typeOfTriangle(10, 10, 10));
		assertEquals(Triangle.EQUILATERAL, Triangle.typeOfTriangle(1, 1, 1));
	}
	
	@Test
	public void aTriangleThatIsIsosceles() {
		assertEquals(Triangle.ISOSCELES, Triangle.typeOfTriangle(12, 12, 3));
		assertEquals(Triangle.ISOSCELES, Triangle.typeOfTriangle(3, 9, 3));
		assertEquals(Triangle.ISOSCELES, Triangle.typeOfTriangle(3, 8, 8));
	}
	
	@Test
	public void aTriangleThatIsScalene() {
		assertEquals(Triangle.SCALENE, Triangle.typeOfTriangle(12, 13, 14));
		assertEquals(Triangle.SCALENE, Triangle.typeOfTriangle(3, 4, 6));
		assertEquals(Triangle.SCALENE, Triangle.typeOfTriangle(20, 30, 19));
		assertEquals(Triangle.ERROR, Triangle.typeOfTriangle(1, 2, 3));
	}

}
