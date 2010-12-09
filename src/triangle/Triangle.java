package triangle;

/**
 * Utility class that provides information about Triangle(s)
 * @author raul
 *
 */
public class Triangle {

	public static final int SCALENE = 1;
	public static final int ISOSCELES = 2;
	public static final int EQUILATERAL = 3;
	public static final int ERROR = 4;

	/*
	 * tells if it is (and its type) of triangle given the size of the sides
	 */
	public static int typeOfTriangle(int sideOne, int sideTwo, int sideThree) {
		int result = ERROR;
		if (sideOne <= 0 || sideTwo <= 0 || sideThree <= 0) {
			// it is not a triangle
			result = ERROR;
		} else if (sideOne == sideTwo && sideTwo == sideThree) {
			// it is an equilateral
			result = EQUILATERAL;
		} else if (sideOne == sideTwo || sideOne == sideThree || sideTwo == sideThree) {
			// it is an isosceles
			result = ISOSCELES;
		} else {
			// check if it is scalene
			if (sideOne + sideTwo > sideThree &&
				sideOne + sideThree > sideTwo &&
				sideTwo + sideThree > sideOne) {
				result = SCALENE;
			} else {
				// otherwise it's not a triangle
				result = ERROR;
			}
		}
		
		return result;
	}

}
