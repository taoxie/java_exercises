package hashset;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

	private final Map<E, Object> map;
	
	public MyHashSet() {
		map = new HashMap<E, Object>();
	}
	
	@Override
	public boolean add(E argument) {
		if (map.containsKey(argument)) {
			return false;
		} else {
			map.put(argument, Boolean.TRUE);
			return true;
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> argument) {
		boolean result = false;
		for (Iterator iterator = argument.iterator(); iterator.hasNext();) {
			E e = (E) iterator.next();
			Boolean previousElement = (Boolean)map.put(e, Boolean.TRUE);
			if (!result && previousElement == null) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public void clear() {
		map.clear();
	}
	

	@Override
	public boolean contains(Object argument) {
		return map.containsKey(argument);
	}

	@Override
	public boolean containsAll(Collection<?> argument) {
		boolean result = true;
		for (Iterator iterator = argument.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			if (!map.containsKey(object)) {
				result = false;
				break;
			}
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	@Override
	public boolean remove(Object argument) {
		if (map.remove(argument) == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> argument) {
		boolean result = false;
		for (Iterator iterator = argument.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			Boolean value = (Boolean) map.remove(object);
			if (value != null && value.booleanValue() == true) {
				result = true;
			}
		}
		
	    return result;
	}

	@Override
	public boolean retainAll(Collection<?> argument) {
		boolean result = false;
		Object[] arrayOfKeys= map.keySet().toArray();
		for (int i = 0; i < arrayOfKeys.length; i++) {
			Object object = (Object) arrayOfKeys[i];
			if (!argument.contains(object)) {
				map.remove(object);
				result = true;
			}
		}
		return result;
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Object[] toArray() {
		return map.keySet().toArray();
	}

	@Override
	public <T> T[] toArray(T[] argument) {
		return map.keySet().toArray(argument);
	}

}
