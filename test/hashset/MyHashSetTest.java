package hashset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class MyHashSetTest {
	
	private final String aSimpleString = "just a simple string";
	private final String anotherString = "just another string";

	private Set<String> myHashSet;
	
	@Before
	public void createMyHashSet() {
		myHashSet = new MyHashSet<String>();
		
		myHashSet.add(aSimpleString);
		myHashSet.add(anotherString);
	}

	@Test
	public void canAddElementOnlyIfNotExisting() {
		String nonExistingString = "non existing string";
		assertTrue(myHashSet.add(nonExistingString));
		
		// when it's already there i won't be able to insert it
		assertFalse(myHashSet.add(nonExistingString));
	}
	
	@Test
	public void canAddCollectionToSet() {
		List<String> aListCollection = new ArrayList<String>();
		aListCollection.add(aSimpleString);
		String stringFromListCollection = "string from a list collection";
		aListCollection.add(stringFromListCollection);
		
		Set<String> aSetCollection = new HashSet<String>();
		aSetCollection.add(anotherString);
		String stringFromSetCollection = "string from a set collection";
		aSetCollection.add(stringFromSetCollection);
		
		int myOriginalSetSize = myHashSet.size();
		
		assertTrue(myHashSet.addAll(aListCollection));
		assertEquals(myOriginalSetSize + 1, myHashSet.size());
		
		assertTrue(myHashSet.addAll(aSetCollection));
		assertEquals(myOriginalSetSize + 2, myHashSet.size());
	}
	
	@Test
	public void isAbleToClearSet() {
		assertEquals(2, myHashSet.size());
		myHashSet.clear();
		assertEquals(0, myHashSet.size());
	}
	
	@Test
	public void checkIfItContainsElement() {
		
		assertTrue(myHashSet.contains(aSimpleString));
		assertTrue(myHashSet.contains(anotherString));
		assertFalse(myHashSet.contains("does not exist"));
		assertFalse(myHashSet.contains(null));
	}
	
	@Test
	public void checkIfItContainsACollection() {
		
		List<String> aListCollection = new ArrayList<String>();
		aListCollection.add(aSimpleString);
		aListCollection.add(anotherString);
		
		Set<String> aSetCollection = new HashSet<String>();
		aSetCollection.add(aSimpleString);
		aSetCollection.add(anotherString);
		
		assertTrue(myHashSet.containsAll(aListCollection));
		assertTrue(myHashSet.containsAll(aSetCollection));
		aListCollection.add("adding another element to list");
		aSetCollection.add("adding another element to list");
		assertFalse(myHashSet.containsAll(aListCollection));
		assertFalse(myHashSet.containsAll(aSetCollection));
	}
	
	@Test
	public void checkIfItEmpty() {
		
		assertFalse(myHashSet.isEmpty());
		myHashSet.clear();
		assertTrue(myHashSet.isEmpty());
	}
	
	@Test
	public void canProvideAnIterator() {
		Iterator<String> stringIterator = myHashSet.iterator();
		assertNotNull(stringIterator);
	}
	
	@Test
	public void canRemoveObjectFromSet() {
		
		int setSize = myHashSet.size();
		assertTrue(myHashSet.contains(aSimpleString));
		myHashSet.remove(aSimpleString);
		assertFalse(myHashSet.contains(aSimpleString));
		assertEquals(setSize - 1, myHashSet.size());
	}
	
	@Test
	public void canRemoveCollectionFromSet() {

		List<String> aListCollection = new ArrayList<String>();
		aListCollection.add(aSimpleString);
		aListCollection.add(anotherString);
		
		Set<String> aSetCollection = new HashSet<String>();
		aSetCollection.add(aSimpleString);
		aSetCollection.add(anotherString);

		assertEquals(2, myHashSet.size());
		
		// takes out the two existing elements -> returns true because the set changed
		assertTrue(myHashSet.removeAll(aListCollection));
		assertEquals(0, myHashSet.size());
		
		// there's nothing else to take -> returns false
		assertFalse(myHashSet.removeAll(aSetCollection));
		assertEquals(0, myHashSet.size());
		
	}
	
	@Test
	public void canRetainCollectionInSet() {
		List<String> aListCollection = new ArrayList<String>();
		aListCollection.add(aSimpleString);
		aListCollection.add(anotherString);
		
		Set<String> aSetCollection = new HashSet<String>();
		aSetCollection.add(aSimpleString);

		assertEquals(2, myHashSet.size());
		// retain the two existing elements -> returns false because the set is unchanged
		assertFalse(myHashSet.retainAll(aListCollection));
		assertEquals(2, myHashSet.size());
		
		// retain one element -> returns true because the set is changed by removing one element
		assertTrue(myHashSet.retainAll(aSetCollection));
		assertEquals(1, myHashSet.size());

		assertTrue(myHashSet.contains(aSimpleString));
		assertFalse(myHashSet.contains(anotherString));
		
	}
	
	
	
	
	@Test
	public void canGenerateArrayVersionOfSet() {
		Object[] arrayVersion = myHashSet.toArray();
		
		assertArrayEqualsWithoutOrder(new Object[] {aSimpleString, anotherString}, arrayVersion);
		
	}
	
	@Test
	public void canGenerateArrayVersionOfSetPassingArrayAsParameter() {
		
		Object[] passingArray = new String[2];
		
		Object[] arrayVersion = myHashSet.toArray(passingArray);
		
		assertArrayEqualsWithoutOrder(new Object[] {aSimpleString, anotherString}, arrayVersion);
	}

	private void assertArrayEqualsWithoutOrder(Object[] strings,
			Object[] arrayVersion) {
		if (strings.length != arrayVersion.length) {
			assertTrue(false);
			return;
		}
		for (Object arrayVersionObject : arrayVersion) {
			boolean found = false;
			for (Object stringObject : strings) {
				if (stringObject.equals(arrayVersionObject)) {
					found = true;
					break;
				}
			}
			if (!found) {
				assertTrue(false);
			}
		}
		assertTrue(true);
	}

}
