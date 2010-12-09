package bagcollection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class HashBagTest {
	

	
	
	private String aStringElement = "string element";
	private String anotherStringElement = "another string element";
	private Bag<String> hashBag;

	@Before
	public void createExampleBag() {
		hashBag = new HashBag<String>();
	}
	
	@Test
	public void countAmountOfACertainElementInBag() {
		
		assertEquals(0, hashBag.count(aStringElement));
		hashBag.add(aStringElement);
		assertEquals(1, hashBag.count(aStringElement));
	}
	
	@Test
	public void addAnElementToBag() {

		assertEquals(0, hashBag.size());
		assertEquals(0, hashBag.count(aStringElement));
		hashBag.add(aStringElement);
		assertEquals(1, hashBag.count(aStringElement));
		assertEquals(1, hashBag.size());
	}
	
	@Test
	public void addACollectionToBag() {
		
		hashBag.add(aStringElement);
		
		List<String> aListCollection = new ArrayList<String>();
		aListCollection.add(aStringElement);
		aListCollection.add(anotherStringElement);
		
		hashBag.addAll(aListCollection);
		
		assertEquals(2, hashBag.count(aStringElement));
		assertEquals(1, hashBag.count(anotherStringElement));
		assertEquals(3, hashBag.size());
		
	}

	
	@Test
	public void canClearBag() {
		
		hashBag.add(aStringElement);
		assertEquals(1, hashBag.size());
		hashBag.clear();
		assertEquals(0, hashBag.size());
	}
	
	@Test
	public void checkIfItContainsAnElementInTheBag() {

		assertFalse(hashBag.contains(aStringElement));
		
		hashBag.add(aStringElement);
		
		assertTrue(hashBag.contains(aStringElement));
	}
	
	
	@Test
	public void checkIfItBagContainsAllElementsFromACollection() {
		
		hashBag.add(aStringElement);
		hashBag.add(anotherStringElement);
		
		List<String> aStringList = new ArrayList<String>();
		aStringList.add(aStringElement);
		aStringList.add(anotherStringElement);
		
		assertTrue(hashBag.containsAll(aStringList));

		aStringList.add("non existing string in bag");
		assertFalse(hashBag.containsAll(aStringList));
	}
	
	@Test
	public void checkIfBagIsEmpty() {
		
		assertTrue(hashBag.isEmpty());
		
		hashBag.add(aStringElement);
		
		assertFalse(hashBag.isEmpty());
		
	}
	
	@Test
	public void canProvideAnIterator() {
		// TODO
	}
	
	@Test
	public void canRemoveElementFromBag() {
		hashBag.add(aStringElement);
		hashBag.add(aStringElement);
		hashBag.add(anotherStringElement);
		
		assertEquals(3, hashBag.size());
		assertEquals(2, hashBag.count(aStringElement));
		
		assertTrue(hashBag.remove(aStringElement));
		
		assertEquals(2, hashBag.size());
		assertEquals(1, hashBag.count(aStringElement));
		
	}
	
	@Test
	public void cannotRemoveUnexistingElement() {
		assertFalse(hashBag.remove(anotherStringElement));
	}
	
	@Test
	public void canRemoveACollectionFromTheBag() {
		hashBag.add(aStringElement);
		hashBag.add(aStringElement);
		hashBag.add(anotherStringElement);
		
		assertEquals(3, hashBag.size());
		assertEquals(2, hashBag.count(aStringElement));
		
		List<String> aStringList = new ArrayList<String>();
		
		aStringList.add(aStringElement);
		
		hashBag.removeAll(aStringList);
		
		assertEquals(1, hashBag.size());
		assertEquals(0, hashBag.count(aStringElement));
		assertEquals(1, hashBag.count(anotherStringElement));
		
		aStringList.add(anotherStringElement);
		
		hashBag.removeAll(aStringList);
		
		assertEquals(0, hashBag.size());
	}
	
	
	@Test
	public void canRetainCollectionElementsInBag() {

		hashBag.add(aStringElement);
		hashBag.add(aStringElement);
		hashBag.add(anotherStringElement);
		
		assertEquals(3, hashBag.size());
		assertEquals(2, hashBag.count(aStringElement));
		assertEquals(1, hashBag.count(anotherStringElement));
		
		List<String> aStringList = new ArrayList<String>();
		
		aStringList.add(aStringElement);
		
		assertTrue(hashBag.retainAll(aStringList));
		
		assertEquals(2, hashBag.size());
		assertEquals(2, hashBag.count(aStringElement));
		assertEquals(0, hashBag.count(anotherStringElement));
	}

}
