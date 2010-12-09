package topwords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Provides information about the most common words based on a filename
 * @author raul
 *
 */
public class TopWords {
	private String filename;
	private int numberOfTopWords;
	private Map<String, Integer> topWordsMap = new HashMap<String, Integer>();

	public TopWords(String filename, int numberOfTopWords){
		this.numberOfTopWords = numberOfTopWords;
		this.filename = filename;
	}

	public Map<String, Integer> find() throws Exception {
		
		final Scanner scanner = new Scanner((Readable) new BufferedReader(new FileReader(filename))); 
		while(scanner.hasNext()) {
			String word = getCleanWord(scanner);
			Integer current = (Integer) topWordsMap.get(word);
			if (current == null) {
				topWordsMap.put(word, 1);
			} else {
				topWordsMap.put(word, current + 1);
			}
		}
		
		WordCountComparator comparator = new WordCountComparator(topWordsMap);
		Map<String, Integer> sortedMap = new TreeMap<String, Integer>(comparator);
		
		sortedMap.putAll(topWordsMap);
		Object[] sortedArray = sortedMap.keySet().toArray();
		
		for(int i = numberOfTopWords; i < sortedArray.length; i++) {
			sortedMap.remove((String)sortedArray[i]);
		}
		
		return sortedMap;
	}

	public String getCleanWord(final Scanner scanner) {
		return scanner.next().replace('.', ' ').replace('"', ' ').trim();
	}
	
	class WordCountComparator implements Comparator<String> {

		private Map<String, Integer> map;

		public WordCountComparator(Map<String, Integer> map) {
			this.map = map;
		}
		
		@Override
		public int compare(String objectOne, String objectTwo) {
			Integer numberObjectOne = (Integer)map.get(objectOne);
			Integer numberObjectTwo = (Integer)map.get(objectTwo);
			
			if (numberObjectOne == null) {
				numberObjectOne = 0;
			}
			if (numberObjectTwo == null) {
				numberObjectTwo = 0;
			}
			
			return numberObjectTwo - numberObjectOne; 
		}
		
	}
	
	

}
