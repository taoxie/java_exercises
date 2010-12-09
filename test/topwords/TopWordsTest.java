package topwords;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class TopWordsTest {
	
	@Test
	public void findTopWordsInFile() throws Exception {
		
		String filename = "test/topwords/apache_license.txt";
		TopWords topWords = new TopWords(filename, 10);
		
		Map<String, Integer> mapOfTopWords = topWords.find();
		
		Map<String, Integer> expectedMap = new HashMap<String, Integer>();
		expectedMap.put("the", 81);
		expectedMap.put("or", 59);
		expectedMap.put("of", 58);
		expectedMap.put("and", 39);
		expectedMap.put("to", 35);
		expectedMap.put("any", 28);
		expectedMap.put("You", 23);
		expectedMap.put("Work", 22);
		expectedMap.put("that", 21);
		expectedMap.put("in", 20);
		
		assertTrue(expectedMap.keySet().containsAll(mapOfTopWords.keySet()));
		for (Iterator iterator = expectedMap.keySet().iterator(); iterator.hasNext();) {
			String word = (String) iterator.next();
			if (!expectedMap.get(word).equals(mapOfTopWords.get(word))) {
				fail();
			}
		}
	}


}
