package edu.smith.cs.csc212.adtr;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import edu.smith.cs.csc212.adtr.real.JavaList;
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
		MyAssert.assertIntEquals(5, data.get("five"));
		
		data.put("zero", 10);
		assertEquals(5, data.size());
		MyAssert.assertIntEquals(10, data.get("zero"));
	}
	
	@Test
	public void testGet() {
		MapADT<String, Integer> data = makeFullMap();
		MyAssert.assertIntEquals(2, data.get("two"));
		MyAssert.assertIntEquals(0, data.get("zero"));
		MyAssert.assertIntEquals(3, data.get("three"));
		MyAssert.assertIntEquals(1, data.get("one"));
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
		MyAssert.assertIntEquals(1, data.remove("one"));
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
		MyAssert.assertListUnorderlyEquals(new JavaList<String>(Arrays.asList("zero", "one", "two", "three")), data.getKeys());
		assertEquals(4, data.getKeys().size());
	
		MapADT<String, Integer> empty = makeEmptyMap();
		assertTrue(empty.getKeys().isEmpty());
		assertEquals(0, empty.getKeys().size());
		
		empty.put("pi", 314);
		assertEquals(1, empty.getKeys().size());
		MyAssert.assertListUnorderlyEquals(new JavaList<String>(Arrays.asList("pi")), empty.getKeys());
	}
	
	@Test
	public void testGetEntries() {
		MapADT<String, Integer> data = makeFullMap();
		assertEquals(4, data.getEntries().size());
		MyAssert.assertListUnorderlyEquals(data.getEntries(), new JavaList<Pair<String, Integer>>(Arrays.asList(new Pair<String, Integer>("zero", 0),
														  														new Pair<String, Integer>("one", 1),
														  														new Pair<String, Integer>("two", 2),
														  														new Pair<String, Integer>("three", 3))));
		
		MapADT<String, Integer> empty = makeEmptyMap();
		assertEquals(0, empty.getEntries().size());
		assertTrue(empty.getEntries().isEmpty());
		
		empty.put("pi", 314);
		assertEquals(1, empty.getKeys().size());
		MyAssert.assertListUnorderlyEquals(new JavaList<Pair<String, Integer>>(Arrays.asList(new Pair<String, Integer>("pi", 314))), null);
	}
	
	@Test
	public void testToJava() {
		MapADT<String, Integer> data = makeFullMap();
		assertEquals(data.toJava(), Map.of("zero", 0, "one", 1, "two", 2, "three", 3));
		
		MapADT<String, Integer> empty = makeEmptyMap();
		assertEquals(empty.toJava(), Map.of());
		
		empty.put("pi", 314);
		assertEquals(empty.toJava(), Map.of("pi", 314));
	}
}
