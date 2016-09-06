package set;

import java.util.NoSuchElementException;
import java.util.Iterator;


public class MaxSet<E extends Comparable<E>> extends ArraySet<E> {
	private E maxElement;
	
	/**
	 * Constructs a new empty set.
	 */
	public MaxSet() {
		super(); 
		maxElement = null;
	}
	
	/** Returns the currently largest element in this set. 
	pre: the set is not empty 
	post: the set is unchanged 
	@return the currently largest element in this set 
	@throws NoSuchElementException if this set is empty 
	*/ 
	public E getMax() {
		if (maxElement == null) {
			throw new NoSuchElementException();
		} else {
			return maxElement;
		}
	}
	
	/** 
	 * Adds the specified element to this set, if it is not already present.
	 * post: x is added to the set if it is not already present
	 * @param  x the element to be added
	 * @return true if the specified element was added
	 */
	public boolean add(E x) {
		boolean flag = super.add(x);
		if(flag) {
			if (maxElement == null) {
				maxElement = x;
			} else if (x.compareTo(maxElement) > 0) {
				maxElement = x;
			}
			return flag;
		}
		return flag;
	}
	
	/** 
	 * Removes the specified element from this set if it is present. 
	 * post: 	x is removed if it was present
	 * @param 	x the element to remove - if present
	 * @return true if the set contained the specified element
	 */
	public boolean remove(Object x) {
		if(super.remove(x)) {
			@SuppressWarnings("unchecked")
			E removed_element = (E) x;
			if(size() > 0) {
				if(removed_element.compareTo(maxElement) == 0) {
					Iterator<? extends E> it = iterator();
					maxElement = it.next();
					while (it.hasNext()) {
						E tmp = it.next();
						if (tmp.compareTo(maxElement) > 0) {
							maxElement = tmp;
						}
					}
				}
			} else {
				maxElement = null;
			}
			return true;
		}
		return false;
	}
	
	/** Adds all of the elements in the specified set, for which it is 
	possible, to this set. 
	post: all elements, for which it is possible, in the 
	specified set are added to this set. 
	@return true if this set changed as a result of the call 
	*/
	public boolean addAll(SimpleSet<? extends E> c) {
		boolean flag = false;
		
		for(E e : c) {
			if(flag == false) {
				flag = add(e);
			} else {
				add(e);
			}
		}
		return flag;
	}
	
}