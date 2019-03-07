package edu.smith.cs.csc212.adtr;

import static org.junit.Assert.*;

import java.util.Arrays;
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
	
	@Test
	public void testEmpty() {
		assertEquals(0, makeEmptySet().size());
	}
	
	@Test
	public void testSize() {
		assertEquals(4, makeFullSet().size());
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
		assertEquals(4, data.toList().size());
		MyAssert.assertListUnorderlyEquals(data.toList(), new JavaList<String>(Arrays.asList("a", "c", "b", "d")));
		
		SetADT<String> empty = makeEmptySet();
		assertEquals(0, empty.toList().size());
		MyAssert.assertListUnorderlyEquals(empty.toList(), new JavaList<String>(Arrays.asList()));
		assertTrue(empty.toList().isEmpty());
		
		SetADT<String> single = makeEmptySet();
		single.insert("banana");
		assertEquals(1, single.toList().size());
		MyAssert.assertListUnorderlyEquals(single.toList(), new JavaList<String>(Arrays.asList("banana")));
	}
	
	@Test
	public void toJava() {
		SetADT<String> data = makeFullSet();
		assertEquals(4, data.toJava().size());
		assertEquals(data.toJava(), Set.of("a", "b", "d", "c"));
		
		SetADT<String> empty = makeEmptySet();
		assertEquals(0, empty.toJava().size());
		assertEquals(empty.toJava(), Set.of());
		assertTrue(empty.toJava().isEmpty());
		
		SetADT<String> single = makeEmptySet();
		single.insert("banana");
		assertEquals(1, single.toJava().size());
		assertEquals(single.toJava(), Set.of("banana"));
	}
}
