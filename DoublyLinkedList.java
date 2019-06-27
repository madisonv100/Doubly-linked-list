package cs143;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import cs143.DoublyLinkedList.Node;

public class DoublyLinkedList<Item extends Comparable<Item>> implements IterableList<Item> {

	private static LocalTime lastChange = LocalTime.now();
	private int size = 0;
	private Node<Item> head;
	private Node<Item> tail;

	public DoublyLinkedList() {
		super();
		tail = null;
		head = null; 

	}

	/////////////////////// Node Class
	public static class Node<Item extends Comparable<Item>> {
		private Item data;
		private Node<Item> next;
		private Node<Item> previous; 

		public Node(Item data, Node<Item> next, Node<Item> previous) {
			this.data = data;
			this.next = next;
			this.previous = previous;
		}

		public Node(Item data, Node<Item> next) {
			this.data = data;
			this.next = next;
		}

		public Node(Item data) {
			this.data = data;
			this.next = null;
			this.previous = null;
		}

		public Node() {
			this.data = null;
			this.next = null;
			this.previous = null;
		}

	}// Node Class End

	@Override
	public ListIterator<Item> listIterator() {

		return new DLinkListIterator();
	}

	@Override
	public Iterator<Item> iterator() {

		return (Iterator<Item>) new DLinkListIterator();
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void append(Item item) {
		if (head == null) {
			head = tail = new Node<Item>(item);
			
			size++;

		} else {
			tail = tail.next = new Node<Item>(item, null, tail);
			size++;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Item get(int index) {
		if (isEmpty()) {
			throw new NoSuchElementException();

		}
		if (index >= size) {
			throw new NoSuchElementException();
		}
		Node<Item> current = head;
		int count = 0;
		while (count != index) {
			count++; 
			current = current.next;

		}
		return current.data;

	}

	@Override
	public boolean contains(Item item) {
		Node<Item> current = head;
		while (current != null) {
			if (current.data.equals(item)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	@Override
	public void prepend(Item item) {
		if (head == null) {
			head = tail = new Node<Item>(item);
			size++;

		} else {
			head = head.previous = new Node<Item>(item, head, null);
			size++;
		}

	}

	@Override
	public int length() {

		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;

	}

	@Override
	public Item[] toArray(Item[] array) {
		@SuppressWarnings("unchecked")
		Item[] itemArray = (Item[]) Array.newInstance(array.getClass().getComponentType(), size);
		// add items
		Node<Item> current = head;
		int i = 0;
		while (current != null) {

			itemArray[i] = (current.data);
			i++;
			{

			}
			current = current.next;
		}

		return itemArray;
	}

	private enum IteratorState {
		Start, Next, Previous, RemoveCalled
	};

	/////////////////////// DLinkListIterator Class
	public class DLinkListIterator implements ListIterator<Item> {

		private IteratorState lastMove;
		private Node<Item> previous;
		private Node<Item> next;
		private int pos = 0;
		private LocalTime lastChange = LocalTime.now();

		public DLinkListIterator() {
			super();
			next = head;
			
		}
		
		

		@Override
		public void add(Item item) {
			
			if(isEmpty()  || next == head)
			{
				prepend(item);
				previous = head;
				pos++;
				
				lastMove = lastMove.Start;
				return;
			}
			else if(previous == tail) 
			{
				
				append(item);
				previous = tail;
				//System.out.println("im adding at the tail");
				pos++;
				lastMove = lastMove.Start;
				return;
				
			}
			else
			{
				Node n = new Node(item, next, previous);
 
				previous.next = n;
				next.previous = n;
				previous = n;
				pos++;
				size++;
				lastMove = IteratorState.Start;
				//System.out.println("im adding in the middle");
				return;
			}

	

		}

		@Override
		public boolean hasNext() {
			return(next != null);
		
		} 

		@Override
		public boolean hasPrevious() {
            if(previous.previous != null)
            {
            	return true;
            }
			return false;
		}

		@Override
		public Item next() {
			
			if (next == null)
			{
				throw new IllegalStateException();

			}
			previous = next;
			next = next.next;
			pos++;
			lastMove = IteratorState.Next;
			return previous.data;
			
		}

		@Override
		public int nextIndex() {

			return pos;
		}

		@Override
		public Item previous() {

			if (previous == null)
			{
				throw new IllegalStateException();

			}
			next = previous;
			previous = previous.previous;
			
			pos--;
			lastMove = IteratorState.Previous;
			return next.data;
		}

		@Override
		public int previousIndex() {
 
			return pos-1;
		}

		@Override
		public void remove() {
			if(lastMove.equals(IteratorState.Previous))
			{
				if(previous == null)
				{
					head= next;
					next= next.next;
					next.previous = null;
					  size--;
					  pos--;
					lastMove = IteratorState.RemoveCalled;
					return;
				}
				if(previous == tail)
				{
					next = null;
					previous = tail;
					  size--;
					  pos--;
					lastMove = IteratorState.RemoveCalled;
					return;
				}
				if(next == tail)
				{
					tail = previous;
					
					next = null;
					previous.next = null;
					lastMove = IteratorState.RemoveCalled;
					return;
				}
				else {
				next = next.next;
				next.previous = previous;
				previous.next = previous;
				  size--;
				  pos--; 
				lastMove = IteratorState.RemoveCalled;
				return;
				} 
			}
			if(lastMove.equals(IteratorState.Next))
			{//remove previous
				if(previous == tail)
				{
				previous = previous.previous;
					previous.next = null;
					tail = previous;
					return;
				}
				if(previous ==head)
				{
					next.previous =  null;
					head = next; 
					
					  lastMove = IteratorState.RemoveCalled;
					  size--;
					  pos--;
					return;
				}
				else{
			previous = previous.previous;
			  previous.next = next;
			  next.previous = previous; 
			  size--;
			  pos--;
			  lastMove = IteratorState.RemoveCalled;
			  return; 
				}
			}
			if(lastMove.equals(IteratorState.Start))
			{
				throw new IllegalStateException();
			}
			if(lastMove.equals(IteratorState.RemoveCalled))
			{
				throw new IllegalStateException();
			}
		}

		@Override
		public void set(Item data) {
			if(lastMove.equals(IteratorState.Previous))
			{
				next.data = data;
				
			}
			if(lastMove.equals(IteratorState.Next))
			{
			  previous.data = data;
			}			
			
			if(lastMove.equals(IteratorState.Start))
			{
				throw new IllegalStateException();
			}
			if(lastMove.equals(IteratorState.RemoveCalled))
			{
				throw new IllegalStateException();
			}
		}

	}/////////////////////// DLinkListIterator End

}
