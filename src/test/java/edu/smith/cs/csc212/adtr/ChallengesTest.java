package edu.smith.cs.csc212.adtr;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import edu.smith.cs.csc212.adtr.real.JavaList;
import edu.smith.cs.csc212.adtr.real.JavaMap;
import edu.smith.cs.csc212.adtr.real.JavaSet;

public class ChallengesTest {
	
	/**
	 * Split string into words, not counting punctuation. I stole the regular expression and most of the code from your WordSplitter class in SpookyMansion.
	 * @param s - string to split into words
	 * @return a ListADT of words
	 */
	private ListADT<String> wordSplitter(String s) {
		ListADT<String> words = new JavaList<>();
		for (String token : s.split("(\\s+|\\p{Punct})")) {
			token = token.trim();
			if (token.isEmpty()) {
				continue;
			}
			
			words.addBack(token);	
		}
		return words;
	}
	
	@Test
	public void testSetUnion() {
		SetADT<Integer> left = new JavaSet<>(Set.of(2, 3, 5, 8, 10, 13)); 
		SetADT<Integer> right = new JavaSet<>(Set.of(2, 3, 5, 8, 10, 13));
		MyAssert.assertSetEquals(Challenges.union(left, right), new JavaSet<Integer>());
		
		left = new JavaSet<>(Set.of(2, 3, 5, 8, 10, 13)); 
		right = new JavaSet<>(Set.of(2, 3, 10, 13, 15));
		MyAssert.assertSetEquals(Challenges.union(left, right), new JavaSet<Integer>(Set.of(5, 8, 15)));
		
		left = new JavaSet<>(Set.of(0, 2, 4, 6, 8)); 
		right = new JavaSet<>(Set.of(1, 3, 5, 7, 9));
		MyAssert.assertSetEquals(Challenges.union(left, right), new JavaSet<Integer>(Set.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
		
		left = new JavaSet<>(Set.of()); 
		right = new JavaSet<>(Set.of());
		MyAssert.assertSetEquals(Challenges.union(left, right), new JavaSet<Integer>(Set.of()));
	}
	
	@Test
	public void testSetIntersect() {
		SetADT<Integer> left = new JavaSet<>(Set.of(2, 3, 5, 8, 10, 13)); 
		SetADT<Integer> right = new JavaSet<>(Set.of(2, 3, 5, 8, 10, 13));
		MyAssert.assertSetEquals(Challenges.intersection(left, right), new JavaSet<Integer>(Set.of(2, 3, 5, 8, 10, 13)));
		
		left = new JavaSet<>(Set.of(2, 3, 5, 8, 10, 13)); 
		right = new JavaSet<>(Set.of(2, 3, 10, 13, 15));
		MyAssert.assertSetEquals(Challenges.intersection(left, right), new JavaSet<Integer>(Set.of(2, 3, 10, 13)));
		
		left = new JavaSet<>(Set.of(0, 2, 4, 6, 8)); 
		right = new JavaSet<>(Set.of(1, 3, 5, 7, 9));
		MyAssert.assertSetEquals(Challenges.intersection(left, right), new JavaSet<Integer>(Set.of()));
		
		left = new JavaSet<>(Set.of(1)); 
		right = new JavaSet<>(Set.of(2));
		MyAssert.assertSetEquals(Challenges.intersection(left, right), new JavaSet<Integer>(Set.of()));
	}
	
	@Test
	public void testWordCount() {
		String paragraph = "A paragraph (from the Ancient Greek παράγραφος paragraphos, \"to write beside\" or \"written beside\") "
				+ "is a self-contained unit of a discourse in writing dealing with a particular point or idea. "
				+ "A paragraph consists of one or more sentences. "
				+ "Though not required by the syntax of any language, "
				+ "paragraphs are usually an expected part of formal writing, used to organize longer prose."; //paragraph from wikipedia.org/wiki/Paragraph
		ListADT<String> words = wordSplitter(paragraph.toLowerCase());
		MyAssert.assertMapEquals(Challenges.wordCount(words), new JavaMap<String, Integer>(Map.ofEntries(
				Map.entry("language", 1),
				Map.entry("used", 1),
				Map.entry("paragraphs", 1),
				Map.entry("required", 1),
				Map.entry("from", 1),
				Map.entry("beside", 2),
				Map.entry("written", 1),
				Map.entry("usually", 1),
				Map.entry("write", 1),
				Map.entry("though", 1),
				Map.entry("dealing", 1),
				Map.entry("in", 1),
				Map.entry("expected", 1),
				Map.entry("is", 1),
				Map.entry("particular", 1),
				Map.entry("an", 1),
				Map.entry("formal", 1),
				Map.entry("paragraphos", 1),
				Map.entry("unit", 1),
				Map.entry("longer", 1),
				Map.entry("a", 5),
				Map.entry("paragraph", 2),
				Map.entry("idea", 1),
				Map.entry("sentences", 1),
				Map.entry("part", 1),
				Map.entry("point", 1),
				Map.entry("παράγραφος", 1),
				Map.entry("not", 1),
				Map.entry("are", 1),
				Map.entry("organize", 1),
				Map.entry("ancient", 1),
				Map.entry("of", 4),
				Map.entry("writing", 2),
				Map.entry("by", 1),
				Map.entry("prose", 1),
				Map.entry("or", 3),
				Map.entry("more", 1),
				Map.entry("one", 1),
				Map.entry("any", 1),
				Map.entry("discourse", 1),
				Map.entry("the", 2),
				Map.entry("with", 1),
				Map.entry("contained", 1),
				Map.entry("self", 1),
				Map.entry("consists", 1),
				Map.entry("syntax", 1),
				Map.entry("to", 2),
				Map.entry("greek", 1))));
		
		paragraph = "Hallo!!";
		words = wordSplitter(paragraph.toLowerCase());
		MyAssert.assertMapEquals(Challenges.wordCount(words), new JavaMap<String, Integer>(Map.of("hallo", 1)));
		
		paragraph = "";
		words = wordSplitter(paragraph.toLowerCase());
		MyAssert.assertMapEquals(Challenges.wordCount(words), new JavaMap<String, Integer>(Map.of()));
		assertEquals(0, Challenges.wordCount(words).size());
	}
}
