package edu.smith.cs.csc212.adtr;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.smith.cs.csc212.adtr.real.JavaMap;

public class JavaMapTest {
	
	private <KeyType, ValueType> MapADT<KeyType, ValueType> makeEmptyMap(){
		return new JavaMap<>();
	}
	
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
		assertEquals(empty.size(), 0);
	}
	
	public void testPut() {
		MapADT<String, Integer> data = makeEmptyMap();
		assertEquals(data.size(), 0);
		data.put("zero", 0);
		assertEquals(data.size(), 1);
		assertEquals(data.get("zero"), 0);
		
		data.put("")
	}
}
