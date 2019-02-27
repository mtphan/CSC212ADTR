package edu.smith.cs.csc212.adtr;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import edu.smith.cs.csc212.adtr.real.JavaList;
import edu.smith.cs.csc212.adtr.real.JavaSet;

public class JavaSetTest {
	
	/**
	 * Make a new empty set.
	 * @return an empty set to be tested.
	 */
	private <T> SetADT<T> makeEmptySet() {
		return new JavaSet<>();
	}
	
	/**
	 * Helper method to make a full set.
	 * @return [a, b, c, d].
	 */
	private SetADT<String> makeFullSet() {
		SetADT<String> data = makeEmptySet();
		data.insert("a");
		data.insert("b");
		data.insert("c");
		data.insert("d");
		return data;
	}
	
	/**
	 * Asserts that two ListADT are equal.
	 * @param expected - expected ListADT value
	 * @param actual - actual ListADT value
	 */
	private static void assertUnorderedListEquals(ListADT<String> expected, ListADT<String> actual) {
		List<String> newExpected = expected.toJava();
		List<String> newActual = actual.toJava();
		
		Collections.sort(newExpected);
		Collections.sort(newActual);
		
		assertEquals(newExpected, newActual);
	}
	
	@Test
	public void testEmpty() {
		SetADT<String> empty = makeEmptySet();
		assertEquals(0, empty.size());
	}
	
	@Test
	public void testSize() {
		SetADT<String> data = makeFullSet();
		assertEquals(4, data.size());
	}
	
	@Test
	public void testInsert() {
		SetADT<String> data = makeEmptySet();
		data.insert("apple");
		assertEquals(1, data.size());
		assertTrue(data.contains("apple"));
		data.insert("banana");
		assertEquals(2, data.size());
		assertTrue(data.contains("banana"));
		data.insert("apple");
		assertEquals(2, data.size());
		assertTrue(data.contains("apple"));
		data.insert("banana");
		assertEquals(2, data.size());
		assertTrue(data.contains("banana"));
	}
	
	@Test
	public void testContains() {
		SetADT<String> data = makeFullSet();
		assertTrue(data.contains("a"));
		assertTrue(data.contains("b"));
		assertTrue(data.contains("c"));
		assertTrue(data.contains("d"));
	}
	
	@Test
	public void testRemove() {
		SetADT<String> data = makeFullSet();
		data.remove("a");
		assertEquals(3, data.size());
		assertFalse(data.contains("a"));
		data.remove("b");
		assertEquals(2, data.size());
		assertFalse(data.contains("b"));
	}
	
	@Test
	public void toList() {
		SetADT<String> data = makeFullSet();
		assertUnorderedListEquals(data.toList(), new JavaList<String>(Arrays.asList("a", "b", "d", "c")));
		SetADT<String> empty = makeEmptySet();
		assertUnorderedListEquals(empty.toList(), new JavaList<String>(Arrays.asList()));
	}
	
	@Test
	public void toJava() {
		SetADT<String> data = makeFullSet();
		assertEquals(data.toJava(), Set.of("a", "b", "d", "c"));
		SetADT<String> empty = makeEmptySet();
		assertEquals(empty.toJava(), Set.of());
	}
}
