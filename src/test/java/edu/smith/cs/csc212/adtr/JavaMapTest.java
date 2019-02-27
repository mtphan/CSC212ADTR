package edu.smith.cs.csc212.adtr;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;
import edu.smith.cs.csc212.adtr.real.JavaMap;

public class JavaMapTest {
	
	/**
	 * Make a new empty map.
	 * @return an empty map to be tested.
	 */
	private <KeyType, ValueType> MapADT<KeyType, ValueType> makeEmptyMap(){
		return new JavaMap<>();
	}
	
	/**
	 * Make a simple map.
	 * @return {"zero"=0, "one"=1, "two"=2, "three"=3}.
	 */
	private MapADT<String, Integer> makeFullMap(){
		MapADT<String, Integer> data = makeEmptyMap();
		data.put("zero", 0);
		data.put("one", 1);
		data.put("two", 2);
		data.put("three", 3);
		return data;
	}
	
	/**
	 * Asserts that two int (or Integer) are equal.
	 * @param expected - expected int (or Integer) value
	 * @param actual - actual int (or Integer) value
	 */
	private static void assertIntEquals(int expected, int actual) {
		assertEquals(expected, actual);
	}
	
	/**
	 * Asserts that two Pair<String, Integer> are equal.
	 * @param expected - expected Pair<String, Integer> instance
	 * @param actual - actual Pair<String, Integer> instance
	 */
	private static <K, V> void assertPairEquals(Pair<K, V> expected, Pair<K, V> actual) {
		// Test null
		if (expected == null && actual != null) {
			fail("expected:<null> but was:<key=" + actual.getKey() + ", value=" + actual.getValue());
			return;
		}
		if (actual == null && expected != null) {
			fail("expected:<key=" + expected.getKey() + ", value=" + expected.getValue() + "> but was:<null>");
			return;
		}
		// Not null
		if (expected.getKey() == actual.getKey() && expected.getValue() == actual.getValue()) {
			return;
		} else {
			fail("expected:<key=" + expected.getKey() + ", value=" + expected.getValue()
			+ "> but was:<key=" + actual.getKey() + ", value=" + actual.getValue() + ">");
		}
	}
	
	@Test
	public void testEmpty() {
		MapADT<String, Integer> empty = makeEmptyMap();
		assertEquals(0, empty.size());
	}
	
	@Test
	public void testSize() {
		MapADT<String, Integer> data = makeFullMap();
		assertEquals(4, data.size());
	}
	
	@Test
	public void testPut() {
		MapADT<String, Integer> data = makeFullMap();
		assertEquals(4, data.size());
		assertNull(data.get("five"));
		
		data.put("five", 5);
		assertEquals(5, data.size());
		assertIntEquals(5, data.get("five"));
		
		data.put("zero", 10);
		assertEquals(5, data.size());
		assertIntEquals(10, data.get("zero"));
	}
	
	@Test
	public void testGet() {
		MapADT<String, Integer> data = makeFullMap();
		assertIntEquals(2, data.get("two"));
		assertIntEquals(0, data.get("zero"));
		assertIntEquals(3, data.get("three"));
		assertIntEquals(1, data.get("one"));
	}
	
	@Test
	public void testGetNull() {
		MapADT<String, Integer> empty = makeEmptyMap();
		assertNull(empty.get("banana"));
		assertNull(empty.get("four"));
	}
	
	@Test
	public void testRemove() {
		MapADT<String, Integer> data = makeFullMap();
		assertIntEquals(1, data.remove("one"));
		assertNull(data.get("one"));
		assertEquals(3, data.size());
	}
	
	@Test
	public void testRemoveNull() {
		MapADT<String, Integer> empty = makeEmptyMap();
		assertNull(empty.remove("zero"));
		assertNull(empty.remove("one"));
	}
	
	@Test
	public void testGetKeys() {
		MapADT<String, Integer> data = makeFullMap();
		assertEquals(Arrays.asList("zero", "one", "two", "three"), data.getKeys().toJava());
	}
	
	@Test
	public void testGetKeysEmpty() {
		MapADT<String, Integer> empty = makeEmptyMap();
		assertTrue(empty.getKeys().isEmpty());
		assertEquals(0, empty.getKeys().size());
	}
	
	@Test
	public void testGetEntries() {
		MapADT<String, Integer> data = makeFullMap();
		assertEquals(4, data.getEntries().size());
		assertPairEquals(new Pair<String, Integer>("zero", 0), data.getEntries().getIndex(0));
		assertPairEquals(new Pair<String, Integer>("one", 1), data.getEntries().getIndex(1));
		assertPairEquals(new Pair<String, Integer>("two", 2), data.getEntries().getIndex(2));
		assertPairEquals(new Pair<String, Integer>("three", 3), data.getEntries().getIndex(3));
	}
	
	@Test
	public void testGetEntriesEmpty() {
		MapADT<String, Integer> empty = makeEmptyMap();
		assertEquals(0, empty.getEntries().size());
		assertTrue(empty.getEntries().isEmpty());
	}
	
	@Test
	public void testToJava() {
		MapADT<String, Integer> data = makeFullMap();
		assertEquals(data.toJava(), Map.of("zero", 0, "one", 1, "two", 2, "three", 3));
		MapADT<String, Integer> empty = makeEmptyMap();
		assertEquals(empty.toJava(), Map.of());
	}
}
