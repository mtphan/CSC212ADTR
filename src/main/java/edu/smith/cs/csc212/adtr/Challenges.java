package edu.smith.cs.csc212.adtr;

import edu.smith.cs.csc212.adtr.real.JavaMap;
import edu.smith.cs.csc212.adtr.real.JavaSet;

public class Challenges {

	/**
	 * Create a union of two sets (the set of elements that either contains).
	 * @param left - set 1
	 * @param right - set 2
	 * @return the union of left and right
	 */
	public static SetADT<Integer> union(SetADT<Integer> left, SetADT<Integer> right) {
		// Add all value in left to output
		SetADT<Integer> output = new JavaSet<>(left.toJava());
		
		// Make an iterable list from right
		ListADT<Integer> iterableRight = right.toList();
		
		// Loop though right and remove duplicate element or add non-duplicate ones.
		for (int value : iterableRight) {
			if (output.contains(value)) {
				output.remove(value);
			} else {
				output.insert(value);
			}
		}
		return output;
	}
	
	/**
	 * Create an intersection of two sets (the set of elements that both contain).
	 * @param left - set 1
	 * @param right - set 2
	 * @return the intersection of left and right
	 */
	public static SetADT<Integer> intersection(SetADT<Integer> left, SetADT<Integer> right) {
		SetADT<Integer> output = new JavaSet<>();
		
		// Make an iterable list from right
		ListADT<Integer> iterableRight = right.toList();
		
		// Loop though right and insert duplicate ones.
		for (int value : iterableRight) {
			if (left.contains(value)) {
				output.insert(value);
			}
		}
		return output;
	}
	
	// Count the words in the input list and put them in the map.
	public static MapADT<String, Integer> wordCount(ListADT<String> words) {
		MapADT<String, Integer> output = new JavaMap<>();
		
		for (String word : words) {
			Integer num = output.get(word);
			// Word never counted:
			if (num == null) {
				output.put(word, 1);
			} else {
				output.put(word, ++num);
			}
		}
		
		return output;
	}
}
