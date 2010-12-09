package bagcollection;

import java.util.Collection;

/**
 * Bag interface that extends a Collection
 * @author raul
 *
 * @param <E>
 */
public interface Bag<E> extends Collection<E> {
	
	int count(E e);

}
