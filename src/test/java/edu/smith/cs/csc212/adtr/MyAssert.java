package edu.smith.cs.csc212.adtr;

import org.junit.Assert;

import edu.smith.cs.csc212.adtr.real.JavaList;

public class MyAssert extends Assert{

	/**
	 * Asserts that two ListADT are equal.
	 * @param expected - expected ListADT value
	 * @param actual - actual ListADT value
	 */
	public static <T> void assertListUnorderlyEquals(ListADT<T> expected, ListADT<T> actual) {
		// Test null
		if (failNullCases(expected, actual)) return;
	
		// Must have equal size
		if (actual.size() != expected.size()) {
			failFormatted(expected, actual);
			return;
		}
		
		// List to loop over and remove
		ListADT<T> newActual = new JavaList<T>(actual.toJava());
		
		for(T expectedS : expected) {
			// Test is default to be failed for every value before comparing
			boolean failTest = true;
			// Loop through second list and compare value, remove value (not from original list) once we found a same value.
			for (int i=newActual.size()-1; i>=0; i--) {
				if (expectedS.equals(newActual.getIndex(i))) {
					failTest = false;
					newActual.removeIndex(i);
					break;
				}
			}
			
			// If the loop reach this points and failTest is still true, the whole thing fail.
			if (failTest) {
				failFormatted(expected, actual);
				return;
			}
		}
	}
	

	/**
	 * Asserts that two int (or Integer) are equal.
	 * @param expected - expected int (or Integer) value
	 * @param actual - actual int (or Integer) value
	 */
	public static void assertIntEquals(int expected, int actual) {
		assertEquals(expected, actual);
	}
	
	/**
	 * Asserts that two SetADT are equals
	 * @param expected - expected SetADT value
	 * @param actual - actual SetADT value
	 */
	public static <T> void assertSetEquals(SetADT<T> expected, SetADT<T> actual) {
		// Test null
		if (failNullCases(expected, actual)) return;
		
		// Test size
		if (expected.size() != actual.size()) {
			failFormatted(expected, actual);
			return;
		}
		
		for (T value : expected.toList()) {
			if (!actual.contains(value)) {
				failFormatted(expected, actual);
				return;
			}
		}
	}
	
	/**
	 * Assert that two MapADT are equals
	 * @param expected - expected MapADT value
	 * @param actual - actual MapADT value
	 */
	public static <K, V> void assertMapEquals(MapADT<K, V> expected, MapADT<K,V> actual) {
		// Test null
		if (failNullCases(expected, actual)) return;
				
		// Test size
		if (expected.size() != actual.size()) {
			failFormatted(expected, actual);
			return;
		}

		ListADT<K> keys = expected.getKeys();
		for (K key : keys) {
			if (expected.get(key) != actual.get(key)) {
				failFormatted(expected, actual);
				return;
			}
		}
	}
	
	/**
	 * Test null cases and throw errors if null cases doesn't pass.
	 * @param expected - expected Object value
	 * @param actual - actual Object value
	 * @return true if expected = actual = null
	 */
	private static boolean failNullCases(Object expected, Object actual) {
		if ((expected == null && actual != null) || (actual == null && expected != null)) {
			failFormatted(expected, actual);
			return true;
		}
		return false;
	}
	
	/**
	 * Give fail message in formatted way.
	 * @param expected - expected Object value
	 * @param actual - actual Object value
	 */
	private static void failFormatted (Object expected, Object actual) {
		String expectedString = expected == null ? "null" : expected.toString();
		String actualString = actual == null ? "null" : actual.toString();
		fail("expected:<" + expectedString + "> but was:<" + actualString + ">");
	}
}
