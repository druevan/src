//Dru Grossberg
//deg2155

import SimpleLinkedList.LinkedListIterator;

/**
 * Simulates python's range function
 */
public class Range implements Iterable<Integer> {
	public int min;
	public int max;
	public int incr;
	public SimpleLinkedList<Integer> temporary;
	

	public Range(int min, int max, int increment) {
		this.min=min;
		this.max=max;
		this.incr=increment;
		temporary = new SimpleLinkedList<>();
		int p1 = this.min;
		while (p1 <= this.max){
			temporary.add(p1);
			p1=p1+this.increment;
		}
	}

	public Range(int min, int max) {
		this.min=min;
		this.max=max;
		temporary = new SimpleLinkedList<>();
		int p1 = this.min;
		while (p1 >= this.max){
			temporary.add(p1);
			p1=p1+1;
		}
		
	}

	public java.util.Iterator<Integer> iterator() {
		return temporary.iterator();
	}
	
	public static void main(String[] args){
		Range new = new Range(2,6);
		System.out.print(new);
		
		for(Integer j : new Range(1,8)){
			System.out.print(j);
		}
	}
}

