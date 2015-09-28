//Dru Grossberg
//deg2155

/**
 * LinkedList class implements a doubly-linked list. Adapted from Weiss, Data
 * Structures and Algorithm Analysis in Java. 3rd ed.
 * http://users.cis.fiu.edu/~weiss/dsaajava3/code/SimpleLinkedList.java
 */
public class SimpleLinkedList<T> implements Iterable<T> {

	private int size;
	private Node<T> beginMarker;
	private Node<T> endMarker;

	/**
	 * This is the doubly-linked list node class.
	 */
	private class Node<NodeT> {
		public Node(NodeT d, Node<NodeT> p, Node<NodeT> n) {
			data = d;
			prev = p;
			next = n;
		}

		public NodeT data;
		public Node<NodeT> prev;
		public Node<NodeT> next;
	}

	/**
	 * Construct an empty LinkedList.
	 */
	public SimpleLinkedList() {
		doClear();
	}

	/**
	 * Change the size of this collection to zero by initializing the beginning
	 * and end marker.
	 */
	public void doClear() {
		beginMarker = new Node<>(null, null, null);
		endMarker = new Node<>(null, beginMarker, null);
		beginMarker.next = endMarker;

		size = 0;
	}

	/**
	 * @return the number of items in this collection.
	 */
	public int size() {
		return size;
	}

	/**
	 * @return boolean indicating if the linked list is empty
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * 
	 * @param idx
	 *          index to search at.
	 * @param lower
	 *          lowest valid index.
	 * @param upper
	 *          highest valid index.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *           if index is not between lower and upper, inclusive.
	 */
	private Node<T> getNode(int idx, int lower, int upper) {
		Node<T> p;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: "
					+ size());

		if (idx < size() / 2) { // Search through list from the beginning
			p = beginMarker.next;
			for (int i = 0; i < idx; i++)
				p = p.next;
		} else { // search through the list from the end
			p = endMarker;
			for (int i = size(); i > idx; i--)
				p = p.prev;
		}

		return p;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * 
	 * @param idx
	 *          index to search at.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *           if index is out of range.
	 */
	private Node<T> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Returns the item at position idx.
	 * 
	 * @param idx
	 *          the index to search in.
	 * @throws IndexOutOfBoundsException
	 *           if index is out of range.
	 */
	public T get(int idx) {
		return getNode(idx).data;
	}

	/**
	 * Changes the item at position idx.
	 * 
	 * @param idx
	 *          the index to change.
	 * @param newVal
	 *          the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException
	 *           if index is out of range.
	 */
	public T set(int idx, T newVal) {
		Node<T> p = getNode(idx);
		T oldVal = p.data;

		p.data = newVal;
		return oldVal;
	}

	/**
	 * Adds an item in front of node p, shifting p and all items after it one
	 * position to the right in the list.
	 * 
	 * @param p
	 *          Node to add before.
	 * @param x
	 *          any object.
	 * @throws IndexOutOfBoundsException
	 *           if idx < 0 or idx > size()
	 */
	private void addBefore(Node<T> p, T x) {
		Node<T> newNode = new Node<>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		size++;
	}

	/**
	 * Adds an item at specified index. Remaining items shift up one index.
	 * 
	 * @param x
	 *          any object.
	 * @param idx
	 *          position to add at.
	 * @throws IndexOutOfBoundsException
	 *           if idx < 0 or idx > size()
	 */
	public void add(int idx, T x) {
		addBefore(getNode(idx, 0, size()), x);
	}

	/**
	 * Adds an item to this collection, at the end.
	 * 
	 * @param x
	 *          any object.
	 */
	public void add(T x) {
		add(size(), x);
	}

	/**
	 * Removes the object contained in Node p.
	 * 
	 * @param p
	 *          the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private T remove(Node<T> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		size--;

		return p.data;
	}

	/**
	 * Removes an item from this collection.
	 * 
	 * @param idx
	 *          the index of the object.
	 * @return the item was removed from the collection.
	 */
	public T remove(int idx) {
		return remove(getNode(idx));
	}

	/**
	 * Returns a String representation of this collection.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		for (T x : this) {
			sb.append(x + " ");
		}
		sb.append("]");

		return new String(sb);
	}

	/********* ADD YOUR SOLUTIONS HERE *****************/

	//returns the index of the first time 
	  // `o` appears or -1 if it is not found
	  
	 public int indexOf(Object o){
	   for (int idx = 0; idx < this.size(); idx++){
	     if (get(idx).equals(o)){
	       return idx;
	     }
	   }
	   return -1;
	 }
	 
	 //reverses the order of the list
	 public void reverse(){
		 //switches the beginMarker and the endMarker
		 Node<T> temp1 = endMarker; 
		 this.endMarker = this.beginMarker;
		 this.beginMarker = temp1;
		 for (Node<T> pointer = this.beginMarker; pointer!= null; pointer = pointer.next){
			 //switches the previous pointer with the next pointer
			 Node<T> temp2 = pointer.next; 
			 pointer.next = pointer.prev;
			 pointer.prev = temp2;
		 }
	 }
	 
	/**
	 * removes duplicate elements in the list 
	 * Modify the original linked list in place
	 */
	 public void removeDuplicates(){
		 if (this.isEmpty()){//if this list is empty then do nothing
			 
		 }
		 else {//if this list is not empty then remove duplicates in it
			 Node<T> pointer1 = this.beginMarker.next;
			 while (pointer1!=this.endMarker.prev){
				 Node<T> pointer2 = pointer1.next;
				 while (pointer2!=this.endMarker){
					 if (pointer2.data.equals(pointer1.data)){
						 this.remove(pointer2);
					 }
					 pointer2=pointer2.next;
				 }
				 pointer1=pointer1.next;
			 }
		 }
	 }
	 
		//N*(N-1)/2

	 
	//interleaves aspects of the second list  into  the  linked  list and if the second
	//is  longer  than  the  default, whatever is left will be changed
	public void interleave(SimpleLinkedList<T> other){
		Node<T> pointer1 = this.beginMarker.next.next;
		Node<T> pointer2 = other.beginMarker.next;
		while (pointer1!=null && pointer2!=other.endMarker){
			this.addBefore(pointer1, pointer2.data);

			pointer1=pointer1.next;
			pointer2=pointer2.next;
		}
		while (pointer2!=other.endMarker){
			this.addBefore(this.endMarker, pointer2.data);
			pointer2=pointer2.next;
		}
		
	}
	
	
	
	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * 
	 * @return an iterator positioned prior to the first element.
	 */
	public java.util.Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	/**
	 * This is the implementation of the LinkedListIterator. It maintains a notion
	 * of a current position and of course the implicit reference to the
	 * SimpleLinkedList.
	 */
	private class LinkedListIterator implements java.util.Iterator<T> {
		private Node<T> current = beginMarker.next;
		private boolean okToRemove = false;

		public boolean hasNext() {
			return current != endMarker;
		}

		public T next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();

			T nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (!okToRemove)
				throw new IllegalStateException();

			SimpleLinkedList.this.remove(current.prev);
			//ensures that remove() cannot be called twice during a single step in
			// iteration
			okToRemove = false;
		}
	}

	/**
	 * Test the linked list.
	 */
	public static void main(String[] args) {
		SimpleLinkedList<Integer> lst = new SimpleLinkedList<>();

		for (int i = 0; i < 10; i++)
			lst.add(i);
		for (int i = 20; i < 30; i++)
			lst.add(0, i);

		lst.remove(0);
		lst.remove(lst.size() - 1);

		System.out.println(lst);
		System.out.println("\n");
		
		
		/**
		 * testing method of public int indexOf()
		 */
		int x = lst.indexOf(76);
		System.out.println(x == -1);
		System.out.println("\n");
		
		/**
		 * testing method public void reverse()
		 */
		lst.reverse();
		System.out.println(lst);
		System.out.println("\n");
		
		/**
		 * testing method public void removeDuplicates()
		 */
		lst.add(0,8);
		lst.add(7,21);
		System.out.println(lst);
		lst.removeDuplicates();
		System.out.println(lst);
		System.out.println("\n");
		
		/**
		 * testing interleave method
		 */
		SimpleLinkedList<Integer> lst2 = new SimpleLinkedList<>();
		for (int i = 0; i < 30; i++)
			lst2.add(i);
		lst.interleave(lst2);
		System.out.println(lst2);
		System.out.println(lst);
		System.out.println("\n");

	}
}
