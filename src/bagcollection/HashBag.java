package bagcollection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * class that implements Bag interface trough HashMap
 * 
 * @author raul
 *
 * @param <E>
 */
public class HashBag<E> implements Bag<E> {

	private final Map<E, Integer> bagMap;
	int bagSize = 0;

	public HashBag() {
		bagMap = new HashMap<E, Integer>();
		bagSize = 0;
	}

	@Override
	public boolean add(E e) {
		if (bagMap.containsKey(e)) {
			bagMap.put(e, bagMap.get(e) + 1);
		} else {
			bagMap.put(e, 1);
		}
		bagSize++;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean result = false;
		for (Iterator iterator = c.iterator(); iterator.hasNext();) {
			E e = (E) iterator.next();
			this.add(e);
			result = true;
		}
		return result;
	}

	@Override
	public void clear() {
		bagMap.clear();
		bagSize = 0;

	}

	@Override
	public boolean contains(Object o) {
		return bagMap.containsKey(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		boolean result = true;
		for (Iterator iterator = c.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			if (!bagMap.containsKey(object)) {
				result = false;
			}
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return bagSize == 0;
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object object) {
		if (!bagMap.containsKey(object)) {
			return false;
		} else {
			
			bagMap.put((E) object, this.count((E) object) - 1);
			bagSize--;
			return true;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = false;

		for (Iterator iterator = c.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			if (this.contains(object)) {
				bagSize -= bagMap.remove(object);
				result = true;
			}
		}

		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean result = false;

		for (E e : bagMap.keySet()) {
			if (!c.contains(e)) {
				bagSize -= bagMap.remove(e);
				result = true;
			}
		}
		
		return result;
	}

	@Override
	public int size() {
		return this.bagSize;
	}


	@Override
	public int count(E e) {
		if (!bagMap.containsKey(e)) {
			return 0;
		} else {
			return bagMap.get(e);
		}
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

}
